package com.ruoyi.project.oa.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.project.oa.domain.Task1Reply;
import com.ruoyi.project.oa.mapper.Task1ReplyMapper;
import com.ruoyi.project.oa.service.ITask1ReplyService;
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
public class Task1ReplyServiceImpl implements ITask1ReplyService
{
    @Autowired
    private Task1ReplyMapper taskReplyMapper;

    /**
     * 查询任务督办汇报人
     * 
     * @param oaTaskReplyId 任务督办汇报人ID
     * @return 任务督办汇报人
     */
    @Override
    public Task1Reply selectTaskReplyById(Long oaTaskReplyId)
    {
        return taskReplyMapper.selectTask1ReplyById(oaTaskReplyId);
    }

    /**
     * 查询任务督办汇报人列表
     * 
     * @param taskReply 任务督办汇报人
     * @return 任务督办汇报人
     */
    @Override
    public List<Task1Reply> selectTaskReplyList(Task1Reply taskReply)
    {
        return taskReplyMapper.selectTask1ReplyList(taskReply);
    }

    /**
     * 新增任务督办汇报人
     * 
     * @param taskReply 任务督办汇报人
     * @return 结果
     */
    @Override
    public int insertTaskReply(Task1Reply taskReply)
    {
        taskReply.setCreateTime(DateUtils.getNowDate());
        return taskReplyMapper.insertTask1Reply(taskReply);
    }

    /**
     * 修改任务督办汇报人
     * 
     * @param taskReply 任务督办汇报人
     * @return 结果
     */
    @Override
    public int updateTaskReply(Task1Reply taskReply)
    {
        taskReply.setUpdateTime(DateUtils.getNowDate());
        return taskReplyMapper.updateTask1Reply(taskReply);
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
        return taskReplyMapper.deleteTask1ReplyByIds(Convert.toStrArray(ids));
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
        return taskReplyMapper.deleteTask1ReplyById(oaTaskReplyId);
    }
}
