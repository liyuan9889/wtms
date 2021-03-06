package com.ruoyi.project.oa.mapper;

import com.ruoyi.project.oa.domain.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 任务督办Mapper接口
 * 
 * @author ruoyi
 * @date 2020-03-29
 */
public interface TaskMapper 
{
    /**
     * 查询任务督办
     * 
     * @param oaTaskId 任务督办ID
     * @return 任务督办
     */
    public Task selectTaskById(Long oaTaskId);

    /**
     * 查询任务督办列表
     * 
     * @param task 任务督办
     * @return 任务督办集合
     */
    public List<Task> selectTaskList(Task task);

    /**
     * 新增任务督办
     * 
     * @param task 任务督办
     * @return 结果
     */
    public int insertTask(Task task);

    /**
     * 修改任务督办
     * 
     * @param task 任务督办
     * @return 结果
     */
    public int updateTask(Task task);

    /**
     * 删除任务督办
     * 
     * @param oaTaskId 任务督办ID
     * @return 结果
     */
    public int deleteTaskById(Long oaTaskId);

    /**
     * 批量删除任务督办
     * 
     * @param oaTaskIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteTaskByIds(String[] oaTaskIds);

    /**
     * 查询所有回复内容
     * @return
     */
    public List<Task> selectTaskReplyContentList(Task task);

    /**
     * 查询进度
     * @param oaTaskId
     * @return
     */
    public int selectTaskProgressByTaskId(Long oaTaskId);

    /**
     * 首页查询个人督办任务
     * @param task
     * @return
     */
    public List<Task> selectTaskByUserIdForMain(Task task);

    /**
     * 查询字典表
     * @param dictType
     * @return
     */
    public List<String> ajaxSelectSourceType(String dictType);
}
