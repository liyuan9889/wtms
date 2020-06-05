package com.ruoyi.project.oa.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 论坛帖子回复对象 forum_message_reply
 * 
 * @author ruoyi
 * @date 2020-04-18
 */
public class ForumMessageReply extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 帖子的id */
    @Excel(name = "帖子的id")
    private Long forumMessageId;

    /** 论坛的id */
    @Excel(name = "论坛的id")
    private Long forumId;

    /** 回复帖子的内容 */
    @Excel(name = "回复帖子的内容")
    private String replyMessage;

    /** 评论帖子的用户id */
    @Excel(name = "评论帖子的用户id")
    private Long createUser;

    /** 回复某个评论 */
    @Excel(name = "回复某个评论")
    private Long parentId;

    /** 0是有效的1是删除的 */
    @Excel(name = "0是有效的1是删除的")
    private Long status;

    /** 修改人 */
    @Excel(name = "修改人")
    private Long updateUser;

    /**
     * 发帖人
     */
    private String userName;

    /**
     * 论坛标题
     */
    private String forumTitle;

    /**
     * 所属板块
     */
    private  String plateType;

    /**
     * 帖子内容
     */
    private String context;

    /**帖子标题*/
    private String titile;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

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

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getTitile() {
        return titile;
    }

    public void setTitile(String titile) {
        this.titile = titile;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
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
    public void setReplyMessage(String replyMessage) 
    {
        this.replyMessage = replyMessage;
    }

    public String getReplyMessage() 
    {
        return replyMessage;
    }
    public void setCreateUser(Long createUser) 
    {
        this.createUser = createUser;
    }

    public Long getCreateUser() 
    {
        return createUser;
    }
    public void setParentId(Long parentId) 
    {
        this.parentId = parentId;
    }

    public Long getParentId() 
    {
        return parentId;
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
            .append("forumMessageId", getForumMessageId())
            .append("forumId", getForumId())
            .append("replyMessage", getReplyMessage())
            .append("createUser", getCreateUser())
            .append("createTime", getCreateTime())
            .append("parentId", getParentId())
            .append("status", getStatus())
            .append("updateUser", getUpdateUser())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
