package com.ruoyi.project.oa.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 论坛对象 forum
 * 
 * @author ruoyi
 * @date 2020-04-18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Forum extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 论坛标题 */
    @Excel(name = "论坛标题")
    private String title;

    @Excel(name = "所属板块")
    private Integer plateType;

    /** 创建人id */
    @Excel(name = "创建人id")
    private Long createUser;

    /** 修改人 */
    @Excel(name = "修改人")
    private Long updateUser;

    /** 0是有效的1是删除 */
    @Excel(name = "0是有效的1是删除")
    private Long status;

    /** 头像 */
    private String imgUrl;

}
