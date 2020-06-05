package com.ruoyi.project.oa.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 用户内容对象 oa_task_user
 * 
 * @author ruoyi
 * @date 2020-03-31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long oaTaskUserId;

    /** 任务ID */
    @Excel(name = "任务ID")
    private Long taskId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 用户名称 */
    @Excel(name = "用户名称")
    private String userName;

    /** 用户类型（1：负责人；2：评价人） */
    @Excel(name = "用户类型", readConverterExp = "1=：负责人；2：评价人")
    private Integer userType;

    /** 意见内容 */
    @Excel(name = "意见内容")
    private String content;

    /** 进度 */
    @Excel(name = "进度")
    private Integer progress;

    private String hours;

    /** 用户是否存在此岗位标识 默认不存在 */
    private boolean flag = false;

}
