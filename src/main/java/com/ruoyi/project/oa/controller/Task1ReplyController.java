package com.ruoyi.project.oa.controller;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.oa.domain.TaskReply;
import com.ruoyi.project.oa.service.ITaskReplyService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 任务督办汇报人Controller
 * 
 * @author ruoyi
 * @date 2020-04-19
 */
@Controller
@RequestMapping("/oa/reply1")
public class Task1ReplyController extends BaseController
{
    private String prefix = "oa/reply1";

    @Autowired
    private ITaskReplyService taskReplyService;

    @RequiresPermissions("oa:reply:view")
    @GetMapping()
    public String reply()
    {
        return prefix + "/reply";
    }

    /**
     * 查询任务督办汇报人列表
     */
    @RequiresPermissions("oa:reply:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TaskReply taskReply)
    {
        startPage();
        List<TaskReply> list = taskReplyService.selectTaskReplyList(taskReply);
        return getDataTable(list);
    }

    /**
     * 导出任务督办汇报人列表
     */
    @RequiresPermissions("oa:reply:export")
    @Log(title = "任务督办汇报人", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TaskReply taskReply)
    {
        List<TaskReply> list = taskReplyService.selectTaskReplyList(taskReply);
        ExcelUtil<TaskReply> util = new ExcelUtil<TaskReply>(TaskReply.class);
        return util.exportExcel(list, "reply");
    }

    /**
     * 新增任务督办汇报人
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存任务督办汇报人
     */
    @RequiresPermissions("oa:reply:add")
    @Log(title = "任务督办汇报人", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TaskReply taskReply)
    {
        return toAjax(taskReplyService.insertTaskReply(taskReply));
    }

    /**
     * 修改任务督办汇报人
     */
    @GetMapping("/edit/{oaTaskReplyId}")
    public String edit(@PathVariable("oaTaskReplyId") Long oaTaskReplyId, ModelMap mmap)
    {
        TaskReply taskReply = taskReplyService.selectTaskReplyById(oaTaskReplyId);
        mmap.put("taskReply", taskReply);
        return prefix + "/edit";
    }

    /**
     * 修改保存任务督办汇报人
     */
    @RequiresPermissions("oa:reply:edit")
    @Log(title = "任务督办汇报人", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TaskReply taskReply)
    {
        return toAjax(taskReplyService.updateTaskReply(taskReply));
    }

    /**
     * 删除任务督办汇报人
     */
    @RequiresPermissions("oa:reply:remove")
    @Log(title = "任务督办汇报人", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(taskReplyService.deleteTaskReplyByIds(ids));
    }
}
