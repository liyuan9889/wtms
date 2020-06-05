package com.ruoyi.project.oa.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkStatistics {

    private Integer deptId;
    private String deptName;
    private Integer userId;
    private String userName;
    /** 总数 */
    private Integer totalNum;
    /** 进行中 */
    private Integer proceedNum;
    /** 待评价 */
    private Integer preparedEvaluatingNum;
    /** 评价未通过 */
    private Integer failedNum;
    /** 已完成 */
    private Integer finishNum;
    /** 已超期 */
    private Integer expiredNum;
    /** 分钟 */
    private Integer minutes;

    private String startDate;
    private String endDate;
    private String cStartTime;
    private String cEndTime;

    private double hours;

}
