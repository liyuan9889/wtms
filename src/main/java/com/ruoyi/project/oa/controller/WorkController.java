package com.ruoyi.project.oa.controller;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.oa.domain.Task;
import com.ruoyi.project.oa.domain.Work;
import com.ruoyi.project.oa.domain.WorkUser;
import com.ruoyi.project.oa.mapper.WorkMapper;
import com.ruoyi.project.oa.service.IWorkService;
import com.ruoyi.project.system.dept.domain.Dept;
import com.ruoyi.project.system.dept.mapper.DeptMapper;
import com.ruoyi.project.system.role.domain.Role;
import com.ruoyi.project.system.role.mapper.RoleMapper;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.mapper.UserMapper;
import com.ruoyi.project.system.user.mapper.UserRoleMapper;
import io.swagger.models.auth.In;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 工作汇报Controller
 * 
 * @author ruoyi
 * @date 2020-04-03
 */
@Controller
@RequestMapping("/oa/work")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WorkController extends BaseController
{
    private String prefix = "oa/work";

    private final IWorkService workService;
    private final WorkMapper workMapper;
    private final DeptMapper deptMapper;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;

    @RequiresPermissions("oa:work:view")
    @GetMapping()
    public String work(ModelMap modelMap)
    {
        modelMap.put("userId", getUserId());
        return prefix + "/work";
    }

    /**
     * 查询工作汇报列表
     */
    @RequiresPermissions("oa:work:list")
    @PostMapping("/listForMain")
    @ResponseBody
    public TableDataInfo listForMain(Work work, @RequestParam(value = "subSearchType", required = false) Integer subSearchType)
    {
        startPage();
        Long userId = work.getUserId();
        if (null != userId){
            work.setSponsorId(userId);
        }
        List<Work> list = new ArrayList<>();
        if (null != subSearchType) {
            switch (subSearchType) {
                case 0: // 总数
                    list = workService.selectWorkList(work);
                    break;
                case 1: // 进行中
                    work.setStatus(1);
                    work.setSubStatus(1);
                    list = workService.selectWorkList(work);
                    break;
                case 2: // 已完结
                    work.setStatus(2);
                    list = workService.selectWorkList(work);
                    break;
                case 3: // 超期
                    work.setStatus(1);
                    work.setSubStatus(2);
                    list = workService.selectWorkList(work);
                    break;
            }
        } else {
            list = workService.selectWorkList(work);
        }
        
        return getDataTable(list);
    }

    /**
     * 查询工作汇报列表
     */
    @RequiresPermissions("oa:work:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Work work)
    {
        startPage();
        List<Role> sysRoleList = getSysUser().getRoles();
        boolean flag = false;
        for(Role role:sysRoleList){
            if(role.getRoleId().toString().equals("2")){
                work.setDeptId(getSysUser().getDeptId());
                flag = true;
                break;
            }
        }
        if (flag){
            work.setDeptId(getSysUser().getDeptId());
        }

        List<Work> list = workService.selectWorkList(work);
        return getDataTable(list);
    }

    /**
     * 查询工作汇报列表
     */
    @PostMapping("/selectWorkReplyContentList")
    @ResponseBody
    public List<Work> selectWorkReplyContentList(@RequestParam("workId") Long workId)
    {
        Work work = new Work();
        work.setOaWorkId(workId);
        List<Work> list = workService.selectWorkReplyContentList(work);
        return list;
    }

    /**
     * 导出工作汇报列表
     */
    @RequiresPermissions("oa:work:export")
    @Log(title = "工作汇报", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Work work)
    {
        List<Work> list = workService.selectWorkList(work);
        for (Work t: list) {
            String content = t.getContent();
            if (StringUtils.isNotEmpty(content)){
                t.setContent(StringUtils.stripHtml(content));
            }
        }
        ExcelUtil<Work> util = new ExcelUtil<Work>(Work.class);
        return util.exportExcel(list, "work");
    }

    /**
     * 新增工作汇报
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存工作汇报
     */
    @RequiresPermissions("oa:work:add")
    @Log(title = "工作汇报", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Work work)
    {
        work.setSponsorId(getUserId());
        return toAjax(workService.insertWork(work));
    }

    /**
     * 跳转到反馈工作汇报
     */
    @GetMapping("/toFeedBack/{oaWorkId}")
    public String toFeedBack(@PathVariable("oaWorkId") Long oaWorkId, ModelMap mmap)
    {
        Work work = workService.selectWorkById(oaWorkId);
        List<Work> workList = workService.selectWorkReplyContentList(work);
        List<Role> sysRoleList = getSysUser().getRoles();
        int flag = 0;
        for (Role role: sysRoleList) {
            if (role.getRoleId().longValue() == 3) {
                flag = 1;
                break;
            }
        }
        mmap.put("flag", flag);
        mmap.put("work", work);
        mmap.put("workList", workList);
        return prefix + "/workFeedback";
    }

    @GetMapping("/detail/{oaWorkId}")
    public String detail(@PathVariable("oaWorkId") Long oaWorkId, ModelMap mmap)
    {
        Work work = workService.selectWorkById(oaWorkId);
        mmap.put("work", work);
        return prefix + "/detail";
    }

    /**
     * 修改工作汇报
     */
    @GetMapping("/edit/{oaWorkId}")
    public String edit(@PathVariable("oaWorkId") Long oaWorkId, ModelMap mmap)
    {
        Work work = workService.selectWorkById(oaWorkId);
        mmap.put("work", work);
        return prefix + "/edit";
    }

    /**
     * 修改保存工作汇报
     */
    @RequiresPermissions("oa:work:edit")
    @Log(title = "工作汇报", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Work work)
    {
        return toAjax(workService.updateWork(work));
    }

    /**
     * 删除工作汇报
     */
    @RequiresPermissions("oa:work:remove")
    @Log(title = "工作汇报", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(workService.deleteWorkByIds(ids));
    }


    /**
     * 汇报工作
     * @param workId
     * @param content
     * @return
     */
    @PostMapping("/feedback")
    @ResponseBody
    public Object feedback(@RequestParam("workId") Long workId,
                           @RequestParam("progress") Integer progress,
                           @RequestParam("hours") String hours,
                           @RequestParam("content") String content) {
        WorkUser workUser = new WorkUser();
        workUser.setWorkId(workId);
        workUser.setUserId(getUserId());
        workUser.setProgress(progress);
        if (null != progress && progress == 100 ){
            Work work = new Work();
            work.setOaWorkId(workId);
            work.setStatus(2);
            workService.updateWork(work);
        }
        workUser.setHours(hours);
        if (StringUtils.isNotEmpty(content)){
            String contentStr = content.replace("<img", "<img width=\"200px\"");
            workUser.setContent(contentStr);
        }
        return toAjax(workService.insertWorkUser(workUser));
    }

    @GetMapping("/ajaxSelectWorkByUserIdForMain")
    @ResponseBody
    public Object ajaxSelectWorkByUserIdForMain(@RequestParam(value="userId", required = false) Long userId) {
        Work workO = new Work();
        if (null != userId){
            workO.setUserId(userId);
        }
        List<Work> workList = workMapper.selectWorkByUserIdForMain(workO);
        Work workResult = new Work();
        for (Work work : workList) {
            int searchType = work.getSearchType();
            int num = work.getNum();
            switch (searchType) {
                case 0: // 总数
                    workResult.setWork_totalNum(num);
                    break;
                case 1: // 进行中
                    workResult.setWork_proceedNum(num);
                    break;
                case 2: // 待评价
                    workResult.setWork_preparedEvaluatingNum(num);
                    break;
                case 3: // 超期
                    workResult.setWork_expiredNum(num);
                    break;
            }
        }
        return AjaxResult.success(workResult);
    }

    @GetMapping("/isAuthorityOperate")
    @ResponseBody
    public AjaxResult isAuthorityOperate(@RequestParam("sponsorId") Long sponsorId) {
        if (getSysUser().isAdmin()){
            return AjaxResult.success();
        }
        boolean roleFlag = false;
        List<Role> roleList = roleMapper.selectRolesByUserId(sponsorId);
        for (Role role : roleList){
            if (role.getRoleId().longValue() == 3) {
                roleFlag = true;
                break;
            }
        }
        if (roleFlag && sponsorId != getUserId()){
            return AjaxResult.error();
        }

        User user = userMapper.selectUserById(sponsorId);
        if (user.getDeptId() == getSysUser().getDeptId()) {
            return AjaxResult.success();
        }
        return AjaxResult.error();
    }

}
