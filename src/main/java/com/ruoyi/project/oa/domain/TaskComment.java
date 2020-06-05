package com.ruoyi.project.oa.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 任务评价对象 oa_task_comment
 * 
 * @author ruoyi
 * @date 2020-04-15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskComment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long oaTaskCommentId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long taskId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long userId;

    /** 星级 */
    @Excel(name = "星级")
    private Integer star;

    /** 是否通过(0：通过；1：不通过） */
    @Excel(name = "是否通过(0：通过；1：不通过）")
    private Integer isPass;

    /** 不通过理由 */
    @Excel(name = "不通过理由")
    private String reason;

    /** 是否删除（0：正常；1：已删） */
    private Integer delFlag;

    private String userName;

}
