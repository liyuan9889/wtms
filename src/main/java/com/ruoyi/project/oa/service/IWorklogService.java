package com.ruoyi.project.oa.service;

import com.ruoyi.project.oa.domain.Worklog;

import java.util.List;

/**
 * 工作日志Service接口
 * 
 * @author ruoyi
 * @date 2020-04-03
 */
public interface IWorklogService 
{
    /**
     * 查询工作日志
     * 
     * @param oaWorklogId 工作日志ID
     * @return 工作日志
     */
    public Worklog selectWorklogById(Long oaWorklogId);

    /**
     * 查询工作日志列表
     * 
     * @param worklog 工作日志
     * @return 工作日志集合
     */
    public List<Worklog> selectWorklogList(Worklog worklog);

    /**
     * 新增工作日志
     * 
     * @param worklog 工作日志
     * @return 结果
     */
    public int insertWorklog(Worklog worklog);

    /**
     * 修改工作日志
     * 
     * @param worklog 工作日志
     * @return 结果
     */
    public int updateWorklog(Worklog worklog);

    /**
     * 批量删除工作日志
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWorklogByIds(String ids);

    /**
     * 删除工作日志信息
     * 
     * @param oaWorklogId 工作日志ID
     * @return 结果
     */
    public int deleteWorklogById(Long oaWorklogId);
}
