package com.ruoyi.project.oa.service.impl;

import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.project.oa.domain.WorklogUser;
import com.ruoyi.project.oa.mapper.WorklogUserMapper;
import com.ruoyi.project.oa.service.IWorklogUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liy
 * @date 2020/4/3 23:35
 */
@Service
public class WorklogUserServiceImpl implements IWorklogUserService {

    @Autowired
    private WorklogUserMapper worklogUserMapper;

    /**
     * 查询工作日志用户
     *
     * @param worklogId 工作日志用户ID
     * @return 工作日志用户
     */
    @Override
    public WorklogUser selectWorklogUserById(Long worklogId)
    {
        return worklogUserMapper.selectWorklogUserById(worklogId);
    }

    /**
     * 查询工作日志用户列表
     *
     * @param worklogUser 工作日志用户
     * @return 工作日志用户
     */
    @Override
    public List<WorklogUser> selectWorklogUserList(WorklogUser worklogUser)
    {
        return worklogUserMapper.selectWorklogUserList(worklogUser);
    }

    /**
     * 查询工作日志用户列表
     *
     * @param worklogUser 工作日志用户
     * @return 工作日志用户
     */
    @Override
    public List<Long> selectWorklogIdList(WorklogUser worklogUser)
    {
        List<Long> longList = new ArrayList<>();
        List<WorklogUser> worklogUserList = worklogUserMapper.selectWorklogUserList(worklogUser);
        for (WorklogUser user: worklogUserList) {
            longList.add(user.getUserId());
        }
        return longList;
    }

    /**
     * 新增工作日志用户
     *
     * @param worklogUser 工作日志用户
     * @return 结果
     */
    @Override
    public int insertWorklogUser(WorklogUser worklogUser)
    {
        return worklogUserMapper.insertWorklogUser(worklogUser);
    }

    /**
     * 修改工作日志用户
     *
     * @param worklogUser 工作日志用户
     * @return 结果
     */
    @Override
    public int updateWorklogUser(WorklogUser worklogUser)
    {
        return worklogUserMapper.updateWorklogUser(worklogUser);
    }

    /**
     * 删除工作日志用户对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWorklogUserByIds(String ids)
    {
        return worklogUserMapper.deleteWorklogUserByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除工作日志用户信息
     *
     * @param worklogId 工作日志用户ID
     * @return 结果
     */
    @Override
    public int deleteWorklogUserById(Long worklogId)
    {
        return worklogUserMapper.deleteWorklogUserById(worklogId);
    }

}
