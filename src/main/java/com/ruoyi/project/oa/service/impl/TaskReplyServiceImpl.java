package com.ruoyi.project.oa.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.project.oa.domain.TaskReply;
import com.ruoyi.project.oa.mapper.TaskReplyMapper;
import com.ruoyi.project.oa.service.ITaskReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 任务督办汇报人Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-04-19
 */
@Service
public class TaskReplyServiceImpl implements ITaskReplyService
{
    @Autowired
    private TaskReplyMapper taskReplyMapper;

    /**
     * 查询任务督办汇报人
     * 
     * @param oaTaskReplyId 任务督办汇报人ID
     * @return 任务督办汇报人
     */
    @Override
    public TaskReply selectTaskReplyById(Long oaTaskReplyId)
    {
        return taskReplyMapper.selectTaskReplyById(oaTaskReplyId);
    }

    /**
     * 查询任务督办汇报人列表
     * 
     * @param taskReply 任务督办汇报人
     * @return 任务督办汇报人
     */
    @Override
    public List<TaskReply> selectTaskReplyList(TaskReply taskReply)
    {
        return taskReplyMapper.selectTaskReplyList(taskReply);
    }

    /**
     * 新增任务督办汇报人
     * 
     * @param taskReply 任务督办汇报人
     * @return 结果
     */
    @Override
    public int insertTaskReply(TaskReply taskReply)
    {
        taskReply.setCreateTime(DateUtils.getNowDate());
        return taskReplyMapper.insertTaskReply(taskReply);
    }

    /**
     * 修改任务督办汇报人
     * 
     * @param taskReply 任务督办汇报人
     * @return 结果
     */
    @Override
    public int updateTaskReply(TaskReply taskReply)
    {
        taskReply.setUpdateTime(DateUtils.getNowDate());
        return taskReplyMapper.updateTaskReply(taskReply);
    }

    /**
     * 删除任务督办汇报人对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTaskReplyByIds(String ids)
    {
        return taskReplyMapper.deleteTaskReplyByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除任务督办汇报人信息
     * 
     * @param oaTaskReplyId 任务督办汇报人ID
     * @return 结果
     */
    @Override
    public int deleteTaskReplyById(Long oaTaskReplyId)
    {
        return taskReplyMapper.deleteTaskReplyById(oaTaskReplyId);
    }
}
