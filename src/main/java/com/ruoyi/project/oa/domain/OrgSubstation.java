package com.ruoyi.project.oa.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 oa_org_substation
 * 
 * @author ruoyi
 * @date 2020-04-12
 */
public class OrgSubstation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long oaOrgSubstationId;

    /** 组织ID */
    @Excel(name = "组织ID")
    private Long orgId;

    /** 变电站名称 */
    @Excel(name = "变电站名称")
    private String substationName;

    /** 电压等级 */
    @Excel(name = "电压等级")
    private String voltageLevel;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 备注 */
    @Excel(name = "备注")
    private String mark;

    /** 是否删除（0：正常；1：已删） */
    private Integer delFlag;

    public void setOaOrgSubstationId(Long oaOrgSubstationId) 
    {
        this.oaOrgSubstationId = oaOrgSubstationId;
    }

    public Long getOaOrgSubstationId() 
    {
        return oaOrgSubstationId;
    }
    public void setOrgId(Long orgId) 
    {
        this.orgId = orgId;
    }

    public Long getOrgId() 
    {
        return orgId;
    }
    public void setSubstationName(String substationName) 
    {
        this.substationName = substationName;
    }

    public String getSubstationName() 
    {
        return substationName;
    }
    public void setVoltageLevel(String voltageLevel) 
    {
        this.voltageLevel = voltageLevel;
    }

    public String getVoltageLevel() 
    {
        return voltageLevel;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setMark(String mark) 
    {
        this.mark = mark;
    }

    public String getMark() 
    {
        return mark;
    }
    public void setDelFlag(Integer delFlag) 
    {
        this.delFlag = delFlag;
    }

    public Integer getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("oaOrgSubstationId", getOaOrgSubstationId())
            .append("orgId", getOrgId())
            .append("substationName", getSubstationName())
            .append("voltageLevel", getVoltageLevel())
            .append("address", getAddress())
            .append("mark", getMark())
            .append("delFlag", getDelFlag())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
