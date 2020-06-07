package com.ruoyi.project.oa.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 帖子阅读量对象 forum_message_read
 * 
 * @author ruoyi
 * @date 2020-04-18
 */
public class ForumMessageRead extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 阅读用户的id */
    @Excel(name = "阅读用户的id")
    private Long userId;

    /** 帖子的id */
    @Excel(name = "帖子的id")
    private Long forumMessageId;

    /** 论坛的id */
    @Excel(name = "论坛的id")
    private Long forumId;

    /** 0是有效的1是删除的 */
    @Excel(name = "0是有效的1是删除的")
    private Long status;

    /** 修改人 */
    @Excel(name = "修改人")
    private Long updateUser;

    /**论坛的名称*/
    private String forumTitle;

    /**所属论坛*/
    private String plateType;

    public String getForumTitle() {
        return forumTitle;
    }

    public void setForumTitle(String forumTitle) {
        this.forumTitle = forumTitle;
    }

    public String getPlateType() {
        return plateType;
    }

    public void setPlateType(String plateType) {
        this.plateType = plateType;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setForumMessageId(Long forumMessageId) 
    {
        this.forumMessageId = forumMessageId;
    }

    public Long getForumMessageId() 
    {
        return forumMessageId;
    }
    public void setForumId(Long forumId) 
    {
        this.forumId = forumId;
    }

    public Long getForumId() 
    {
        return forumId;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setUpdateUser(Long updateUser) 
    {
        this.updateUser = updateUser;
    }

    public Long getUpdateUser() 
    {
        return updateUser;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("createTime", getCreateTime())
            .append("forumMessageId", getForumMessageId())
            .append("forumId", getForumId())
            .append("status", getStatus())
            .append("updateUser", getUpdateUser())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
