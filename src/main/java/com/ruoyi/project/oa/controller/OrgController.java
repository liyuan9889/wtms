package com.ruoyi.project.oa.controller;

import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.domain.Ztree;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.oa.domain.Org;
import com.ruoyi.project.oa.service.OrgService;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/oa/org")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrgController extends BaseController {

    private String prefix = "oa/org";

    private final OrgService orgService;

    @GetMapping("")
    public String org() {
        return prefix + "/org";
    }

    @GetMapping("/orgView")
    public String orgView() {
        return prefix + "/orgView";
    }

    /**
     * 查询组织列表
     */
    @RequiresPermissions("oa:org:list")
    @PostMapping("/list")
    @ResponseBody
    public List<Org> list(Org org)
    {
        List<Org> list = orgService.selectOrgList(org);
        return list;
    }

    /**
     * 新增组织
     */
    @GetMapping("/add")
    public String add(ModelMap modelMap)
    {
        int maxOrderNum = orgService.selectMaxOrderNum();
        modelMap.put("maxOrderNum", ++maxOrderNum);
        return prefix + "/add";
    }

    /**
     * 新增保存组织
     */
    @RequiresPermissions("oa:org:add")
    @Log(title = "组织", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Org org)
    {
        org.setParentId(Long.valueOf(100));
        org.setAncestors("0,100");
        return toAjax(orgService.insertOrg(org));
    }

    /**
     * 修改组织
     */
    @GetMapping("/edit/{oaOrgId}")
    public String edit(@PathVariable("oaOrgId") Long oaOrgId, ModelMap mmap)
    {
        Org org = orgService.selectOrgById(oaOrgId);
        mmap.put("org", org);
        return prefix + "/edit";
    }

    /**
     * 修改保存组织
     */
    @RequiresPermissions("oa:org:edit")
    @Log(title = "组织", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Org org)
    {
        org.setParentId(Long.valueOf(100));
        org.setAncestors("0,100");
        return toAjax(orgService.updateOrg(org));
    }

    /**
     * 删除组织
     */
    @RequiresPermissions("oa:org:remove")
    @Log(title = "组织", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(orgService.updateOrgByIds(ids));
    }

    /**
     * 加载部门列表树
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData()
    {
        List<Ztree> ztrees = orgService.selectOrgTree(new Org());
        return ztrees;
    }

}
