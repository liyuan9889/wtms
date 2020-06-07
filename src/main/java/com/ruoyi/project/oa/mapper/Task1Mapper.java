package com.ruoyi.project.oa.mapper;

import com.ruoyi.project.oa.domain.Task1;
import java.util.List;

/**
 * 任务督办Mapper接口
 * 
 * @author ruoyi
 * @date 2020-03-29
 */
public interface Task1Mapper
{
    /**
     * 查询任务督办
     * 
     * @param oaTaskId 任务督办ID
     * @return 任务督办
     */
    public Task1 selectTask1ById(Long oaTaskId);

    /**
     * 查询任务督办列表
     * 
     * @param task 任务督办
     * @return 任务督办集合
     */
    public List<Task1> selectTask1List(Task1 task);

    /**
     * 新增任务督办
     * 
     * @param task 任务督办
     * @return 结果
     */
    public int insertTask1(Task1 task);

    /**
     * 修改任务督办
     * 
     * @param task 任务督办
     * @return 结果
     */
    public int updateTask1(Task1 task);

    /**
     * 删除任务督办
     * 
     * @param oaTaskId 任务督办ID
     * @return 结果
     */
    public int deleteTask1ById(Long oaTaskId);

    /**
     * 批量删除任务督办
     * 
     * @param oaTaskIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteTask1ByIds(String[] oaTaskIds);

    /**
     * 查询所有回复内容
     * @return
     */
    public List<Task1> selectTask1ReplyContentList(Task1 task);

    /**
     * 查询进度
     * @param oaTaskId
     * @return
     */
    public int selectTask1ProgressByTaskId(Long oaTaskId);

    /**
     * 首页查询个人督办任务
     * @param task
     * @return
     */
    public List<Task1> selectTask1ByUserIdForMain(Task1 task);

    /**
     * 查询字典表
     * @param dictType
     * @return
     */
    public List<String> ajaxSelectSourceType1(String dictType);
}
