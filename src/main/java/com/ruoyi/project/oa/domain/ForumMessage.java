package com.ruoyi.project.oa.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.Date;

/**
 * 论坛帖子对象 forum_message
 * 
 * @author ruoyi
 * @date 2020-04-18
 */
public class ForumMessage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 论坛id */
    @Excel(name = "论坛id")
    private Long forumId;

    /** 帖子标题 */
    @Excel(name = "帖子标题")
    private String titile;

    /** 帖子内容 */
    @Excel(name = "帖子内容")
    private String context;

    /** 发帖人ID */
    @Excel(name = "发帖人ID")
    private Long createUser;

    /** 0是有效的1是删除的 */
    @Excel(name = "0是有效的1是删除的")
    private Long status;

    /** 修改人 */
    @Excel(name = "修改人")
    private Long updateUser;

    /** 是否讨论区0是 */
    @Excel(name = "是否讨论区0是，1否")
    private Long isTalk;

    /**
     * 是否置顶
     */
    @Excel(name = "是否置顶0否，1是")
    private Long isZd;

    /** 置顶时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date zdCreateTime;

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

    //开始日期
    private String startDate;

    //阅读数量
    private int readNum;

    //回复数量
    private int replyNum;

    //收藏
    private int collNum;

    //所属运维班
    private String deptName;

    //所属运维班id
    private String deptId;

    public int getCollNum() {
        return collNum;
    }

    public void setCollNum(int collNum) {
        this.collNum = collNum;
    }

    public int getReadNum() {
        return readNum;
    }

    public void setReadNum(int readNum) {
        this.readNum = readNum;
    }

    public int getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(int replyNum) {
        this.replyNum = replyNum;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getPlateType() {
        return plateType;
    }

    public void setPlateType(String plateType) {
        this.plateType = plateType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setForumId(Long forumId) 
    {
        this.forumId = forumId;
    }

    public Long getForumId() 
    {
        return forumId;
    }
    public void setTitile(String titile) 
    {
        this.titile = titile;
    }

    public String getTitile() 
    {
        return titile;
    }
    public void setContext(String context) 
    {
        this.context = context;
    }

    public String getContext() 
    {
        return context;
    }
    public void setCreateUser(Long createUser) 
    {
        this.createUser = createUser;
    }

    public Long getCreateUser() 
    {
        return createUser;
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

    public Long getIsTalk() {
        return isTalk;
    }

    public void setIsTalk(Long isTalk) {
        this.isTalk = isTalk;
    }

    public Long getIsZd() {
        return isZd;
    }

    public void setIsZd(Long isZd) {
        this.isZd = isZd;
    }


    public Date getZdCreateTime() {
        return zdCreateTime;
    }

    public void setZdCreateTime(Date zdCreateTime) {
        this.zdCreateTime = zdCreateTime;
    }

    public String getForumTitle() {
        return forumTitle;
    }

    public void setForumTitle(String forumTitle) {
        this.forumTitle = forumTitle;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    @Override
    public String toString() {
        return "ForumMessage{" +
                "id=" + id +
                ", forumId=" + forumId +
                ", titile='" + titile + '\'' +
                ", context='" + context + '\'' +
                ", createUser=" + createUser +
                ", status=" + status +
                ", updateUser=" + updateUser +
                ", isTalk=" + isTalk +
                '}';
    }
}
