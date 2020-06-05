package com.ruoyi.project.oa.mapper;

import com.ruoyi.project.oa.domain.TaskReply;

import java.util.List;

/**
 * 任务督办汇报人Mapper接口
 * 
 * @author ruoyi
 * @date 2020-04-19
 */
public interface TaskReplyMapper 
{
    /**
     * 查询任务督办汇报人
     * 
     * @param oaTaskReplyId 任务督办汇报人ID
     * @return 任务督办汇报人
     */
    public TaskReply selectTaskReplyById(Long oaTaskReplyId);

    /**
     * 查询任务督办汇报人列表
     * 
     * @param taskReply 任务督办汇报人
     * @return 任务督办汇报人集合
     */
    public List<TaskReply> selectTaskReplyList(TaskReply taskReply);

    /**
     * 新增任务督办汇报人
     * 
     * @param taskReply 任务督办汇报人
     * @return 结果
     */
    public int insertTaskReply(TaskReply taskReply);

    /**
     * 修改任务督办汇报人
     * 
     * @param taskReply 任务督办汇报人
     * @return 结果
     */
    public int updateTaskReply(TaskReply taskReply);

    /**
     * 删除任务督办汇报人
     * 
     * @param oaTaskReplyId 任务督办汇报人ID
     * @return 结果
     */
    public int deleteTaskReplyById(Long oaTaskReplyId);

    /**
     * 批量删除任务督办汇报人
     * 
     * @param oaTaskReplyIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteTaskReplyByIds(String[] oaTaskReplyIds);
}
