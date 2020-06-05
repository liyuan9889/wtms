package com.ruoyi.project.oa.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.oa.controller.ForumMessageController;
import com.ruoyi.project.oa.domain.ForumMessage;
import com.ruoyi.project.oa.domain.ForumMessageCollection;
import com.ruoyi.project.oa.mapper.ForumMessageCollectionMapper;
import com.ruoyi.project.oa.service.IForumMessageCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.utils.text.Convert;

/**
 * 用户收藏的论坛帖子Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-04-18
 */
@Service
public class ForumMessageCollectionServiceImpl implements IForumMessageCollectionService
{
    @Autowired
    private ForumMessageCollectionMapper forumMessageCollectionMapper;

    /**
     * 查询用户收藏的论坛帖子
     * 
     * @param id 用户收藏的论坛帖子ID
     * @return 用户收藏的论坛帖子
     */
    @Override
    public ForumMessageCollection selectForumMessageCollectionById(Long id)
    {
        return forumMessageCollectionMapper.selectForumMessageCollectionById(id);
    }

    /**
     * 查询用户收藏的论坛帖子列表
     * 
     * @param forumMessageCollection 用户收藏的论坛帖子
     * @return 用户收藏的论坛帖子
     */
    @Override
    public List<ForumMessageCollection> selectForumMessageCollectionList(ForumMessageCollection forumMessageCollection)
    {
        return forumMessageCollectionMapper.selectForumMessageCollectionList(forumMessageCollection);
    }

    @Override
    public List<ForumMessageCollection> selectClassForumMessageCollectionList(ForumMessageCollection forumMessageCollection) {
        return forumMessageCollectionMapper.selectClassForumMessageCollectionList(forumMessageCollection);
    }

    @Override
    public List<ForumMessage> selectMyForumMessageCollectionList(ForumMessage forumMessage) {
        return forumMessageCollectionMapper.selectMyForumMessageCollectionList(forumMessage);
    }

    @Override
    public List<ForumMessage> selectMyClassForumMessageCollectionList(ForumMessage forumMessage) {
        return forumMessageCollectionMapper.selectMyClassForumMessageCollectionList(forumMessage);
    }

    /**
     * 新增用户收藏的论坛帖子
     * 
     * @param forumMessageCollection 用户收藏的论坛帖子
     * @return 结果
     */
    @Override
    public int insertForumMessageCollection(ForumMessageCollection forumMessageCollection)
    {
        forumMessageCollection.setCreateTime(DateUtils.getNowDate());
        return forumMessageCollectionMapper.insertForumMessageCollection(forumMessageCollection);
    }

    /**
     * 修改用户收藏的论坛帖子
     * 
     * @param forumMessageCollection 用户收藏的论坛帖子
     * @return 结果
     */
    @Override
    public int updateForumMessageCollection(ForumMessageCollection forumMessageCollection)
    {
        forumMessageCollection.setUpdateTime(DateUtils.getNowDate());
        return forumMessageCollectionMapper.updateForumMessageCollection(forumMessageCollection);
    }

    /**
     * 删除用户收藏的论坛帖子对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteForumMessageCollectionByIds(String ids)
    {
        return forumMessageCollectionMapper.deleteForumMessageCollectionByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除用户收藏的论坛帖子信息
     * 
     * @param id 用户收藏的论坛帖子ID
     * @return 结果
     */
    @Override
    public int deleteForumMessageCollectionById(Long id)
    {
        return forumMessageCollectionMapper.deleteForumMessageCollectionById(id);
    }
}
