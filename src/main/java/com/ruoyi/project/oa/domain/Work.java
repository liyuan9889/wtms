package com.ruoyi.project.oa.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 工作汇报对象 oa_work
 * 
 * @author ruoyi
 * @date 2020-04-03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Work extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long oaWorkId;

    /** 部门 */
    @Excel(name = "部门")
    private Long deptId;

    /** 任务名称 */
    @Excel(name = "任务名称")
    private String workName;

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
    private Integer sourceType;

    /** 下发人 */
    @Excel(name = "下发人")
    private Long sponsorId;

    /** 工作内容 */
    @Excel(name = "工作内容")
    private String content;

    /** 进度 */
    @Excel(name = "进度")
    private Integer progress;

    /** 部门 */
    private String deptName;
    private String userName;
    private String replyCreateTime;
    private String replyProgress;
    private String replyContent;
    private Long userId;

    private Integer searchType;
    private Integer subStatus;
    private Integer num;
    private Integer work_totalNum;
    private Integer work_proceedNum;
    private Integer work_preparedEvaluatingNum;
    private Integer work_expiredNum;
    private String statusDescribe;
    private String cStartTime;
    private String cEndTime;
    private String hours;
    private String leaderName;


}
