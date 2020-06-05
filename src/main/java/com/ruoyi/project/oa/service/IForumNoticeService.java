package com.ruoyi.project.oa.service;

import com.ruoyi.project.oa.domain.ForumNotice;

import java.util.List;

/**
 * 论坛公告Service接口
 * 
 * @author ruoyi
 * @date 2020-04-25
 */
public interface IForumNoticeService 
{
    /**
     * 查询论坛公告
     * 
     * @param forumNoticeId 论坛公告ID
     * @return 论坛公告
     */
    public ForumNotice selectForumNoticeById(Long forumNoticeId);

    /**
     * 查询论坛公告列表
     * 
     * @param forumNotice 论坛公告
     * @return 论坛公告集合
     */
    public List<ForumNotice> selectForumNoticeList(ForumNotice forumNotice);

    /**
     * 新增论坛公告
     * 
     * @param forumNotice 论坛公告
     * @return 结果
     */
    public int insertForumNotice(ForumNotice forumNotice);

    /**
     * 修改论坛公告
     * 
     * @param forumNotice 论坛公告
     * @return 结果
     */
    public int updateForumNotice(ForumNotice forumNotice);

    /**
     * 批量删除论坛公告
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteForumNoticeByIds(String ids);

    /**
     * 删除论坛公告信息
     * 
     * @param forumNoticeId 论坛公告ID
     * @return 结果
     */
    public int deleteForumNoticeById(Long forumNoticeId);
}
