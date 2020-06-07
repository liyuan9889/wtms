package com.ruoyi.project.oa.service;

import com.ruoyi.project.oa.domain.Task1;

import java.util.List;

/**
 * 任务督办Service接口
 * 
 * @author ruoyi
 * @date 2020-03-29
 */
public interface ITask1Service
{
    /**
     * 查询任务督办
     * 
     * @param oaTaskId 任务督办ID
     * @return 任务督办
     */
    public Task1 selectTaskById(Long oaTaskId);

    /**
     * 查询任务进度
     * @param oaTaskId
     * @return
     */
    public int selectTaskProgressByTaskId(Long oaTaskId);

    /**
     * 查询任务督办列表
     * 
     * @param task 任务督办
     * @return 任务督办集合
     */
    public List<Task1> selectTaskList(Task1 task);

    /**
     * 新增任务督办
     * 
     * @param task 任务督办
     * @return 结果
     */
    public int insertTask(Task1 task);

    /**
     * 修改任务督办
     * 
     * @param task 任务督办
     * @return 结果
     */
    public int updateTask(Task1 task);

    /**
     * 批量删除任务督办
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTaskByIds(String ids);

    /**
     * 删除任务督办信息
     * 
     * @param oaTaskId 任务督办ID
     * @return 结果
     */
    public int deleteTaskById(Long oaTaskId);

    /**
     * 查询所有回复内容
     * @return
     */
    public List<Task1> selectTaskReplyContentList(Task1 task);

    /**
     * 首页查询个人督办任务
     * @param task
     * @return
     */
    public List<Task1> selectTaskByUserIdForMain(Task1 task);
}
