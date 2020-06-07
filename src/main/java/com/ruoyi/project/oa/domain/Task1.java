package com.ruoyi.project.oa.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 任务督办对象 oa_task
 * 
 * @author ruoyi
 * @date 2020-03-29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task1 extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long oaTaskId;

    /** 任务名称 */
    @Excel(name = "任务名称")
    private String taskName;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 重要程度 */
    @Excel(name = "重要程度")
    private Integer importantLevel;

    /** 困难程度 */
    @Excel(name = "困难程度")
    private Integer difficultLevel;

    /** 开始日期 */
    @Excel(name = "开始日期")
    private String startDate;

    /** 截止日期 */
    @Excel(name = "截止日期")
    private String endDate;

    /** 集体错失 */
    @Excel(name = "集体错失")
    private Integer isTeam;



    /** 来源 */
    @Excel(name = "来源")
    private String source;

    private Integer sourceType;

    /** 下发人 */
    @Excel(name = "下发人")
    private Long sponsorId;

    /** 负责人 */
    private String executorId;

    /** 评价人 */
    private String appraiserId;
    /** 评价人 */
    private String partId;
    /** 评价人 */
    private String shareId;
    /** 领导 */
    private String leaderId;
    /** 任务详情 */
    private String detail;

    /** 任务要求 */
    @Excel(name = "任务要求")
    private String content;

    /** 过期天数 */
    @Excel(name = "过期天数")
    private Integer expiredDay;

    /** 进度 */
    @Excel(name = "进度")
    private Integer progress;

    /** 文件地址 */
    @Excel(name = "文件地址")
    private String file;

    /** 终结原因 */
    private String overReason;
    private String hours;

    /** 部门 */
    @Excel(name = "部门")
    private Long deptId;

    private Long[] executorIds;
    private Long[] appraiserIds;
    private Long[] partUserIds;
    private Long[] shareUserIds;
    private Long[] leaderIds;

    /** 下发人名字 */
    private String sponsorName;
    /** 执行人名字 */
    private String executorName;
    /** 评价人名字 */
    private String appraiserName;
    /** 共享人名字 */
    private String shareUserName;
    /** 参与人名字 */
    private String partUserName;

    /** 部门 */
    private String deptName;
    private String userName;
    private String replyCreateTime;
    private String replyProgress;
    private String replyContent;
    private Integer userType;
    private Long userId;

    private Integer searchType;
    private Integer num;
    private Integer task_totalNum;
    private Integer task_proceedNum;
    private Integer task_preparedEvaluatingNum;
    private Integer task_failedNum;
    private Integer task_finishNum;
    private Integer task_overNum;
    private Integer task_expiredNum;
    private String cStartTime;
    private String cEndTime;
    private Integer subStatus;
    private String leaderName;


}
