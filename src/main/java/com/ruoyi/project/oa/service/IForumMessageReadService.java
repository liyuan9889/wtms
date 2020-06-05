package com.ruoyi.project.oa.service;


import com.ruoyi.project.oa.domain.ForumMessageRead;

import java.util.List;

/**
 * 帖子阅读量Service接口
 * 
 * @author ruoyi
 * @date 2020-04-18
 */
public interface IForumMessageReadService 
{
    /**
     * 查询帖子阅读量
     * 
     * @param id 帖子阅读量ID
     * @return 帖子阅读量
     */
    public ForumMessageRead selectForumMessageReadById(Long id);

    /**
     * 查询帖子阅读量列表
     * 
     * @param forumMessageRead 帖子阅读量
     * @return 帖子阅读量集合
     */
    public List<ForumMessageRead> selectForumMessageReadList(ForumMessageRead forumMessageRead);

    /**
     * 新增帖子阅读量
     * 
     * @param forumMessageRead 帖子阅读量
     * @return 结果
     */
    public int insertForumMessageRead(ForumMessageRead forumMessageRead);

    /**
     * 修改帖子阅读量
     * 
     * @param forumMessageRead 帖子阅读量
     * @return 结果
     */
    public int updateForumMessageRead(ForumMessageRead forumMessageRead);

    /**
     * 批量删除帖子阅读量
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteForumMessageReadByIds(String ids);

    /**
     * 删除帖子阅读量信息
     * 
     * @param id 帖子阅读量ID
     * @return 结果
     */
    public int deleteForumMessageReadById(Long id);

    /**
     * 查询阅读过的论坛
     * @param forumMessageRead
     * @return
     */
    public List<ForumMessageRead> selectForum(ForumMessageRead forumMessageRead);
}
