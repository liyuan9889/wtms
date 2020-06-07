package com.ruoyi.project.oa.mapper;

import com.ruoyi.project.oa.domain.Task1Comment;
import java.util.List;

/**
 * 任务评价Mapper接口
 * 
 * @author ruoyi
 * @date 2020-04-15
 */
public interface Task1CommentMapper
{
    /**
     * 查询任务评价
     * 
     * @param oaTaskCommentId 任务评价ID
     * @return 任务评价
     */
    public Task1Comment selectTask1CommentById(Long oaTaskCommentId);

    /**
     * 查询任务评价列表
     * 
     * @param taskComment 任务评价
     * @return 任务评价集合
     */
    public List<Task1Comment> selectTask1CommentList(Task1Comment taskComment);

    public List<Task1Comment> searchCommentListByTask1Id(Task1Comment taskComment);

    /**
     * 新增任务评价
     * 
     * @param taskComment 任务评价
     * @return 结果
     */
    public int insertTask1Comment(Task1Comment taskComment);

    /**
     * 修改任务评价
     * 
     * @param taskComment 任务评价
     * @return 结果
     */
    public int updateTask1Comment(Task1Comment taskComment);

    /**
     * 删除任务评价
     * 
     * @param oaTaskCommentId 任务评价ID
     * @return 结果
     */
    public int deleteTask1CommentById(Long oaTaskCommentId);

    /**
     * 批量删除任务评价
     * 
     * @param oaTaskCommentIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteTask1CommentByIds(String[] oaTaskCommentIds);
}
