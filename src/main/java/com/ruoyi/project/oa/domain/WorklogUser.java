package com.ruoyi.project.oa.domain;

import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * @author liy
 * @date 2020/4/3 22:44
 */
public class WorklogUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long worklogId;

    private Long userId;

    public void setWorklogId(Long worklogId)
    {
        this.worklogId = worklogId;
    }

    public Long getWorklogId()
    {
        return worklogId;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }

}
