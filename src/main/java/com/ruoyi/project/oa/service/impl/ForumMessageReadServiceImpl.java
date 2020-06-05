package com.ruoyi.project.oa.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.oa.domain.ForumMessageRead;
import com.ruoyi.project.oa.mapper.ForumMessageReadMapper;
import com.ruoyi.project.oa.service.IForumMessageReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.utils.text.Convert;

/**
 * 帖子阅读量Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-04-18
 */
@Service
public class ForumMessageReadServiceImpl implements IForumMessageReadService
{
    @Autowired
    private ForumMessageReadMapper forumMessageReadMapper;

    /**
     * 查询帖子阅读量
     * 
     * @param id 帖子阅读量ID
     * @return 帖子阅读量
     */
    @Override
    public ForumMessageRead selectForumMessageReadById(Long id)
    {
        return forumMessageReadMapper.selectForumMessageReadById(id);
    }

    /**
     * 查询帖子阅读量列表
     * 
     * @param forumMessageRead 帖子阅读量
     * @return 帖子阅读量
     */
    @Override
    public List<ForumMessageRead> selectForumMessageReadList(ForumMessageRead forumMessageRead)
    {
        return forumMessageReadMapper.selectForumMessageReadList(forumMessageRead);
    }

    /**
     * 新增帖子阅读量
     * 
     * @param forumMessageRead 帖子阅读量
     * @return 结果
     */
    @Override
    public int insertForumMessageRead(ForumMessageRead forumMessageRead)
    {
        forumMessageRead.setCreateTime(DateUtils.getNowDate());
        return forumMessageReadMapper.insertForumMessageRead(forumMessageRead);
    }

    /**
     * 修改帖子阅读量
     * 
     * @param forumMessageRead 帖子阅读量
     * @return 结果
     */
    @Override
    public int updateForumMessageRead(ForumMessageRead forumMessageRead)
    {
        forumMessageRead.setUpdateTime(DateUtils.getNowDate());
        return forumMessageReadMapper.updateForumMessageRead(forumMessageRead);
    }

    /**
     * 删除帖子阅读量对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteForumMessageReadByIds(String ids)
    {
        return forumMessageReadMapper.deleteForumMessageReadByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除帖子阅读量信息
     * 
     * @param id 帖子阅读量ID
     * @return 结果
     */
    @Override
    public int deleteForumMessageReadById(Long id)
    {
        return forumMessageReadMapper.deleteForumMessageReadById(id);
    }

    @Override
    public List<ForumMessageRead> selectForum(ForumMessageRead forumMessageRead) {
        return forumMessageReadMapper.selectForum(forumMessageRead);
    }
}
