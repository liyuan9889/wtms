package com.ruoyi.project.oa.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.framework.web.domain.Ztree;
import com.ruoyi.project.oa.domain.Org;
import com.ruoyi.project.oa.mapper.OrgMapper;
import com.ruoyi.project.oa.service.OrgService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrgServiceImpl implements OrgService {

    private final OrgMapper orgMapper;

    @Override
    public List<Ztree> selectOrgTree(Org org) {
        List<Org> orgList = orgMapper.selectOrgList(org);
        List<Ztree> ztrees = initZtree(orgList);
        return ztrees;
    }

    /**
     * 对象转组织树
     *
     * @param orgList 组织列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<Org> orgList)
    {
        return initZtree(orgList, null);
    }

    /**
     * 对象转组织树
     *
     * @param orgList 组织列表
     * @param roleOrgList 角色已存在菜单列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<Org> orgList, List<String> roleOrgList)
    {
        List<Ztree> ztrees = new ArrayList<Ztree>();
        boolean isCheck = StringUtils.isNotNull(roleOrgList);
        for (Org org : orgList)
        {
            Ztree ztree = new Ztree();
            ztree.setId(org.getOaOrgId());
            ztree.setpId(org.getParentId());
            ztree.setName(org.getOrgName());
            ztree.setTitle(org.getOrgName());
            if (isCheck)
            {
                ztree.setChecked(roleOrgList.contains(org.getOaOrgId() + org.getOrgName()));
            }
            ztrees.add(ztree);
        }
        return ztrees;
    }

    /**
     * 查询组织
     *
     * @param oaOrgId 组织ID
     * @return 组织
     */
    @Override
    public Org selectOrgById(Long oaOrgId)
    {
        return orgMapper.selectOrgById(oaOrgId);
    }

    @Override
    public Integer selectMaxOrderNum() {
        return orgMapper.selectMaxOrderNum();
    }

    /**
     * 查询组织列表
     *
     * @param org 组织
     * @return 组织
     */
    @Override
    public List<Org> selectOrgList(Org org)
    {
        return orgMapper.selectOrgList(org);
    }

    /**
     * 新增组织
     *
     * @param org 组织
     * @return 结果
     */
    @Override
    public int insertOrg(Org org)
    {
        org.setCreateTime(DateUtils.getNowDate());
        return orgMapper.insertOrg(org);
    }

    /**
     * 修改组织
     *
     * @param org 组织
     * @return 结果
     */
    @Override
    public int updateOrg(Org org)
    {
        org.setUpdateTime(DateUtils.getNowDate());
        return orgMapper.updateOrg(org);
    }

    /**
     * 删除组织对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteOrgByIds(String ids)
    {
        return orgMapper.deleteOrgByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除组织对象（逻辑删除）
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int updateOrgByIds(String ids) {
        return orgMapper.updateOrgByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除组织信息
     *
     * @param oaOrgId 组织ID
     * @return 结果
     */
    @Override
    public int deleteOrgById(Long oaOrgId)
    {
        return orgMapper.deleteOrgById(oaOrgId);
    }
    
}
