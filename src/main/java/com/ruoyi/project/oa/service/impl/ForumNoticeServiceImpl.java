package com.ruoyi.project.oa.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.project.oa.domain.ForumNotice;
import com.ruoyi.project.oa.mapper.ForumNoticeMapper;
import com.ruoyi.project.oa.service.IForumNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 论坛公告Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-04-25
 */
@Service
public class ForumNoticeServiceImpl implements IForumNoticeService
{
    @Autowired
    private ForumNoticeMapper forumNoticeMapper;

    /**
     * 查询论坛公告
     * 
     * @param forumNoticeId 论坛公告ID
     * @return 论坛公告
     */
    @Override
    public ForumNotice selectForumNoticeById(Long forumNoticeId)
    {
        return forumNoticeMapper.selectForumNoticeById(forumNoticeId);
    }

    /**
     * 查询论坛公告列表
     * 
     * @param forumNotice 论坛公告
     * @return 论坛公告
     */
    @Override
    public List<ForumNotice> selectForumNoticeList(ForumNotice forumNotice)
    {
        return forumNoticeMapper.selectForumNoticeList(forumNotice);
    }

    /**
     * 新增论坛公告
     * 
     * @param forumNotice 论坛公告
     * @return 结果
     */
    @Override
    public int insertForumNotice(ForumNotice forumNotice)
    {
        forumNotice.setCreateTime(DateUtils.getNowDate());
        return forumNoticeMapper.insertForumNotice(forumNotice);
    }

    /**
     * 修改论坛公告
     * 
     * @param forumNotice 论坛公告
     * @return 结果
     */
    @Override
    public int updateForumNotice(ForumNotice forumNotice)
    {
        forumNotice.setUpdateTime(DateUtils.getNowDate());
        return forumNoticeMapper.updateForumNotice(forumNotice);
    }

    /**
     * 删除论坛公告对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteForumNoticeByIds(String ids)
    {
        return forumNoticeMapper.deleteForumNoticeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除论坛公告信息
     * 
     * @param forumNoticeId 论坛公告ID
     * @return 结果
     */
    @Override
    public int deleteForumNoticeById(Long forumNoticeId)
    {
        return forumNoticeMapper.deleteForumNoticeById(forumNoticeId);
    }
}
