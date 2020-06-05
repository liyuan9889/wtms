package com.ruoyi.project.oa.controller;

import java.util.List;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.oa.domain.Task;
import com.ruoyi.project.oa.domain.TaskUser;
import com.ruoyi.project.oa.service.ITaskReplyService;
import com.ruoyi.project.oa.service.ITaskService;
import com.ruoyi.project.oa.service.ITaskUserService;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.oa.domain.TaskComment;
import com.ruoyi.project.oa.service.ITaskCommentService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 任务评价Controller
 * 
 * @author ruoyi
 * @date 2020-04-15
 */
@Controller
@RequestMapping("/oa/comment")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TaskCommentController extends BaseController
{
    private String prefix = "oa/comment";

    private final ITaskCommentService taskCommentService;
    private final ITaskUserService taskUserService;
    private final ITaskService taskService;
    private final ITaskReplyService taskReplyService;

//    @RequiresPermissions("oa:comment:view")
    @GetMapping()
    public String comment(@RequestParam("taskId") Integer taskId, ModelMap modelMap)
    {
        modelMap.put("taskId", taskId);
        return prefix + "/comment";
    }

    /**
     * 查询任务评价列表
     */
//    @RequiresPermissions("oa:comment:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TaskComment taskComment)
    {
        startPage();
        List<TaskComment> list = taskCommentService.selectTaskCommentList(taskComment);
        return getDataTable(list);
    }

    @PostMapping("/toCommentList/{taskId}")
    @ResponseBody
    public List<TaskComment> toCommentList(@PathVariable("taskId") Long taskId) {
        TaskComment taskComment = new TaskComment();
        taskComment.setTaskId(taskId);
        List<TaskComment> list = taskCommentService.selectTaskCommentList(taskComment);
        return list;
    }

    /**
     * 导出任务评价列表
     */
    @RequiresPermissions("oa:comment:export")
    @Log(title = "任务评价", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TaskComment taskComment)
    {
        List<TaskComment> list = taskCommentService.selectTaskCommentList(taskComment);
        ExcelUtil<TaskComment> util = new ExcelUtil<TaskComment>(TaskComment.class);
        return util.exportExcel(list, "comment");
    }

    /**
     * 新增任务评价
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存任务评价
     */
    @RequiresPermissions("oa:comment:add")
    @Log(title = "任务评价", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TaskComment taskComment)
    {
        return toAjax(taskCommentService.insertTaskComment(taskComment));
    }


    @PostMapping("/ajaxSaveTaskComment")
    @ResponseBody
    public AjaxResult ajaxSaveTaskComment(@RequestParam("taskId") Long taskId,
                                          @RequestParam("star") Integer star,
                                          @RequestParam("isPass") Integer isPass,
                                          @RequestParam("reason") String reason)
    {
        TaskComment taskComment = new TaskComment();
        taskComment.setTaskId(taskId);
        taskComment.setStar(star);
        taskComment.setIsPass(isPass);
        if (StringUtils.isNotNull(reason)) {
            taskComment.setReason(reason);
        }
        taskComment.setUserId(getUserId());
        taskCommentService.insertTaskComment(taskComment);

        int schedule = taskService.selectTaskProgressByTaskId(taskId);
        if (schedule == 100){
            TaskUser taskUser = new TaskUser();
            taskUser.setTaskId(taskId);
            taskUser.setUserType(2);
            List<TaskUser> taskUserList = taskUserService.selectTaskUserList(taskUser);
            List<TaskComment> taskCommentList = taskCommentService.searchCommentListByTaskId(taskComment);
            if (taskUserList.size() == taskCommentList.size()){
                Task task = new Task();
                task.setOaTaskId(taskId);
                task.setStatus(4);
                taskService.updateTask(task);
            }
        }

        return toAjax(1);
    }

    /**
     * 修改任务评价
     */
    @GetMapping("/edit/{oaTaskCommentId}")
    public String edit(@PathVariable("oaTaskCommentId") Long oaTaskCommentId, ModelMap mmap)
    {
        TaskComment taskComment = taskCommentService.selectTaskCommentById(oaTaskCommentId);
        mmap.put("taskComment", taskComment);
        return prefix + "/edit";
    }

    /**
     * 修改保存任务评价
     */
    @RequiresPermissions("oa:comment:edit")
    @Log(title = "任务评价", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TaskComment taskComment)
    {
        return toAjax(taskCommentService.updateTaskComment(taskComment));
    }

    /**
     * 删除任务评价
     */
    @RequiresPermissions("oa:comment:remove")
    @Log(title = "任务评价", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(taskCommentService.deleteTaskCommentByIds(ids));
    }
}
