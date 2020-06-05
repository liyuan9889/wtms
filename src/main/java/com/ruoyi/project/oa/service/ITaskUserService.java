package com.ruoyi.project.oa.service;

import com.ruoyi.project.oa.domain.TaskUser;

import java.util.List;

/**
 * 用户内容Service接口
 * 
 * @author ruoyi
 * @date 2020-03-31
 */
public interface ITaskUserService 
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

    public List<Long> selectTaskUserIdList(TaskUser taskUser);

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
     * 批量删除用户内容
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTaskUserByIds(String ids);

    /**
     * 删除用户内容信息
     * 
     * @param oaTaskUserId 用户内容ID
     * @return 结果
     */
    public int deleteTaskUserById(Long oaTaskUserId);

    /**
     * 批量插入任务用户
     * @param taskUserList
     * @return 结果
     */
    public int insertTaskUserBatch(List<TaskUser> taskUserList);


}
