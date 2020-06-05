package com.ruoyi.project.oa.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.oa.mapper.TaskCommentMapper;
import com.ruoyi.project.oa.domain.TaskComment;
import com.ruoyi.project.oa.service.ITaskCommentService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 任务评价Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-04-15
 */
@Service
public class TaskCommentServiceImpl implements ITaskCommentService 
{
    @Autowired
    private TaskCommentMapper taskCommentMapper;

    /**
     * 查询任务评价
     * 
     * @param oaTaskCommentId 任务评价ID
     * @return 任务评价
     */
    @Override
    public TaskComment selectTaskCommentById(Long oaTaskCommentId)
    {
        return taskCommentMapper.selectTaskCommentById(oaTaskCommentId);
    }

    /**
     * 查询任务评价列表
     * 
     * @param taskComment 任务评价
     * @return 任务评价
     */
    @Override
    public List<TaskComment> selectTaskCommentList(TaskComment taskComment)
    {
        return taskCommentMapper.selectTaskCommentList(taskComment);
    }

    @Override
    public List<TaskComment> searchCommentListByTaskId(TaskComment taskComment)
    {
        return taskCommentMapper.searchCommentListByTaskId(taskComment);
    }

    /**
     * 新增任务评价
     * 
     * @param taskComment 任务评价
     * @return 结果
     */
    @Override
    public int insertTaskComment(TaskComment taskComment)
    {
        taskComment.setCreateTime(DateUtils.getNowDate());
        return taskCommentMapper.insertTaskComment(taskComment);
    }

    /**
     * 修改任务评价
     * 
     * @param taskComment 任务评价
     * @return 结果
     */
    @Override
    public int updateTaskComment(TaskComment taskComment)
    {
        taskComment.setUpdateTime(DateUtils.getNowDate());
        return taskCommentMapper.updateTaskComment(taskComment);
    }

    /**
     * 删除任务评价对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTaskCommentByIds(String ids)
    {
        return taskCommentMapper.deleteTaskCommentByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除任务评价信息
     * 
     * @param oaTaskCommentId 任务评价ID
     * @return 结果
     */
    @Override
    public int deleteTaskCommentById(Long oaTaskCommentId)
    {
        return taskCommentMapper.deleteTaskCommentById(oaTaskCommentId);
    }
}
