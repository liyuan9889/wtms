package com.ruoyi.project.oa.controller;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.oa.domain.Worklog;
import com.ruoyi.project.oa.domain.WorklogUser;
import com.ruoyi.project.oa.mapper.WorklogMapper;
import com.ruoyi.project.oa.service.IWorklogService;
import com.ruoyi.project.oa.service.IWorklogUserService;
import com.ruoyi.project.system.role.domain.Role;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 工作日志Controller
 *
 * @author ruoyi
 * @date 2020-04-03
 */
@Controller
@RequestMapping("/oa/worklog")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WorklogController extends BaseController
{
    private String prefix = "oa/worklog";

    private final IWorklogService worklogService;
    private final IUserService userService;
    private final IWorklogUserService worklogUserService;
    private final WorklogMapper worklogMapper;

    @RequiresPermissions("oa:worklog:view")
    @GetMapping()
    public String worklog()
    {
        return prefix + "/worklog";
    }

    /**
     * 查询工作日志列表
     */
    @RequiresPermissions("oa:worklog:list")
    @PostMapping("/listForMain")
    @ResponseBody
    public TableDataInfo listForMain(Worklog worklog)
    {
        startPage();
        Long userId = worklog.getUserId();
        if (null != userId){
            worklog.setCreateUserId(userId);
        }
        List<Worklog> list = worklogService.selectWorklogList(worklog);
        return getDataTable(list);
    }

    /**
     * 查询工作日志列表
     */
    @RequiresPermissions("oa:worklog:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Worklog worklog)
    {
        startPage();
//        Long userId = worklog.getUserId();
//        if (null != userId){
//            worklog.setCreateUserId(userId);
//        }
//        List<Role> sysRoleList = getSysUser().getRoles();
//        for(Role role:sysRoleList){
//            if(role.getRoleId().toString().equals("2")){
//                worklog.setDeptId(getSysUser().getDeptId());
//                break;
//            }
//        }
        worklog.setDeptId(getSysUser().getDeptId());
        List<Worklog> list = worklogService.selectWorklogList(worklog);
        return getDataTable(list);
    }

    /**
     * 导出工作日志列表
     */
    @RequiresPermissions("oa:worklog:export")
    @Log(title = "工作日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Worklog worklog)
    {
        List<Worklog> list = worklogService.selectWorklogList(worklog);
        ExcelUtil<Worklog> util = new ExcelUtil<Worklog>(Worklog.class);
        return util.exportExcel(list, "worklog");
    }

    /**
     * 新增工作日志
     */
    @GetMapping("/add")
    public String add(ModelMap modelMap)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        modelMap.put("startDate", sdf.format(new Date()));
        return prefix + "/add";
    }

    /**
     * 新增保存工作日志
     */
    @RequiresPermissions("oa:worklog:add")
    @Log(title = "工作日志", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Worklog worklog)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            User sysUser = getSysUser();
            worklog.setCreateUserName(sysUser.getUserName());
            worklog.setDeptId(sysUser.getDeptId());
            worklog.setCreateUserId(sysUser.getUserId());
//          int resultMinutes = getMinutes(worklog.getStartTime(), worklog.getEndTime());
            long resultMinutes = getDistanceTime(sdf.parse(worklog.getStartTime()).getTime(), sdf.parse(worklog.getEndTime()).getTime());
            worklog.setMinutes(Integer.valueOf(resultMinutes+"")*60);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return toAjax(worklogService.insertWorklog(worklog));
    }

    /**
     * 根据两个时间获取分钟时差
     * @param startTime
     * @param endTime
     * @return
     */
    public int getMinutes(String startTime, String endTime) {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        int result = 0;
        try {
            Date date = df.parse(startTime);
            Date now = df.parse(endTime);

            long l = now.getTime() - date.getTime();
            long day = l / (24 * 60 * 60 * 1000);
            long hour = (l / (60 * 60 * 1000) - day * 24);
            long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
            result = (int) (hour * 60 + min);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 计算time2减去time1的差值 差值只设置 几天 几个小时 或 几分钟
     * 根据差值返回多长之间前或多长时间后
     * @param time1
     * @param time2
     * @return
     */
    public static long getDistanceTime(long time1, long time2) {
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        long diff;

        if (time1 < time2) {
            diff = time2 - time1;
        } else {
            diff = time1 - time2;
        }
        day = diff / (24 * 60 * 60 * 1000);
        hour = (diff / (60 * 60 * 1000) - day * 24);
        min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
        sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
//        if (day != 0) return day + "天"+hour + "小时"+min + "分钟" + sec + "秒";
//        if (hour != 0) return hour;

        if (day > 0){
            hour += day*24;
            return hour;
        } else if (day == 0 && hour != 0){
            return hour;
        }

        return 0;
    }


    @GetMapping("/detail/{oaWorklogId}")
    public String detail(@PathVariable("oaWorklogId") Long oaWorklogId, ModelMap mmap)
    {
        List<User> userList = new ArrayList<>();
        Worklog worklog = worklogService.selectWorklogById(oaWorklogId);
        User user = new User();
        user.setDeptId(getSysUser().getDeptId());
        List<User> uList = userService.selectJuniorUserByUser(user);
        // 工作人员
        WorklogUser worklogUser = new WorklogUser();
        worklogUser.setWorklogId(oaWorklogId);
        List<Long> worklogUserList = worklogUserService.selectWorklogIdList(worklogUser);
        for (User u : uList) {
            User us = new User();
            us.setUserId(u.getUserId());
            us.setUserName(u.getUserName());
            if (worklogUserList.contains(u.getUserId())) {
                us.setFlag(true);
            }
            userList.add(us);
        }

        mmap.put("worklog", worklog);
        mmap.put("userList", userList);
        return prefix + "/detail";
    }

    /**
     * 修改工作日志
     */
    @GetMapping("/edit/{oaWorklogId}")
    public String edit(@PathVariable("oaWorklogId") Long oaWorklogId, ModelMap mmap)
    {
        List<User> userList = new ArrayList<>();
        Worklog worklog = worklogService.selectWorklogById(oaWorklogId);
        User user = new User();
        user.setDeptId(getSysUser().getDeptId());
        List<User> uList = userService.selectJuniorUserByUser(user);
        // 工作人员
        WorklogUser worklogUser = new WorklogUser();
        worklogUser.setWorklogId(oaWorklogId);
        List<Long> worklogUserList = worklogUserService.selectWorklogIdList(worklogUser);
        for (User u : uList) {
            User us = new User();
            us.setUserId(u.getUserId());
            us.setUserName(u.getUserName());
            if (worklogUserList.contains(u.getUserId())) {
                us.setFlag(true);
            }
            userList.add(us);
        }

        mmap.put("worklog", worklog);
        mmap.put("userList", userList);
        return prefix + "/edit";
    }

    /**
     * 修改保存工作日志
     */
    @RequiresPermissions("oa:worklog:edit")
    @Log(title = "工作日志", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Worklog worklog)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            User sysUser = getSysUser();
            worklog.setDeptId(sysUser.getDeptId());
            long resultMinutes = getDistanceTime(sdf.parse(worklog.getStartTime()).getTime(), sdf.parse(worklog.getEndTime()).getTime());
            worklog.setMinutes(Integer.valueOf(resultMinutes+"")*60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return toAjax(worklogService.updateWorklog(worklog));
    }

    /**
     * 删除工作日志
     */
    @RequiresPermissions("oa:worklog:remove")
    @Log(title = "工作日志", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(worklogService.deleteWorklogByIds(ids));
    }

    @GetMapping("/ajaxSelectWorklogByUserIdForMain")
    @ResponseBody
    public Object ajaxSelectWorklogByUserIdForMain(@RequestParam(value="userId", required = false) Long userId) {
        Worklog workO = new Worklog();
        if (null != userId){
            workO.setUserId(userId);
        }
        List<Worklog> worklogList = worklogMapper.selectWorklogByUserIdForMain(workO);
        Worklog worklogResult = new Worklog();
        for (Worklog worklog : worklogList) {
            int searchType = worklog.getSearchType();
            int num = worklog.getNum();
            switch (searchType) {
                case 0: // 总数
                    worklogResult.setWorklog_totalNum(num);
                    break;
            }
        }
        return AjaxResult.success(worklogResult);
    }

}
