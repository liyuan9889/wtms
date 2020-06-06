package com.ruoyi.project.oa.controller;

import com.google.common.base.Joiner;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.oa.domain.*;
import com.ruoyi.project.oa.mapper.TaskMapper;
import com.ruoyi.project.oa.service.ITaskCommentService;
import com.ruoyi.project.oa.service.ITaskReplyService;
import com.ruoyi.project.oa.service.ITaskService;
import com.ruoyi.project.oa.service.ITaskUserService;
import com.ruoyi.project.system.role.domain.Role;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.service.IUserService;
import com.ruoyi.project.tool.DateUtils;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 任务督办Controller
 * 
 * @author ruoyi
 * @date 2020-03-29
 */
@Controller
@RequestMapping("/oa/task")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TaskController extends BaseController
{
    private String prefix = "oa/task";

    private final TaskMapper taskMapper;
    private final ITaskService taskService;
    private final ITaskUserService taskUserService;
    private final ITaskCommentService taskCommentService;
    private final IUserService userService;
    private final ITaskReplyService taskReplyService;

    @RequiresPermissions("oa:task:view")
    @GetMapping()
    public String task(ModelMap modelMap)
    {
        modelMap.put("userId", getUserId());
        return prefix + "/task";
    }

    /**
     * 查询任务督办列表
     */
    @RequiresPermissions("oa:task:list")
    @PostMapping("/listForMain")
    @ResponseBody
    public TableDataInfo listForMain(Task task, @RequestParam(value = "userId", required = false) Long userId,
                                                @RequestParam(value = "subSearchType", required = false) Integer subSearchType)
    {
        startPage();
        List<Task> list = new ArrayList<>();
        if (null != subSearchType) {
            switch (subSearchType) {
                case 0: // 总数
                    list = taskService.selectTaskList(task);
                    break;
                case 1: // 进行中
                    task.setStatus(1);
                    task.setSubStatus(1);
                    list = taskService.selectTaskList(task);
                    break;
                case 2: // 未通过
                    task.setStatus(3);
                    list = taskService.selectTaskList(task);
                    break;
                case 3: // 待评价
                    task.setStatus(2);
                    list = taskService.selectTaskList(task);
                    break;
                case 4: // 已完结
                    task.setStatus(4);
                    list = taskService.selectTaskList(task);
                    break;
                case 5: // 已终结
                    task.setStatus(5);
                    list = taskService.selectTaskList(task);
                    break;
                case 6: // 超期
                    task.setStatus(1);
                    task.setSubStatus(2);
                    list = taskService.selectTaskList(task);
                    break;
            }
        } else {
            list = taskService.selectTaskList(task);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            for (Task taskO : list ) {
                if (taskO.getStatus() == 1){
                    int compare = DateUtils.dateCompare(sdf.parse(sdf.format(new Date())), sdf.parse(taskO.getEndDate()));
                    if (compare == 1) {
                        taskO.setStatus(6);
                    }
                }

                taskO.setSponsorName(userService.selectUserById(taskO.getSponsorId()).getUserName());
                List<String> executorList = userService.selectUserNameForOaTaskUser(taskO.getOaTaskId(), 1);
                List<String> appraiserList = userService.selectUserNameForOaTaskUser(taskO.getOaTaskId(), 2);
                List<String> partUserList = userService.selectUserNameForOaTaskUser(taskO.getOaTaskId(), 3);
                List<String> shareUserList = userService.selectUserNameForOaTaskUser(taskO.getOaTaskId(), 4);
                List<String> leaderList = userService.selectUserNameForOaTaskUser(taskO.getOaTaskId(), 5);
                taskO.setExecutorName(Joiner.on(",").join(executorList));
                taskO.setAppraiserName(Joiner.on(",").join(appraiserList));
                taskO.setPartUserName(Joiner.on(",").join(partUserList));
                taskO.setShareUserName(Joiner.on(",").join(shareUserList));
                taskO.setLeaderName(Joiner.on(",").join(leaderList));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return getDataTable(list);
    }

    /**
     * 查询任务督办列表
     */
    @RequiresPermissions("oa:task:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Task task, @RequestParam(value = "userId", required = false) Long userId)
    {
        startPage();
        List<Role> sysRoleList = getSysUser().getRoles();
        boolean flag = false;
        for(Role role:sysRoleList){
            if(role.getRoleId().toString().equals("2")){
                task.setDeptId(getSysUser().getDeptId());
                flag = true;
                break;
            }
        }
        if (flag){
            task.setUserId(getUserId());
        }

        List<Task> list = taskService.selectTaskList(task);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            for (Task taskO : list) {
                if (taskO.getStatus() == 1) {
                    int compare = DateUtils.dateCompare(sdf.parse(sdf.format(new Date())), sdf.parse(taskO.getEndDate()));
                    if (compare == 1) {
                        taskO.setStatus(6);
                    }
                }
                taskO.setSponsorName(userService.selectUserById(taskO.getSponsorId()).getUserName());
                List<String> executorList = userService.selectUserNameForOaTaskUser(taskO.getOaTaskId(), 1);
                List<String> appraiserList = userService.selectUserNameForOaTaskUser(taskO.getOaTaskId(), 2);
                List<String> partUserList = userService.selectUserNameForOaTaskUser(taskO.getOaTaskId(), 3);
                List<String> shareUserList = userService.selectUserNameForOaTaskUser(taskO.getOaTaskId(), 4);
                List<String> leaderList = userService.selectUserNameForOaTaskUser(taskO.getOaTaskId(), 5);
                taskO.setExecutorName(Joiner.on(",").join(executorList));
                taskO.setAppraiserName(Joiner.on(",").join(appraiserList));
                taskO.setPartUserName(Joiner.on(",").join(partUserList));
                taskO.setShareUserName(Joiner.on(",").join(shareUserList));
                taskO.setLeaderName(Joiner.on(",").join(leaderList));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return getDataTable(list);
    }

    /**
     * 跳转到反馈任务汇报
     */
    @GetMapping("/toFeedBack/{userType}/{oaTaskId}")
    public String toFeedBack(@PathVariable("userType") Integer userType, @PathVariable("oaTaskId") Long oaTaskId, ModelMap mmap)
    {
        Task task = taskService.selectTaskById(oaTaskId);
        task.setUserType(userType);
        task.setUserId(getUserId());
        List<Task> taskList = taskService.selectTaskReplyContentList(task);
        // 领导标志位
        List<Role> sysRoleList = getSysUser().getRoles();
        int flag = 0;
        if (getSysUser().isAdmin()) {
            flag = 1;
        } else{
            for (Role role: sysRoleList) {
                if (role.getRoleId().longValue() == 3) {
                    flag = 1;
                    break;
                }
            }
        }

        // 判断是否是负责人 0：否 1：是
        int isExecutor = 0;
        task.setUserType(1);
        List<Task> listExecutor = taskService.selectTaskList(task);
        if (!CollectionUtils.isEmpty(listExecutor)){
            isExecutor = 1;
        }
        // 判断是否是评价人 0：否 1：是
        int isAppraiser = 0;
        task.setUserType(2);
        List<Task> listAppraiser = taskService.selectTaskList(task);
        if (!CollectionUtils.isEmpty(listAppraiser)){
            isAppraiser = 1;
        }
        // 判断是否是参与人 0：否 1：是
        int isPart = 0;
        task.setUserType(3);
        List<Task> listPart = taskService.selectTaskList(task);
        if (!CollectionUtils.isEmpty(listPart)){
            isPart = 1;
        }
        // 判断是否是共享人 0：否 1：是
        int isShare = 0;
        task.setUserType(4);
        List<Task> listShare = taskService.selectTaskList(task);
        if (!CollectionUtils.isEmpty(listShare)){
            isShare = 1;
        }

        List<User> executorIdList = new ArrayList<>();
        List<User> appraiserIdList = new ArrayList<>();
        List<User> partUserIdList = new ArrayList<>();
        List<User> shareUserIdList = new ArrayList<>();
        User user = new User();
        user.setDeptId(task.getDeptId());
        List<User> userList = userService.selectJuniorUserByUser(user);
        // 负责人
        TaskUser taskUser1 = new TaskUser();
        taskUser1.setTaskId(oaTaskId);
        taskUser1.setUserType(1);
        List<Long> taskUserIdList = taskUserService.selectTaskUserIdList(taskUser1);
        for (User u : userList) {
            User us = new User();
            us.setUserId(u.getUserId());
            us.setUserName(u.getUserName());
            if (taskUserIdList.contains(u.getUserId())) {
                us.setFlag(true);
            }
            executorIdList.add(us);
        }
        if (taskUserIdList.contains(getUserId())) {
            mmap.put("specialFlag", "1");
        } else {
            mmap.put("specialFlag", "0");
        }
        // 评价人
        TaskUser taskUser2 = new TaskUser();
        taskUser2.setTaskId(oaTaskId);
        taskUser2.setUserType(2);
        List<Long> taskUserList2 = taskUserService.selectTaskUserIdList(taskUser2);
        for (User u : userList) {
            User us = new User();
            us.setUserId(u.getUserId());
            us.setUserName(u.getUserName());
            if (taskUserList2.contains(u.getUserId())) {
                us.setFlag(true);
            }
            appraiserIdList.add(us);
        }
        // 参与人
        TaskUser taskUser3 = new TaskUser();
        taskUser3.setTaskId(oaTaskId);
        taskUser3.setUserType(3);
        List<Long> taskUserList3 = taskUserService.selectTaskUserIdList(taskUser3);
        for (User u : userList) {
            User us = new User();
            us.setUserId(u.getUserId());
            us.setUserName(u.getUserName());
            if (taskUserList3.contains(u.getUserId())) {
                us.setFlag(true);
            }
            partUserIdList.add(us);
        }
        // 共享人
        TaskUser taskUser4 = new TaskUser();
        taskUser4.setTaskId(oaTaskId);
        taskUser4.setUserType(4);
        List<Long> taskUserList4 = taskUserService.selectTaskUserIdList(taskUser4);
        for (User u : userList) {
            User us = new User();
            us.setUserId(u.getUserId());
            us.setUserName(u.getUserName());
            if (taskUserList4.contains(u.getUserId())) {
                us.setFlag(true);
            }
            shareUserIdList.add(us);
        }

        int schedule = taskService.selectTaskProgressByTaskId(oaTaskId);
        mmap.put("schedule", schedule + "%");
        mmap.put("executorIdList", executorIdList);
        mmap.put("appraiserIdList", appraiserIdList);
        mmap.put("partUserIdList", partUserIdList);
        mmap.put("shareUserIdList", shareUserIdList);
        mmap.put("userName", getSysUser().getUserName());
        mmap.put("flag", flag);
        mmap.put("isExecutor", isExecutor);
        mmap.put("isAppraiser", isAppraiser);
        mmap.put("isPart", isPart);
        mmap.put("isShare", isShare);
        mmap.put("task", task);
        mmap.put("taskList", taskList);
        return prefix + "/taskFeedback";
    }

    /**
     * 汇报工作
     * @param taskId
     * @param content
     * @return
     */
    @PostMapping("/feedback")
    @ResponseBody
    public Object feedback(@RequestParam("taskId") Long taskId,
                           @RequestParam("progress") Integer progress,
                           @RequestParam("hours") String hours,
                           @RequestParam("content") String content) {
        TaskReply taskReply = new TaskReply();
        taskReply.setTaskId(taskId);
        taskReply.setUserId(getUserId());
        taskReply.setProgress(progress);
        taskReply.setUserName(getSysUser().getUserName());
        if (null != progress && progress == 100 ) {
            TaskUser taskUser = new TaskUser();
            taskUser.setTaskId(taskId);
            taskUser.setUserType(2);
            List<TaskUser> taskUserList = taskUserService.selectTaskUserList(taskUser);
            TaskComment taskComment = new TaskComment();
            taskComment.setTaskId(taskId);
            List<TaskComment> taskCommentList = taskCommentService.searchCommentListByTaskId(taskComment);
            if (taskUserList.size() == taskCommentList.size()){
                Task task = new Task();
                task.setOaTaskId(taskId);
                task.setStatus(4);
                taskService.updateTask(task);
            }
        }
        taskReply.setHours(hours);
        if (StringUtils.isNotEmpty(content)){
            String contentStr = content.replace("<img", "<img width=\"200px\"");
            taskReply.setContent(contentStr);
        }
        return toAjax(taskReplyService.insertTaskReply(taskReply));
    }

    /**
     * 弹出终结任务窗口
     * @param oaTaskId
     * @param modelMap
     * @return
     */
    @GetMapping("/toOverTask/{oaTaskId}")
    public String toOverTask(@PathVariable("oaTaskId") Long oaTaskId, ModelMap modelMap) {
        Task task = taskService.selectTaskById(oaTaskId);
        modelMap.put("oaTaskId", oaTaskId);
        modelMap.put("task", task);
        return prefix + "/overTask";
    }

    /**
     * 查看终结任务
     * @param oaTaskId
     * @param modelMap
     * @return
     */
    @GetMapping("/searchOverTask/{oaTaskId}")
    public String searchOverTask(@PathVariable("oaTaskId") Long oaTaskId, ModelMap modelMap) {
        Task task = taskService.selectTaskById(oaTaskId);
        modelMap.put("task", task);
        return prefix + "/overTask";
    }

    @GetMapping("/taskStatisticsByUserId")
    public String taskStatisticsByUserId(ModelMap modelMap, @RequestParam("userId") Integer userId) {
        modelMap.put("userId", userId);
        return prefix + "/taskStatistics";
    }

    /**
     * 导出任务督办列表
     */
    @RequiresPermissions("oa:task:export")
    @Log(title = "任务督办", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Task task)
    {
        List<Task> list = taskService.selectTaskList(task);
        for (Task t: list) {
            String content = t.getContent();
            if (StringUtils.isNotEmpty(content)){
                t.setContent(StringUtils.stripHtml(content));
            }
        }
        ExcelUtil<Task> util = new ExcelUtil<Task>(Task.class);
        return util.exportExcel(list, "task");
    }

    /**
     * 新增任务督办
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存任务督办
     */
    @RequiresPermissions("oa:task:add")
    @Log(title = "任务督办", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Task task)
    {
        task.setSponsorId(getUserId());
        int count = taskService.insertTask(task);
        if (count > 0) {
            List<TaskUser> taskUserList = new ArrayList<>();
            Long taskId = task.getOaTaskId();
            String executorUserIds = task.getExecutorId();
            String appraiserUserIds = task.getAppraiserId();
            String partUserIds = task.getPartId();
            String shareUserIds = task.getShareId();
            String leaderIds = task.getLeaderId();
            if (StringUtils.isNotEmpty(executorUserIds)) {
                String[] executorIds = executorUserIds.split(",");
                for (String executorId : executorIds) {
                    TaskUser taskUser = new TaskUser();
                    taskUser.setTaskId(taskId);
                    taskUser.setUserId(Long.parseLong(executorId));
                    taskUser.setUserType(1);
                    taskUserList.add(taskUser);
                }
            }
            if (StringUtils.isNotEmpty(appraiserUserIds)) {
                String[] appraiserIds = appraiserUserIds.split(",");
                for (String appraiserId : appraiserIds) {
                    TaskUser taskUser = new TaskUser();
                    taskUser.setTaskId(taskId);
                    taskUser.setUserId(Long.parseLong(appraiserId));
                    taskUser.setUserType(2);
                    taskUserList.add(taskUser);
                }
            }
            if (StringUtils.isNotEmpty(partUserIds)) {
                String[] partIds = partUserIds.split(",");
                for (String partId : partIds) {
                    TaskUser taskUser = new TaskUser();
                    taskUser.setTaskId(taskId);
                    taskUser.setUserId(Long.parseLong(partId));
                    taskUser.setUserType(3);
                    taskUserList.add(taskUser);
                }
            }
            if (StringUtils.isNotEmpty(shareUserIds)) {
                String[] shareIds = shareUserIds.split(",");
                for (String shareUserId : shareIds) {
                    TaskUser taskUser = new TaskUser();
                    taskUser.setTaskId(taskId);
                    taskUser.setUserId(Long.parseLong(shareUserId));
                    taskUser.setUserType(4);
                    taskUserList.add(taskUser);
                }
            }
            if (StringUtils.isNotEmpty(leaderIds)) {
                String[] leaders = leaderIds.split(",");
                for (String leaderId : leaders) {
                    TaskUser taskUser = new TaskUser();
                    taskUser.setTaskId(taskId);
                    taskUser.setUserId(Long.parseLong(leaderId));
                    taskUser.setUserType(5);
                    taskUserList.add(taskUser);
                }
            }
            taskUserService.insertTaskUserBatch(taskUserList);
        }

        return toAjax(count);
    }

    @GetMapping("/detail/{oaTaskId}")
    public String detail(@PathVariable("oaTaskId") Long oaTaskId, ModelMap mmap)
    {
        List<User> executorIdList = new ArrayList<>();
        List<User> appraiserIdList = new ArrayList<>();
        Task task = taskService.selectTaskById(oaTaskId);
        User user = new User();
        user.setDeptId(task.getDeptId());
        List<User> userList = userService.selectJuniorUserByUser(user);
        // 负责人
        TaskUser taskUser1 = new TaskUser();
        taskUser1.setTaskId(oaTaskId);
        taskUser1.setUserType(1);
        List<Long> taskUserIdList = taskUserService.selectTaskUserIdList(taskUser1);
        for (User u : userList) {
            User us = new User();
            us.setUserId(u.getUserId());
            us.setUserName(u.getUserName());
            if (taskUserIdList.contains(u.getUserId())) {
                us.setFlag(true);
            }
            executorIdList.add(us);
        }
        // 评价人
        TaskUser taskUser2 = new TaskUser();
        taskUser2.setTaskId(oaTaskId);
        taskUser2.setUserType(2);
        List<Long> taskUserList2 = taskUserService.selectTaskUserIdList(taskUser2);
        for (User u : userList) {
            User us = new User();
            us.setUserId(u.getUserId());
            us.setUserName(u.getUserName());
            if (taskUserList2.contains(u.getUserId())) {
                us.setFlag(true);
            }
            appraiserIdList.add(us);
        }
        mmap.put("task", task);
        mmap.put("executorIdList", executorIdList);
        mmap.put("appraiserIdList", appraiserIdList);
        return prefix + "/detail";
    }

    /**
     * 修改任务督办
     */
    @GetMapping("/edit/{oaTaskId}")
    public String edit(@PathVariable("oaTaskId") Long oaTaskId, ModelMap mmap)
    {
        List<User> executorIdList = new ArrayList<>();
        List<User> appraiserIdList = new ArrayList<>();
        List<User> partUserIdList = new ArrayList<>();
        List<User> shareUserIdList = new ArrayList<>();
        List<User> leaderIdList = new ArrayList<>();
        Task task = taskService.selectTaskById(oaTaskId);
        User user = new User();
        user.setDeptId(task.getDeptId());
        List<User> userList = userService.selectJuniorUserByUser(user);
        List<User> leaderList = userService.selectAllUserByRoleId(3);
        // 负责人
        TaskUser taskUser1 = new TaskUser();
        taskUser1.setTaskId(oaTaskId);
        taskUser1.setUserType(1);
        List<Long> taskUserIdList = taskUserService.selectTaskUserIdList(taskUser1);
        for (User u : userList) {
            User us = new User();
            us.setUserId(u.getUserId());
            us.setUserName(u.getUserName());
            if (taskUserIdList.contains(u.getUserId())) {
                us.setFlag(true);
            }
            executorIdList.add(us);
        }
        // 评价人
        TaskUser taskUser2 = new TaskUser();
        taskUser2.setTaskId(oaTaskId);
        taskUser2.setUserType(2);
        List<Long> taskUserList2 = taskUserService.selectTaskUserIdList(taskUser2);
        for (User u : userList) {
            User us = new User();
            us.setUserId(u.getUserId());
            us.setUserName(u.getUserName());
            if (taskUserList2.contains(u.getUserId())) {
                us.setFlag(true);
            }
            appraiserIdList.add(us);
        }
        // 参与人
        TaskUser taskUser3 = new TaskUser();
        taskUser3.setTaskId(oaTaskId);
        taskUser3.setUserType(3);
        List<Long> taskUserList3 = taskUserService.selectTaskUserIdList(taskUser3);
        for (User u : userList) {
            User us = new User();
            us.setUserId(u.getUserId());
            us.setUserName(u.getUserName());
            if (taskUserList3.contains(u.getUserId())) {
                us.setFlag(true);
            }
            partUserIdList.add(us);
        }
        // 共享人
        TaskUser taskUser4 = new TaskUser();
        taskUser4.setTaskId(oaTaskId);
        taskUser4.setUserType(4);
        List<Long> taskUserList4 = taskUserService.selectTaskUserIdList(taskUser4);
        for (User u : userList) {
            User us = new User();
            us.setUserId(u.getUserId());
            us.setUserName(u.getUserName());
            if (taskUserList4.contains(u.getUserId())) {
                us.setFlag(true);
            }
            shareUserIdList.add(us);
        }
        // leader
        TaskUser taskUser5 = new TaskUser();
        taskUser5.setTaskId(oaTaskId);
        taskUser5.setUserType(5);
        List<Long> taskUserList5 = taskUserService.selectTaskUserIdList(taskUser5);
        for (User u : leaderList) {
            User us = new User();
            us.setUserId(u.getUserId());
            us.setUserName(u.getUserName());
            if (taskUserList5.contains(u.getUserId())) {
                us.setFlag(true);
            }
            leaderIdList.add(us);
        }
        mmap.put("task", task);
        mmap.put("executorIdList", executorIdList);
        mmap.put("appraiserIdList", appraiserIdList);
        mmap.put("partUserIdList", partUserIdList);
        mmap.put("shareUserIdList", shareUserIdList);
        mmap.put("leaderIdList", leaderIdList);
        return prefix + "/edit";
    }

    /**
     * 修改保存任务督办
     */
    @RequiresPermissions("oa:task:edit")
    @Log(title = "任务督办", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Task task)
    {
        return toAjax(taskService.updateTask(task));
    }

    /**
     * 删除任务督办
     */
    @RequiresPermissions("oa:task:remove")
    @Log(title = "任务督办", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(taskService.deleteTaskByIds(ids));
    }


    @GetMapping("/ajaxSelectTaskByUserIdForMain")
    @ResponseBody
    public Object ajaxSelectTaskByUserIdForMain(@RequestParam(value="userId", required = false) Long userId) {
        Task taskO = new Task();
        if (null != userId){
            taskO.setUserId(userId);
        }
        List<Task> taskList = taskMapper.selectTaskByUserIdForMain(taskO);
        Task taskResult = new Task();
        for (Task task : taskList) {
            int searchType = task.getSearchType();
            int num = task.getNum();
            switch (searchType) {
                case 0: // 总数
                    taskResult.setTask_totalNum(num);
                    break;
                case 1: // 进行中
                    taskResult.setTask_proceedNum(num);
                    break;
                case 2: // 待评价
                    taskResult.setTask_preparedEvaluatingNum(num);
                    break;
                case 3: // 评价未通过
                    taskResult.setTask_failedNum(num);
                    break;
                case 4: // 已完结
                    taskResult.setTask_finishNum(num);
                    break;
                case 5: // 已终结
                    taskResult.setTask_overNum(num);
                    break;
                case 6: // 超期
                    taskResult.setTask_expiredNum(num);
                    break;
            }
        }
        return AjaxResult.success(taskResult);
    }

    @GetMapping("/ajaxSelectSourceType")
    @ResponseBody
    public List<String> ajaxSelectSourceType() {
        List<String> sourceList = taskMapper.ajaxSelectSourceType("oa_source_type");
        return sourceList;
    }

}
