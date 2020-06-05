package com.ruoyi.project.oa.controller;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.oa.domain.OrgSubstation;
import com.ruoyi.project.oa.service.IOrgSubstationService;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 变电站Controller
 * 
 * @author ruoyi
 * @date 2020-04-12
 */
@Controller
@RequestMapping("/oa/substation")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrgSubstationController extends BaseController
{
    private String prefix = "oa/substation";

    private final IOrgSubstationService orgSubstationService;

    @RequiresPermissions("oa:substation:view")
    @GetMapping()
    public String substation()
    {
        return prefix + "/substation";
    }

    /**
     * 查询变电站列表
     */
    @RequiresPermissions("oa:substation:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OrgSubstation orgSubstation)
    {
        startPage();
        if (orgSubstation.getOrgId() !=null && orgSubstation.getOrgId() == 100){
            orgSubstation.setOrgId(null);
        }
        List<OrgSubstation> list = orgSubstationService.selectOrgSubstationList(orgSubstation);
        return getDataTable(list);
    }

    /**
     * 导出变电站列表
     */
    @RequiresPermissions("oa:substation:export")
    @Log(title = "变电站", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OrgSubstation orgSubstation)
    {
        List<OrgSubstation> list = orgSubstationService.selectOrgSubstationList(orgSubstation);
        ExcelUtil<OrgSubstation> util = new ExcelUtil<OrgSubstation>(OrgSubstation.class);
        return util.exportExcel(list, "substation");
    }

    /**
     * 新增变电站
     */
    @GetMapping("/add/{orgId}")
    public String add(@PathVariable("orgId") Long orgId, ModelMap modelMap)
    {
        modelMap.put("orgId", orgId);
        return prefix + "/add";
    }

    /**
     * 新增保存变电站
     */
    @RequiresPermissions("oa:substation:add")
    @Log(title = "变电站", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(OrgSubstation orgSubstation)
    {
        return toAjax(orgSubstationService.insertOrgSubstation(orgSubstation));
    }

    /**
     * 修改变电站
     */
    @GetMapping("/edit/{oaOrgSubstationId}")
    public String edit(@PathVariable("oaOrgSubstationId") Long oaOrgSubstationId, ModelMap mmap)
    {
        OrgSubstation orgSubstation = orgSubstationService.selectOrgSubstationById(oaOrgSubstationId);
        mmap.put("orgSubstation", orgSubstation);
        return prefix + "/edit";
    }

    /**
     * 修改保存变电站
     */
    @RequiresPermissions("oa:substation:edit")
    @Log(title = "变电站", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OrgSubstation orgSubstation)
    {
        return toAjax(orgSubstationService.updateOrgSubstation(orgSubstation));
    }

    /**
     * 删除变电站
     */
    @RequiresPermissions("oa:substation:remove")
    @Log(title = "变电站", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(orgSubstationService.updateOrgSubstationByIds(ids));
    }
}
