package com.ruoyi.project.oa.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Org extends BaseEntity {

    private Long oaOrgId;
    private Long parentId;
    private String ancestors;
    private String orgName;
    private Integer orderNum;
    private Integer delFlag;

}
