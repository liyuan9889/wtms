package com.ruoyi.project.oa.controller;

import java.util.List;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.oa.domain.Forum;
import com.ruoyi.project.oa.service.IForumService;
import com.ruoyi.project.system.user.domain.User;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 论坛Controller
 * 
 * @author ruoyi
 * @date 2020-04-18
 */
@Controller
@RequestMapping("/oa/forum")
public class ForumController extends BaseController
{
    private String prefix = "oa/forum";

    @Autowired
    private IForumService forumService;

    @RequiresPermissions("oa:forum:view")
    @GetMapping()
    public String forum()
    {
        return prefix + "/forum";
    }

    /**
     * 查询论坛列表
     */
    @RequiresPermissions("oa:forum:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Forum forum)
    {
        startPage();
        List<Forum> list = forumService.selectForumList(forum);
        return getDataTable(list);
    }

    /**
     * 查询所有论坛列表
     */
    @PostMapping("/listAll")
    @ResponseBody
    public  List<Forum>  listAll(Forum forum)
    {
        List<Forum> list = forumService.selectForumList(forum);
        return list;
    }

    /**
     * 导出论坛列表
     */
    @RequiresPermissions("oa:forum:export")
    @Log(title = "论坛", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Forum forum)
    {
        List<Forum> list = forumService.selectForumList(forum);
        ExcelUtil<Forum> util = new ExcelUtil<Forum>(Forum.class);
        return util.exportExcel(list, "forum");
    }

    /**
     * 新增论坛
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存论坛
     */
    @RequiresPermissions("oa:forum:add")
    @Log(title = "论坛", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Forum forum)
    {
        return toAjax(forumService.insertForum(forum));
    }

    /**
     * 修改论坛
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Forum forum = forumService.selectForumById(id);
        mmap.put("forum", forum);
        return prefix + "/edit";
    }

    /**
     * 修改保存论坛
     */
    @RequiresPermissions("oa:forum:edit")
    @Log(title = "论坛", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Forum forum)
    {
        return toAjax(forumService.updateForum(forum));
    }

    /**
     * 删除论坛
     */
    @RequiresPermissions("oa:forum:remove")
    @Log(title = "论坛", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(forumService.deleteForumByIds(ids));
    }


    /**
     * 校验用户名
     */
    @PostMapping("/checkForumByTitle")
    @ResponseBody
    public String checkForumByTitle(Forum forum)
    {
        return forumService.checkForumByTitle(forum.getTitle());
    }
    /**
     * 校验用户名
     */
    @PostMapping("/checkForumUpdateByTitle")
    @ResponseBody
    public String checkForumUpdateByTitle(Forum forum)
    {
        return forumService.checkForumUpdateByTitle(forum.getTitle());
    }
}
