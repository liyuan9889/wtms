package com.ruoyi.project.oa.mapper;

import com.ruoyi.project.oa.domain.WorklogUser;
import java.util.List;

/**
 * @author liy
 * @date 2020/4/3 22:44
 */
public interface WorklogUserMapper {

    /**
     * 查询工作日志用户
     *
     * @param worklogId 工作日志用户ID
     * @return 工作日志用户
     */
    public WorklogUser selectWorklogUserById(Long worklogId);

    /**
     * 查询工作日志用户列表
     *
     * @param worklogUser 工作日志用户
     * @return 工作日志用户集合
     */
    public List<WorklogUser> selectWorklogUserList(WorklogUser worklogUser);

    /**
     * 新增工作日志用户
     *
     * @param worklogUser 工作日志用户
     * @return 结果
     */
    public int insertWorklogUser(WorklogUser worklogUser);

    /**
     * 修改工作日志用户
     *
     * @param worklogUser 工作日志用户
     * @return 结果
     */
    public int updateWorklogUser(WorklogUser worklogUser);

    /**
     * 删除工作日志用户
     *
     * @param worklogId 工作日志用户ID
     * @return 结果
     */
    public int deleteWorklogUserById(Long worklogId);

    /**
     * 批量删除工作日志用户
     *
     * @param worklogIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteWorklogUserByIds(String[] worklogIds);

    /**
     * 批量插入工作日志用户
     * @param worklogUserList
     * @return
     */
    public int insertWorklogUserBatch(List<WorklogUser> worklogUserList);

}
