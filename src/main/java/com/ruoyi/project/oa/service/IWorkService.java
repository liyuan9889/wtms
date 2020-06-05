package com.ruoyi.project.oa.service;

import com.ruoyi.project.oa.domain.Work;
import com.ruoyi.project.oa.domain.WorkUser;

import java.util.List;

/**
 * 工作汇报Service接口
 * 
 * @author ruoyi
 * @date 2020-04-03
 */
public interface IWorkService 
{
    /**
     * 查询工作汇报
     * 
     * @param oaWorkId 工作汇报ID
     * @return 工作汇报
     */
    public Work selectWorkById(Long oaWorkId);

    /**
     * 查询工作汇报列表
     * 
     * @param work 工作汇报
     * @return 工作汇报集合
     */
    public List<Work> selectWorkList(Work work);

    /**
     * 新增工作汇报
     * 
     * @param work 工作汇报
     * @return 结果
     */
    public int insertWork(Work work);

    /**
     * 新增工作汇报用户
     * @param workUser
     * @return
     */
    public int insertWorkUser(WorkUser workUser);

    /**
     * 修改工作汇报
     * 
     * @param work 工作汇报
     * @return 结果
     */
    public int updateWork(Work work);

    /**
     * 批量删除工作汇报
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWorkByIds(String ids);

    /**
     * 删除工作汇报信息
     * 
     * @param oaWorkId 工作汇报ID
     * @return 结果
     */
    public int deleteWorkById(Long oaWorkId);

    /**
     * 查询所有回复内容
     * @return
     */
    public List<Work> selectWorkReplyContentList(Work work);

}
