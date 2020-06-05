package com.ruoyi.project.oa.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 论坛公告对象 forum_notice
 * 
 * @author ruoyi
 * @date 2020-04-25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForumNotice extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long forumNoticeId;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    /** 排序 */
    @Excel(name = "排序")
    private Integer orderNum;

    /** 是否删除：（0：正常；1：删除） */
    private Integer delFlag;

    private String cStartTime;
    private String cEndTime;

}
