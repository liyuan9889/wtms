package com.ruoyi.project.oa.mapper;

import com.ruoyi.project.oa.domain.ForumMessage;

import java.util.List;

/**
 * 论坛帖子Mapper接口
 * 
 * @author ruoyi
 * @date 2020-04-18
 */
public interface ForumMessageMapper 
{
    /**
     * 查询论坛帖子
     * 
     * @param id 论坛帖子ID
     * @return 论坛帖子
     */
    public ForumMessage selectForumMessageById(Long id);

    public ForumMessage selectClassForumMessageById(Long id);

    /**
     * 查询论坛帖子列表
     * 
     * @param forumMessage 论坛帖子
     * @return 论坛帖子集合
     */
    public List<ForumMessage> selectForumMessageList(ForumMessage forumMessage);


    /**
     * 查询论坛帖子阅读量最多的
     * @param forumMessage
     * @return
     */
    public List<ForumMessage> selectForumMessageHotList(ForumMessage forumMessage);

    public List<ForumMessage> selectClassForumMessageHotList(ForumMessage forumMessage);


    /**
     * 新增论坛帖子
     * 
     * @param forumMessage 论坛帖子
     * @return 结果
     */
    public int insertForumMessage(ForumMessage forumMessage);

    /**
     * 修改论坛帖子
     * 
     * @param forumMessage 论坛帖子
     * @return 结果
     */
    public int updateForumMessage(ForumMessage forumMessage);

    /**
     * 删除论坛帖子
     * 
     * @param id 论坛帖子ID
     * @return 结果
     */
    public int deleteForumMessageById(Long id);

    /**
     * 批量删除论坛帖子
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteForumMessageByIds(String[] ids);

    /**
     * 帖子的数量
     * @param forumMessage
     * @return
     */
    public int selectForumMessageNum(ForumMessage forumMessage);

    public int selectClassForumMessageNum(ForumMessage forumMessage);

    /**
     * 查询置顶的数据
     * @param forumMessage
     * @return
     */
    public  List<ForumMessage> getZdList(ForumMessage forumMessage);

    public  List<ForumMessage> getClassZdList(ForumMessage forumMessage);

    /**
     * 查询运维板块的帖子
     * @param forumMessage
     * @return
     */
    public List<ForumMessage> selectClassForumMessageList(ForumMessage forumMessage);

}
