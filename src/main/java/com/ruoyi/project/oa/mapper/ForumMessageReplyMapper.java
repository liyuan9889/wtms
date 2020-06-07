package com.ruoyi.project.oa.mapper;

import com.ruoyi.project.oa.domain.ForumMessage;
import com.ruoyi.project.oa.domain.ForumMessageReply;

import java.util.List;

/**
 * 论坛帖子回复Mapper接口
 * 
 * @author ruoyi
 * @date 2020-04-18
 */
public interface ForumMessageReplyMapper 
{
    /**
     * 查询论坛帖子回复
     * 
     * @param id 论坛帖子回复ID
     * @return 论坛帖子回复
     */
    public ForumMessageReply selectForumMessageReplyById(Long id);

    /**
     * 查询论坛帖子回复列表
     * 
     * @param forumMessage 论坛帖子回复
     * @return 论坛帖子回复集合
     */
    public List<ForumMessage> selectMyForumMessageReplyList(ForumMessage forumMessage);

    public List<ForumMessage> selectMyClassForumMessageReplyList(ForumMessage forumMessage);


    public List<ForumMessageReply> selectForumMessageReplyList(ForumMessageReply forumMessage);

    public List<ForumMessageReply> selectClassForumMessageReplyList(ForumMessageReply forumMessage);

    /**
     * 新增论坛帖子回复
     * 
     * @param forumMessageReply 论坛帖子回复
     * @return 结果
     */
    public int insertForumMessageReply(ForumMessageReply forumMessageReply);

    /**
     * 修改论坛帖子回复
     * 
     * @param forumMessageReply 论坛帖子回复
     * @return 结果
     */
    public int updateForumMessageReply(ForumMessageReply forumMessageReply);

    /**
     * 删除论坛帖子回复
     * 
     * @param id 论坛帖子回复ID
     * @return 结果
     */
    public int deleteForumMessageReplyById(Long id);

    /**
     * 批量删除论坛帖子回复
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteForumMessageReplyByIds(String[] ids);


}
