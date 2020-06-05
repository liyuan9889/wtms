package com.ruoyi.project.oa.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.project.oa.domain.Worklog;
import com.ruoyi.project.oa.domain.WorklogUser;
import com.ruoyi.project.oa.mapper.WorklogMapper;
import com.ruoyi.project.oa.mapper.WorklogUserMapper;
import com.ruoyi.project.oa.service.IWorklogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 工作日志Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-04-03
 */
@Service
public class WorklogServiceImpl implements IWorklogService
{
    @Autowired
    private WorklogMapper worklogMapper;
    @Autowired
    private WorklogUserMapper worklogUserMapper;

    /**
     * 查询工作日志
     * 
     * @param oaWorklogId 工作日志ID
     * @return 工作日志
     */
    @Override
    public Worklog selectWorklogById(Long oaWorklogId)
    {
        return worklogMapper.selectWorklogById(oaWorklogId);
    }

    /**
     * 查询工作日志列表
     * 
     * @param worklog 工作日志
     * @return 工作日志
     */
    @Override
    public List<Worklog> selectWorklogList(Worklog worklog)
    {
        return worklogMapper.selectWorklogList(worklog);
    }

    /**
     * 新增工作日志
     * 
     * @param worklog 工作日志
     * @return 结果
     */
    @Override
    @Transactional
    public int insertWorklog(Worklog worklog)
    {
        worklog.setCreateTime(DateUtils.getNowDate());
        int count = worklogMapper.insertWorklog(worklog);
        String[] userIds = worklog.getGroupUserIds().split(",");
        List<WorklogUser> worklogUserList = new ArrayList<>();
        for (String userId : userIds) {
            WorklogUser user = new WorklogUser();
            user.setUserId(Long.valueOf(userId));
            user.setWorklogId(worklog.getOaWorklogId());
            worklogUserList.add(user);
        }
        worklogUserMapper.insertWorklogUserBatch(worklogUserList);
        return count;
    }

    /**
     * 修改工作日志
     * 
     * @param worklog 工作日志
     * @return 结果
     */
    @Override
    @Transactional
    public int updateWorklog(Worklog worklog)
    {
        List<String> stringList = new ArrayList<>();
        stringList.add(String.valueOf(worklog.getOaWorklogId()));
        worklogUserMapper.deleteWorklogUserByIds(stringList.toArray(new String[1]));
        String[] userIds = worklog.getGroupUserIds().split(",");
        List<WorklogUser> worklogUserList = new ArrayList<>();
        for (String userId : userIds) {
            WorklogUser user = new WorklogUser();
            user.setUserId(Long.valueOf(userId));
            user.setWorklogId(worklog.getOaWorklogId());
            worklogUserList.add(user);
        }
        worklogUserMapper.insertWorklogUserBatch(worklogUserList);
        worklog.setUpdateTime(DateUtils.getNowDate());
        return worklogMapper.updateWorklog(worklog);
    }

    /**
     * 删除工作日志对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWorklogByIds(String ids)
    {
        return worklogMapper.deleteWorklogByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除工作日志信息
     * 
     * @param oaWorklogId 工作日志ID
     * @return 结果
     */
    @Override
    public int deleteWorklogById(Long oaWorklogId)
    {
        return worklogMapper.deleteWorklogById(oaWorklogId);
    }
}
