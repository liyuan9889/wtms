package com.ruoyi.project.oa.service;


import com.ruoyi.project.oa.domain.ForumMessage;
import com.ruoyi.project.oa.domain.ForumMessageCollection;

import java.util.List;

/**
 * 用户收藏的论坛帖子Service接口
 * 
 * @author ruoyi
 * @date 2020-04-18
 */
public interface IForumMessageCollectionService 
{
    /**
     * 查询用户收藏的论坛帖子
     * 
     * @param id 用户收藏的论坛帖子ID
     * @return 用户收藏的论坛帖子
     */
    public ForumMessageCollection selectForumMessageCollectionById(Long id);


    /**
     * 查询用户收藏的论坛帖子列表
     * 
     * @param forumMessageCollection 用户收藏的论坛帖子
     * @return 用户收藏的论坛帖子集合
     */
    public List<ForumMessageCollection> selectForumMessageCollectionList(ForumMessageCollection forumMessageCollection);
    public List<ForumMessageCollection> selectClassForumMessageCollectionList(ForumMessageCollection forumMessageCollection);

    public List<ForumMessage>  selectMyForumMessageCollectionList(ForumMessage forumMessage);

    public List<ForumMessage>  selectMyClassForumMessageCollectionList(ForumMessage forumMessage);

    /**
     * 新增用户收藏的论坛帖子
     * 
     * @param forumMessageCollection 用户收藏的论坛帖子
     * @return 结果
     */
    public int insertForumMessageCollection(ForumMessageCollection forumMessageCollection);

    /**
     * 修改用户收藏的论坛帖子
     * 
     * @param forumMessageCollection 用户收藏的论坛帖子
     * @return 结果
     */
    public int updateForumMessageCollection(ForumMessageCollection forumMessageCollection);

    /**
     * 批量删除用户收藏的论坛帖子
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteForumMessageCollectionByIds(String ids);

    /**
     * 删除用户收藏的论坛帖子信息
     * 
     * @param id 用户收藏的论坛帖子ID
     * @return 结果
     */
    public int deleteForumMessageCollectionById(Long id);
}
