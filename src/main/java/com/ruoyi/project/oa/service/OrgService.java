package com.ruoyi.project.oa.service;

import com.ruoyi.framework.web.domain.Ztree;
import com.ruoyi.project.oa.domain.Org;
import java.util.List;

public interface OrgService {

    List<Ztree> selectOrgTree(Org org);

    /**
     * 查询组织
     *
     * @param oaOrgId 组织ID
     * @return 组织
     */
    public Org selectOrgById(Long oaOrgId);

    public Integer selectMaxOrderNum();

    /**
     * 查询组织列表
     *
     * @param org 组织
     * @return 组织集合
     */
    public List<Org> selectOrgList(Org org);

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
     * 批量删除组织
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOrgByIds(String ids);

    /**
     * 批量删除组织（逻辑删除）
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int updateOrgByIds(String ids);

    /**
     * 删除组织信息
     *
     * @param oaOrgId 组织ID
     * @return 结果
     */
    public int deleteOrgById(Long oaOrgId);

}
