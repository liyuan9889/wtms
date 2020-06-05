package com.ruoyi.project.oa.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 任务督办汇报人对象 oa_task_reply
 * 
 * @author ruoyi
 * @date 2020-04-19
 */
public class TaskReply extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long oaTaskReplyId;

    /** 任务ID */
    @Excel(name = "任务ID")
    private Long taskId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 用户名称 */
    @Excel(name = "用户名称")
    private String userName;

    /** 意见内容 */
    @Excel(name = "意见内容")
    private String content;

    /** 进度 */
    @Excel(name = "进度")
    private Integer progress;

    /** 时长 */
    @Excel(name = "时长")
    private String hours;

    public void setOaTaskReplyId(Long oaTaskReplyId) 
    {
        this.oaTaskReplyId = oaTaskReplyId;
    }

    public Long getOaTaskReplyId() 
    {
        return oaTaskReplyId;
    }
    public void setTaskId(Long taskId) 
    {
        this.taskId = taskId;
    }

    public Long getTaskId() 
    {
        return taskId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setProgress(Integer progress) 
    {
        this.progress = progress;
    }

    public Integer getProgress() 
    {
        return progress;
    }
    public void setHours(String hours) 
    {
        this.hours = hours;
    }

    public String getHours() 
    {
        return hours;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("oaTaskReplyId", getOaTaskReplyId())
            .append("taskId", getTaskId())
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("content", getContent())
            .append("progress", getProgress())
            .append("hours", getHours())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
