package com.ruoyi.project.oa.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.project.oa.domain.ForumMessage;
import com.ruoyi.project.oa.domain.ForumMessageReply;
import com.ruoyi.project.oa.mapper.ForumMessageReplyMapper;
import com.ruoyi.project.oa.service.IForumMessageReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 论坛帖子回复Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-04-18
 */
@Service
public class ForumMessageReplyServiceImpl implements IForumMessageReplyService
{
    @Autowired
    private ForumMessageReplyMapper forumMessageReplyMapper;

    /**
     * 查询论坛帖子回复
     * 
     * @param id 论坛帖子回复ID
     * @return 论坛帖子回复
     */
    @Override
    public ForumMessageReply selectForumMessageReplyById(Long id)
    {
        return forumMessageReplyMapper.selectForumMessageReplyById(id);
    }

    @Override
    public List<ForumMessage> selectMyForumMessageReplyList(ForumMessage forumMessage) {
        return forumMessageReplyMapper.selectMyForumMessageReplyList(forumMessage);
    }

    @Override
    public List<ForumMessage> selectMyClassForumMessageReplyList(ForumMessage forumMessage) {
        return forumMessageReplyMapper.selectMyClassForumMessageReplyList(forumMessage);
    }

    /**
     * 查询论坛帖子回复列表
     * 
     * @param forumMessageReply 论坛帖子回复
     * @return 论坛帖子回复
     */
    @Override
    public List<ForumMessageReply> selectForumMessageReplyList(ForumMessageReply forumMessageReply)
    {
        return forumMessageReplyMapper.selectForumMessageReplyList(forumMessageReply);
    }

    @Override
    public List<ForumMessageReply> selectClassForumMessageReplyList(ForumMessageReply forumMessage) {
        return forumMessageReplyMapper.selectClassForumMessageReplyList(forumMessage);
    }

    /**
     * 新增论坛帖子回复
     * 
     * @param forumMessageReply 论坛帖子回复
     * @return 结果
     */
    @Override
    public int insertForumMessageReply(ForumMessageReply forumMessageReply)
    {
        forumMessageReply.setCreateTime(DateUtils.getNowDate());
        forumMessageReply.setCreateUser(ShiroUtils.getUserId());
        return forumMessageReplyMapper.insertForumMessageReply(forumMessageReply);
    }

    /**
     * 修改论坛帖子回复
     * 
     * @param forumMessageReply 论坛帖子回复
     * @return 结果
     */
    @Override
    public int updateForumMessageReply(ForumMessageReply forumMessageReply)
    {
        forumMessageReply.setUpdateTime(DateUtils.getNowDate());
        return forumMessageReplyMapper.updateForumMessageReply(forumMessageReply);
    }

    /**
     * 删除论坛帖子回复对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteForumMessageReplyByIds(String ids)
    {
        return forumMessageReplyMapper.deleteForumMessageReplyByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除论坛帖子回复信息
     * 
     * @param id 论坛帖子回复ID
     * @return 结果
     */
    @Override
    public int deleteForumMessageReplyById(Long id)
    {
        return forumMessageReplyMapper.deleteForumMessageReplyById(id);
    }

}
