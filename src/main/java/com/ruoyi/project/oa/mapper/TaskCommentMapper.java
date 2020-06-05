package com.ruoyi.project.oa.mapper;

import java.util.List;
import com.ruoyi.project.oa.domain.TaskComment;

/**
 * 任务评价Mapper接口
 * 
 * @author ruoyi
 * @date 2020-04-15
 */
public interface TaskCommentMapper 
{
    /**
     * 查询任务评价
     * 
     * @param oaTaskCommentId 任务评价ID
     * @return 任务评价
     */
    public TaskComment selectTaskCommentById(Long oaTaskCommentId);

    /**
     * 查询任务评价列表
     * 
     * @param taskComment 任务评价
     * @return 任务评价集合
     */
    public List<TaskComment> selectTaskCommentList(TaskComment taskComment);

    public List<TaskComment> searchCommentListByTaskId(TaskComment taskComment);

    /**
     * 新增任务评价
     * 
     * @param taskComment 任务评价
     * @return 结果
     */
    public int insertTaskComment(TaskComment taskComment);

    /**
     * 修改任务评价
     * 
     * @param taskComment 任务评价
     * @return 结果
     */
    public int updateTaskComment(TaskComment taskComment);

    /**
     * 删除任务评价
     * 
     * @param oaTaskCommentId 任务评价ID
     * @return 结果
     */
    public int deleteTaskCommentById(Long oaTaskCommentId);

    /**
     * 批量删除任务评价
     * 
     * @param oaTaskCommentIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteTaskCommentByIds(String[] oaTaskCommentIds);
}
