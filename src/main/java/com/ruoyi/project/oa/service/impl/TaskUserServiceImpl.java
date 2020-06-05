package com.ruoyi.project.oa.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.project.oa.domain.TaskUser;
import com.ruoyi.project.oa.mapper.TaskUserMapper;
import com.ruoyi.project.oa.service.ITaskUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户内容Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-03-31
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TaskUserServiceImpl implements ITaskUserService
{
    private final TaskUserMapper taskUserMapper;

    /**
     * 查询用户内容
     * 
     * @param oaTaskUserId 用户内容ID
     * @return 用户内容
     */
    @Override
    public TaskUser selectTaskUserById(Long oaTaskUserId)
    {
        return taskUserMapper.selectTaskUserById(oaTaskUserId);
    }

    /**
     * 查询用户内容列表
     * 
     * @param taskUser 用户内容
     * @return 用户内容
     */
    @Override
    public List<TaskUser> selectTaskUserList(TaskUser taskUser)
    {
        return taskUserMapper.selectTaskUserList(taskUser);
    }

    /**
     * 查询用户内容列表
     *
     * @param taskUser 用户内容
     * @return 用户内容
     */
    @Override
    public List<Long> selectTaskUserIdList(TaskUser taskUser)
    {
        List<Long> longList = new ArrayList<>();
        List<TaskUser> taskUserList = taskUserMapper.selectTaskUserList(taskUser);
        for (int i = 0; i < taskUserList.size(); i++) {
            longList.add(taskUserList.get(i).getUserId());
        }
        return longList;
    }

    /**
     * 新增用户内容
     * 
     * @param taskUser 用户内容
     * @return 结果
     */
    @Override
    public int insertTaskUser(TaskUser taskUser)
    {
        taskUser.setCreateTime(DateUtils.getNowDate());
        return taskUserMapper.insertTaskUser(taskUser);
    }

    /**
     * 修改用户内容
     * 
     * @param taskUser 用户内容
     * @return 结果
     */
    @Override
    public int updateTaskUser(TaskUser taskUser)
    {
        taskUser.setUpdateTime(DateUtils.getNowDate());
        return taskUserMapper.updateTaskUser(taskUser);
    }

    /**
     * 删除用户内容对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTaskUserByIds(String ids)
    {
        return taskUserMapper.deleteTaskUserByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除用户内容信息
     * 
     * @param oaTaskUserId 用户内容ID
     * @return 结果
     */
    @Override
    public int deleteTaskUserById(Long oaTaskUserId)
    {
        return taskUserMapper.deleteTaskUserById(oaTaskUserId);
    }

    /**
     * 批量插入任务用户
     * @param taskUserList
     * @return
     */
    @Override
    public int insertTaskUserBatch(List<TaskUser> taskUserList) {
        return taskUserMapper.insertTaskUserBatch(taskUserList);
    }
}
