package com.ruoyi.project.oa.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.project.oa.domain.Task1Comment;
import com.ruoyi.project.oa.mapper.Task1CommentMapper;
import com.ruoyi.project.oa.service.ITask1CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 任务评价Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-04-15
 */
@Service
public class Task1CommentServiceImpl implements ITask1CommentService
{
    @Autowired
    private Task1CommentMapper taskCommentMapper;

    /**
     * 查询任务评价
     * 
     * @param oaTaskCommentId 任务评价ID
     * @return 任务评价
     */
    @Override
    public Task1Comment selectTaskCommentById(Long oaTaskCommentId)
    {
        return taskCommentMapper.selectTask1CommentById(oaTaskCommentId);
    }

    /**
     * 查询任务评价列表
     * 
     * @param taskComment 任务评价
     * @return 任务评价
     */
    @Override
    public List<Task1Comment> selectTaskCommentList(Task1Comment taskComment)
    {
        return taskCommentMapper.selectTask1CommentList(taskComment);
    }

    @Override
    public List<Task1Comment> searchCommentListByTaskId(Task1Comment taskComment)
    {
        return taskCommentMapper.searchCommentListByTask1Id(taskComment);
    }

    /**
     * 新增任务评价
     * 
     * @param taskComment 任务评价
     * @return 结果
     */
    @Override
    public int insertTaskComment(Task1Comment taskComment)
    {
        taskComment.setCreateTime(DateUtils.getNowDate());
        return taskCommentMapper.insertTask1Comment(taskComment);
    }

    /**
     * 修改任务评价
     * 
     * @param taskComment 任务评价
     * @return 结果
     */
    @Override
    public int updateTaskComment(Task1Comment taskComment)
    {
        taskComment.setUpdateTime(DateUtils.getNowDate());
        return taskCommentMapper.updateTask1Comment(taskComment);
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
        return taskCommentMapper.deleteTask1CommentByIds(Convert.toStrArray(ids));
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
        return taskCommentMapper.deleteTask1CommentById(oaTaskCommentId);
    }
}
