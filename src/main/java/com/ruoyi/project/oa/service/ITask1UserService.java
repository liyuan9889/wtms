package com.ruoyi.project.oa.service;

import com.ruoyi.project.oa.domain.Task1User;

import java.util.List;

/**
 * 用户内容Service接口
 * 
 * @author ruoyi
 * @date 2020-03-31
 */
public interface ITask1UserService
{
    /**
     * 查询用户内容
     * 
     * @param oaTaskUserId 用户内容ID
     * @return 用户内容
     */
    public Task1User selectTaskUserById(Long oaTaskUserId);

    /**
     * 查询用户内容列表
     * 
     * @param taskUser 用户内容
     * @return 用户内容集合
     */
    public List<Task1User> selectTaskUserList(Task1User taskUser);

    public List<Long> selectTaskUserIdList(Task1User taskUser);

    /**
     * 新增用户内容
     * 
     * @param taskUser 用户内容
     * @return 结果
     */
    public int insertTaskUser(Task1User taskUser);

    /**
     * 修改用户内容
     * 
     * @param taskUser 用户内容
     * @return 结果
     */
    public int updateTaskUser(Task1User taskUser);

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
    public int insertTaskUserBatch(List<Task1User> taskUserList);


}
