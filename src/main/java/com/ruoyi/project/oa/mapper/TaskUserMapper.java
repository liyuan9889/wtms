package com.ruoyi.project.oa.mapper;

import com.ruoyi.project.oa.domain.TaskUser;

import java.util.List;

/**
 * 用户内容Mapper接口
 * 
 * @author ruoyi
 * @date 2020-03-31
 */
public interface TaskUserMapper 
{
    /**
     * 查询用户内容
     * 
     * @param oaTaskUserId 用户内容ID
     * @return 用户内容
     */
    public TaskUser selectTaskUserById(Long oaTaskUserId);

    /**
     * 查询用户内容列表
     * 
     * @param taskUser 用户内容
     * @return 用户内容集合
     */
    public List<TaskUser> selectTaskUserList(TaskUser taskUser);

    /**
     * 新增用户内容
     * 
     * @param taskUser 用户内容
     * @return 结果
     */
    public int insertTaskUser(TaskUser taskUser);

    /**
     * 修改用户内容
     * 
     * @param taskUser 用户内容
     * @return 结果
     */
    public int updateTaskUser(TaskUser taskUser);

    /**
     * 删除用户内容
     * 
     * @param oaTaskUserId 用户内容ID
     * @return 结果
     */
    public int deleteTaskUserById(Long oaTaskUserId);

    /**
     * 批量删除用户内容
     * 
     * @param oaTaskUserIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteTaskUserByIds(String[] oaTaskUserIds);

    /**
     * 批量删除用户
     *
     * @param taskUser
     * @return 结果
     */
    public int deleteTaskUserByOaTask(TaskUser taskUser);

    /**
     * 批量插入任务用户
     * @param taskUserList
     * @return 结果
     */
    public int insertTaskUserBatch(List<TaskUser> taskUserList);
}
