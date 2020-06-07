package com.ruoyi.project.oa.mapper;

import com.ruoyi.project.oa.domain.Task1Reply;

import java.util.List;

/**
 * 任务督办汇报人Mapper接口
 * 
 * @author ruoyi
 * @date 2020-04-19
 */
public interface Task1ReplyMapper
{
    /**
     * 查询任务督办汇报人
     * 
     * @param oaTaskReplyId 任务督办汇报人ID
     * @return 任务督办汇报人
     */
    public Task1Reply selectTask1ReplyById(Long oaTaskReplyId);

    /**
     * 查询任务督办汇报人列表
     * 
     * @param taskReply 任务督办汇报人
     * @return 任务督办汇报人集合
     */
    public List<Task1Reply> selectTask1ReplyList(Task1Reply taskReply);

    /**
     * 新增任务督办汇报人
     * 
     * @param taskReply 任务督办汇报人
     * @return 结果
     */
    public int insertTask1Reply(Task1Reply taskReply);

    /**
     * 修改任务督办汇报人
     * 
     * @param taskReply 任务督办汇报人
     * @return 结果
     */
    public int updateTask1Reply(Task1Reply taskReply);

    /**
     * 删除任务督办汇报人
     * 
     * @param oaTaskReplyId 任务督办汇报人ID
     * @return 结果
     */
    public int deleteTask1ReplyById(Long oaTaskReplyId);

    /**
     * 批量删除任务督办汇报人
     * 
     * @param oaTaskReplyIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteTask1ReplyByIds(String[] oaTaskReplyIds);
}
