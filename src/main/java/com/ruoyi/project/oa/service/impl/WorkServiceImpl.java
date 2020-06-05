package com.ruoyi.project.oa.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.project.oa.domain.Work;
import com.ruoyi.project.oa.domain.WorkUser;
import com.ruoyi.project.oa.mapper.WorkMapper;
import com.ruoyi.project.oa.service.IWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 工作汇报Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-04-03
 */
@Service
public class WorkServiceImpl implements IWorkService
{
    @Autowired
    private WorkMapper workMapper;

    /**
     * 查询工作汇报
     * 
     * @param oaWorkId 工作汇报ID
     * @return 工作汇报
     */
    @Override
    public Work selectWorkById(Long oaWorkId)
    {
        return workMapper.selectWorkById(oaWorkId);
    }

    /**
     * 查询工作汇报列表
     * 
     * @param work 工作汇报
     * @return 工作汇报
     */
    @Override
    public List<Work> selectWorkList(Work work)
    {
        return workMapper.selectWorkList(work);
    }

    /**
     * 新增工作汇报
     * 
     * @param work 工作汇报
     * @return 结果
     */
    @Override
    public int insertWork(Work work)
    {
        work.setCreateTime(DateUtils.getNowDate());
        return workMapper.insertWork(work);
    }

    /**
     * 新增工作汇报用户
     * @param workUser
     * @return
     */
    @Override
    public int insertWorkUser(WorkUser workUser) {
        return workMapper.insertWorkUser(workUser);
    }

    /**
     * 修改工作汇报
     * 
     * @param work 工作汇报
     * @return 结果
     */
    @Override
    public int updateWork(Work work)
    {
        work.setUpdateTime(DateUtils.getNowDate());
        return workMapper.updateWork(work);
    }

    /**
     * 删除工作汇报对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWorkByIds(String ids)
    {
        return workMapper.deleteWorkByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除工作汇报信息
     * 
     * @param oaWorkId 工作汇报ID
     * @return 结果
     */
    @Override
    public int deleteWorkById(Long oaWorkId)
    {
        return workMapper.deleteWorkById(oaWorkId);
    }

    /**
     * 查询所有回复内容
     * @return
     */
    public List<Work> selectWorkReplyContentList(Work work) {
        return workMapper.selectWorkReplyContentList(work);
    }
}
