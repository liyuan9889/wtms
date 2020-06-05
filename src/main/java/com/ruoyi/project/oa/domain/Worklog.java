package com.ruoyi.project.oa.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 工作日志对象 oa_worklog
 * 
 * @author ruoyi
 * @date 2020-04-03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Worklog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long oaWorklogId;

    /** 创建人ID */
    @Excel(name = "创建人ID")
    private Long createUserId;

    /** 工作类型 */
    @Excel(name = "工作类型")
    private Integer workType;

    /** 工作地点/内容 */
    @Excel(name = "工作地点/内容")
    private String addrContent;

    /** 开始时间 */
    @Excel(name = "开始时间")
    private String startTime;

    /** 结束时间 */
    @Excel(name = "结束时间")
    private String endTime;

    /** 工作日期 */
    @Excel(name = "工作日期", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date workDate;
    /** 所在部门ID */
    private Long deptId;
    /** 工作时长 */
    private Integer minutes;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createUserName;

    /** 工作人员 */
    private String groupUserIds;

    private Long userId;

    private Integer searchType;
    private Integer num;
    private Integer worklog_totalNum;
    private String cStartTime;
    private String cEndTime;
    private String leaderName;
    private String userName;

}
