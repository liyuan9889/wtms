package com.ruoyi.project.oa.mapper;

import com.ruoyi.project.oa.domain.Task1User;

import java.util.List;

/**
 * 用户内容Mapper接口
 * 
 * @author ruoyi
 * @date 2020-03-31
 */
public interface Task1UserMapper
{
    /**
     * 查询用户内容
     * 
     * @param oaTaskUserId 用户内容ID
     * @return 用户内容
     */
    public Task1User selectTask1UserById(Long oaTaskUserId);

    /**
     * 查询用户内容列表
     * 
     * @param taskUser 用户内容
     * @return 用户内容集合
     */
    public List<Task1User> selectTask1UserList(Task1User taskUser);

    /**
     * 新增用户内容
     * 
     * @param taskUser 用户内容
     * @return 结果
     */
    public int insertTask1User(Task1User taskUser);

    /**
     * 修改用户内容
     * 
     * @param taskUser 用户内容
     * @return 结果
     */
    public int updateTask1User(Task1User taskUser);

    /**
     * 删除用户内容
     * 
     * @param oaTaskUserId 用户内容ID
     * @return 结果
     */
    public int deleteTask1UserById(Long oaTaskUserId);

    /**
     * 批量删除用户内容
     * 
     * @param oaTaskUserIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteTask1UserByIds(String[] oaTaskUserIds);

    /**
     * 批量删除用户
     *
     * @param taskUser
     * @return 结果
     */
    public int deleteTask1UserByOaTask(Task1User taskUser);

    /**
     * 批量插入任务用户
     * @param taskUserList
     * @return 结果
     */
    public int insertTask1UserBatch(List<Task1User> taskUserList);
}
