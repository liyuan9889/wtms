package com.ruoyi.project.oa.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.project.oa.domain.OrgSubstation;
import com.ruoyi.project.oa.mapper.OrgSubstationMapper;
import com.ruoyi.project.oa.service.IOrgSubstationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 变电站Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-04-12
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrgSubstationServiceImpl implements IOrgSubstationService
{
    private final OrgSubstationMapper orgSubstationMapper;

    /**
     * 查询变电站
     * 
     * @param oaOrgSubstationId 变电站ID
     * @return 变电站
     */
    @Override
    public OrgSubstation selectOrgSubstationById(Long oaOrgSubstationId)
    {
        return orgSubstationMapper.selectOrgSubstationById(oaOrgSubstationId);
    }

    /**
     * 查询变电站列表
     * 
     * @param orgSubstation 变电站
     * @return 变电站
     */
    @Override
    public List<OrgSubstation> selectOrgSubstationList(OrgSubstation orgSubstation)
    {
        return orgSubstationMapper.selectOrgSubstationList(orgSubstation);
    }

    /**
     * 新增变电站
     * 
     * @param orgSubstation 变电站
     * @return 结果
     */
    @Override
    public int insertOrgSubstation(OrgSubstation orgSubstation)
    {
        orgSubstation.setCreateTime(DateUtils.getNowDate());
        return orgSubstationMapper.insertOrgSubstation(orgSubstation);
    }

    /**
     * 修改变电站
     * 
     * @param orgSubstation 变电站
     * @return 结果
     */
    @Override
    public int updateOrgSubstation(OrgSubstation orgSubstation)
    {
        orgSubstation.setUpdateTime(DateUtils.getNowDate());
        return orgSubstationMapper.updateOrgSubstation(orgSubstation);
    }

    /**
     * 删除变电站对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteOrgSubstationByIds(String ids)
    {
        return orgSubstationMapper.deleteOrgSubstationByIds(Convert.toStrArray(ids));
    }

    /**
     * 批量删除变电站（逻辑删除）
     * @param ids
     * @return
     */
    public int updateOrgSubstationByIds(String ids) {
        return orgSubstationMapper.updateOrgSubstationByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除变电站信息
     * 
     * @param oaOrgSubstationId 变电站ID
     * @return 结果
     */
    @Override
    public int deleteOrgSubstationById(Long oaOrgSubstationId)
    {
        return orgSubstationMapper.deleteOrgSubstationById(oaOrgSubstationId);
    }
}
