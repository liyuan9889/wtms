package com.ruoyi.project.oa.mapper;

import com.ruoyi.project.oa.domain.Org;

import java.util.List;

public interface OrgMapper {

    public List<Org> selectOrgList(Org org);

    /**
     * 查询组织
     *
     * @param oaOrgId 组织ID
     * @return 组织
     */
    public Org selectOrgById(Long oaOrgId);

    /**
     * 新增组织
     *
     * @param org 组织
     * @return 结果
     */
    public int insertOrg(Org org);

    /**
     * 修改组织
     *
     * @param org 组织
     * @return 结果
     */
    public int updateOrg(Org org);

    /**
     * 删除组织
     *
     * @param oaOrgId 组织ID
     * @return 结果
     */
    public int deleteOrgById(Long oaOrgId);

    /**
     * 批量删除组织
     *
     * @param oaOrgIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteOrgByIds(String[] oaOrgIds);

    public Integer selectMaxOrderNum();

    public int updateOrgByIds(String[] toStrArray);
}
