package com.ruoyi.project.oa.service;

import com.ruoyi.project.oa.domain.Task1Reply;

import java.util.List;

/**
 * 任务督办汇报人Service接口
 * 
 * @author ruoyi
 * @date 2020-04-19
 */
public interface ITask1ReplyService
{
    /**
     * 查询任务督办汇报人
     * 
     * @param oaTaskReplyId 任务督办汇报人ID
     * @return 任务督办汇报人
     */
    public Task1Reply selectTaskReplyById(Long oaTaskReplyId);

    /**
     * 查询任务督办汇报人列表
     * 
     * @param taskReply 任务督办汇报人
     * @return 任务督办汇报人集合
     */
    public List<Task1Reply> selectTaskReplyList(Task1Reply taskReply);

    /**
     * 新增任务督办汇报人
     * 
     * @param taskReply 任务督办汇报人
     * @return 结果
     */
    public int insertTaskReply(Task1Reply taskReply);

    /**
     * 修改任务督办汇报人
     * 
     * @param taskReply 任务督办汇报人
     * @return 结果
     */
    public int updateTaskReply(Task1Reply taskReply);

    /**
     * 批量删除任务督办汇报人
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTaskReplyByIds(String ids);

    /**
     * 删除任务督办汇报人信息
     * 
     * @param oaTaskReplyId 任务督办汇报人ID
     * @return 结果
     */
    public int deleteTaskReplyById(Long oaTaskReplyId);

}
