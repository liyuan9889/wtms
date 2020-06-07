package com.ruoyi.project.oa.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.project.oa.domain.ForumMessage;
import com.ruoyi.project.oa.mapper.ForumMessageMapper;
import com.ruoyi.project.oa.service.IForumMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 论坛帖子Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-04-18
 */
@Service
public class ForumMessageServiceImpl implements IForumMessageService
{
    @Autowired
    private ForumMessageMapper forumMessageMapper;

    /**
     * 查询论坛帖子
     * 
     * @param id 论坛帖子ID
     * @return 论坛帖子
     */
    @Override
    public ForumMessage selectForumMessageById(Long id)
    {
        return forumMessageMapper.selectForumMessageById(id);
    }

    /**
     * 查询论坛帖子列表
     * 
     * @param forumMessage 论坛帖子
     * @return 论坛帖子
     */
    @Override
    public List<ForumMessage> selectForumMessageList(ForumMessage forumMessage)
    {
        return forumMessageMapper.selectForumMessageList(forumMessage);
    }

    /**
     * 新增论坛帖子
     * 
     * @param forumMessage 论坛帖子
     * @return 结果
     */
    @Override
    public int insertForumMessage(ForumMessage forumMessage)
    {
        forumMessage.setCreateTime(DateUtils.getNowDate());
        return forumMessageMapper.insertForumMessage(forumMessage);
    }

    /**
     * 修改论坛帖子
     * 
     * @param forumMessage 论坛帖子
     * @return 结果
     */
    @Override
    public int updateForumMessage(ForumMessage forumMessage)
    {
        forumMessage.setUpdateTime(DateUtils.getNowDate());
        forumMessage.setUpdateUser(ShiroUtils.getUserId());
        forumMessage.setZdCreateTime(DateUtils.getNowDate());
        return forumMessageMapper.updateForumMessage(forumMessage);
    }

    /**
     * 删除论坛帖子对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteForumMessageByIds(String ids)
    {
        return forumMessageMapper.deleteForumMessageByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除论坛帖子信息
     * 
     * @param id 论坛帖子ID
     * @return 结果
     */
    @Override
    public int deleteForumMessageById(Long id)
    {
        return forumMessageMapper.deleteForumMessageById(id);
    }

    /**
     * 查询帖子的数量
     * @param forumMessage
     * @return
     */
    @Override
    public int selectForumMessageNum(ForumMessage forumMessage) {
        return forumMessageMapper.selectForumMessageNum(forumMessage);
    }

    @Override
    public List<ForumMessage> selectForumMessageHotList(ForumMessage forumMessage) {
        return forumMessageMapper.selectForumMessageHotList(forumMessage);
    }

    @Override
    public List<ForumMessage> getZdList(ForumMessage forumMessage) {
        return forumMessageMapper.getZdList(forumMessage);
    }

    @Override
    public List<ForumMessage> getClassZdList(ForumMessage forumMessage) {
        return forumMessageMapper.getClassZdList(forumMessage);
    }

    @Override
    public List<ForumMessage> selectClassForumMessageList(ForumMessage forumMessage) {
        return forumMessageMapper.selectClassForumMessageList(forumMessage);
    }

    @Override
    public ForumMessage selectClassForumMessageById(Long id) {
        return forumMessageMapper.selectClassForumMessageById(id);
    }

    @Override
    public int selectClassForumMessageNum(ForumMessage forumMessage) {
        return forumMessageMapper.selectClassForumMessageNum(forumMessage);
    }

    @Override
    public List<ForumMessage> selectClassForumMessageHotList(ForumMessage forumMessage) {
        return forumMessageMapper.selectClassForumMessageHotList(forumMessage);
    }
}
