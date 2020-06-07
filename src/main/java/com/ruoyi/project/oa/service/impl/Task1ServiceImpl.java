package com.ruoyi.project.oa.service.impl;

import cn.hutool.core.util.ArrayUtil;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.project.oa.domain.Task1;
import com.ruoyi.project.oa.domain.Task1User;
import com.ruoyi.project.oa.mapper.Task1Mapper;
import com.ruoyi.project.oa.mapper.Task1UserMapper;
import com.ruoyi.project.oa.service.ITask1Service;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 任务督办Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-03-29
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Task1ServiceImpl implements ITask1Service
{
    private final Task1Mapper taskMapper;
    private final Task1UserMapper taskUserMapper;
    private final UserMapper userMapper;

    /**
     * 查询任务督办
     * 
     * @param oaTaskId 任务督办ID
     * @return 任务督办
     */
    @Override
    public Task1 selectTaskById(Long oaTaskId)
    {
        return taskMapper.selectTask1ById(oaTaskId);
    }

    /**
     * 查询任务进度
     * @param oaTaskId
     * @return
     */
    @Override
    public int selectTaskProgressByTaskId(Long oaTaskId) {
        return taskMapper.selectTask1ProgressByTaskId(oaTaskId);
    }

    /**
     * 查询任务督办列表
     * 
     * @param task 任务督办
     * @return 任务督办
     */
    @Override
    public List<Task1> selectTaskList(Task1 task)
    {
        return taskMapper.selectTask1List(task);
    }

    /**
     * 新增任务督办
     * 
     * @param task 任务督办
     * @return 结果
     */
    @Override
    public int insertTask(Task1 task)
    {
        task.setCreateTime(DateUtils.getNowDate());
        return taskMapper.insertTask1(task);
    }

    /**
     * 修改任务督办
     * 
     * @param task 任务督办
     * @return 结果
     */
    @Override
    @Transactional
    public int updateTask(Task1 task)
    {
        Long taskId = task.getOaTaskId();
        Long[] executorIds = task.getExecutorIds();
        if (ArrayUtil.isNotEmpty(executorIds)){
            updateTaskUser(taskId, executorIds,1);

        }
        Long[] appraiserIds = task.getAppraiserIds();
        if (ArrayUtil.isNotEmpty(appraiserIds)){
            updateTaskUser(taskId, appraiserIds,2);
        }

        Long[] partUserIds = task.getPartUserIds();
        if (ArrayUtil.isNotEmpty(partUserIds)){
            updateTaskUser(taskId, partUserIds,3);

        }
        Long[] shareUserIds = task.getShareUserIds();
        if (ArrayUtil.isNotEmpty(shareUserIds)){
            updateTaskUser(taskId, shareUserIds,4);
        }
        Long[] leaderIds = task.getLeaderIds();
        if (ArrayUtil.isNotEmpty(leaderIds)){
            updateTaskUser(taskId, leaderIds,5);
        }

        task.setUpdateTime(DateUtils.getNowDate());
        return taskMapper.updateTask1(task);
    }

    public void updateTaskUser(Long taskId, Long[] userIds, int userType) {
        Task1User taskUser = new Task1User();
        taskUser.setTaskId(taskId);
        taskUser.setUserType(userType);
        taskUserMapper.deleteTask1UserByOaTask(taskUser);

        List<Task1User> taskUserList = new ArrayList<>();
        for (Long userId : userIds) {
            User user = userMapper.selectUserById(userId);
            Task1User taskUser1 = new Task1User();
            taskUser1.setTaskId(taskId);
            taskUser1.setUserId(userId);
            taskUser1.setUserType(userType);
            taskUser1.setUserName(user.getUserName());
            taskUserList.add(taskUser1);
        }
        taskUserMapper.insertTask1UserBatch(taskUserList);
    }

    /**
     * 删除任务督办对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTaskByIds(String ids)
    {
        return taskMapper.deleteTask1ByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除任务督办信息
     * 
     * @param oaTaskId 任务督办ID
     * @return 结果
     */
    @Override
    public int deleteTaskById(Long oaTaskId)
    {
        return taskMapper.deleteTask1ById(oaTaskId);
    }

    /**
     * 查询所有回复内容
     * @return
     */
    @Override
    public List<Task1> selectTaskReplyContentList(Task1 task) {
        return taskMapper.selectTask1ReplyContentList(task);
    }

    /**
     * 首页查询个人督办任务
     * @param task
     * @return
     */
    @Override
    public List<Task1> selectTaskByUserIdForMain(Task1 task) {
        return taskMapper.selectTask1ByUserIdForMain(task);
    }
}
