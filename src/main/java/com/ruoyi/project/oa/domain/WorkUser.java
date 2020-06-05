package com.ruoyi.project.oa.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liy
 * @date 2020/4/6 23:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkUser extends BaseEntity {

    private Long workId;
    private Long userId;
    private String content;
    private Integer progress;
    private String hours;

}
