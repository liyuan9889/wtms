package com.ruoyi.project.oa.service;

import com.ruoyi.project.oa.domain.Task1Comment;

import java.util.List;

/**
 * 任务评价Service接口
 * 
 * @author ruoyi
 * @date 2020-04-15
 */
public interface ITask1CommentService
{
    /**
     * 查询任务评价
     * 
     * @param oaTaskCommentId 任务评价ID
     * @return 任务评价
     */
    public Task1Comment selectTaskCommentById(Long oaTaskCommentId);

    /**
     * 查询任务评价列表
     * 
     * @param taskComment 任务评价
     * @return 任务评价集合
     */
    public List<Task1Comment> selectTaskCommentList(Task1Comment taskComment);

    public List<Task1Comment> searchCommentListByTaskId(Task1Comment taskComment);

    /**
     * 新增任务评价
     * 
     * @param taskComment 任务评价
     * @return 结果
     */
    public int insertTaskComment(Task1Comment taskComment);

    /**
     * 修改任务评价
     * 
     * @param taskComment 任务评价
     * @return 结果
     */
    public int updateTaskComment(Task1Comment taskComment);

    /**
     * 批量删除任务评价
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTaskCommentByIds(String ids);

    /**
     * 删除任务评价信息
     * 
     * @param oaTaskCommentId 任务评价ID
     * @return 结果
     */
    public int deleteTaskCommentById(Long oaTaskCommentId);
}
