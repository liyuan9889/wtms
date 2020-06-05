package com.ruoyi.project.oa.mapper;

import com.ruoyi.project.oa.domain.OrgSubstation;

import java.util.List;

/**
 * 变电站Mapper接口
 * 
 * @author ruoyi
 * @date 2020-04-12
 */
public interface OrgSubstationMapper 
{
    /**
     * 查询变电站
     * 
     * @param oaOrgSubstationId 变电站ID
     * @return 变电站
     */
    public OrgSubstation selectOrgSubstationById(Long oaOrgSubstationId);

    /**
     * 查询变电站列表
     * 
     * @param orgSubstation 变电站
     * @return 变电站集合
     */
    public List<OrgSubstation> selectOrgSubstationList(OrgSubstation orgSubstation);

    /**
     * 新增变电站
     * 
     * @param orgSubstation 变电站
     * @return 结果
     */
    public int insertOrgSubstation(OrgSubstation orgSubstation);

    /**
     * 修改变电站
     * 
     * @param orgSubstation 变电站
     * @return 结果
     */
    public int updateOrgSubstation(OrgSubstation orgSubstation);

    /**
     * 删除变电站
     * 
     * @param oaOrgSubstationId 变电站ID
     * @return 结果
     */
    public int deleteOrgSubstationById(Long oaOrgSubstationId);

    /**
     * 批量删除变电站（逻辑删除）
     * @param oaOrgSubstationIds
     * @return
     */
    public int updateOrgSubstationByIds(String[] oaOrgSubstationIds);

    /**
     * 批量删除变电站
     * 
     * @param oaOrgSubstationIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteOrgSubstationByIds(String[] oaOrgSubstationIds);
}
