package com.ruoyi.project.oa.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.project.oa.domain.Task1User;
import com.ruoyi.project.oa.mapper.Task1UserMapper;
import com.ruoyi.project.oa.service.ITask1UserService;
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
public class Task1UserServiceImpl implements ITask1UserService
{
    private final Task1UserMapper taskUserMapper;

    /**
     * 查询用户内容
     * 
     * @param oaTaskUserId 用户内容ID
     * @return 用户内容
     */
    @Override
    public Task1User selectTaskUserById(Long oaTaskUserId)
    {
        return taskUserMapper.selectTask1UserById(oaTaskUserId);
    }

    /**
     * 查询用户内容列表
     * 
     * @param taskUser 用户内容
     * @return 用户内容
     */
    @Override
    public List<Task1User> selectTaskUserList(Task1User taskUser)
    {
        return taskUserMapper.selectTask1UserList(taskUser);
    }

    /**
     * 查询用户内容列表
     *
     * @param taskUser 用户内容
     * @return 用户内容
     */
    @Override
    public List<Long> selectTaskUserIdList(Task1User taskUser)
    {
        List<Long> longList = new ArrayList<>();
        List<Task1User> taskUserList = taskUserMapper.selectTask1UserList(taskUser);
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
    public int insertTaskUser(Task1User taskUser)
    {
        taskUser.setCreateTime(DateUtils.getNowDate());
        return taskUserMapper.insertTask1User(taskUser);
    }

    /**
     * 修改用户内容
     * 
     * @param taskUser 用户内容
     * @return 结果
     */
    @Override
    public int updateTaskUser(Task1User taskUser)
    {
        taskUser.setUpdateTime(DateUtils.getNowDate());
        return taskUserMapper.updateTask1User(taskUser);
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
        return taskUserMapper.deleteTask1UserByIds(Convert.toStrArray(ids));
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
        return taskUserMapper.deleteTask1UserById(oaTaskUserId);
    }

    /**
     * 批量插入任务用户
     * @param taskUserList
     * @return
     */
    @Override
    public int insertTaskUserBatch(List<Task1User> taskUserList) {
        return taskUserMapper.insertTask1UserBatch(taskUserList);
    }
}
