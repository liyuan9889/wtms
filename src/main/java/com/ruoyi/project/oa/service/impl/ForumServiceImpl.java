package com.ruoyi.project.oa.service.impl;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.project.oa.domain.Forum;
import com.ruoyi.project.oa.mapper.ForumMapper;
import com.ruoyi.project.oa.service.IForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 论坛Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-04-18
 */
@Service
public class ForumServiceImpl implements IForumService
{
    @Autowired
    private ForumMapper forumMapper;

    /**
     * 查询论坛
     * 
     * @param id 论坛ID
     * @return 论坛
     */
    @Override
    public Forum selectForumById(Long id)
    {
        return forumMapper.selectForumById(id);
    }

    /**
     * 查询论坛列表
     * 
     * @param forum 论坛
     * @return 论坛
     */
    @Override
    public List<Forum> selectForumList(Forum forum)
    {
        return forumMapper.selectForumList(forum);
    }

    /**
     * 新增论坛
     * 
     * @param forum 论坛
     * @return 结果
     */
    @Override
    public int insertForum(Forum forum)
    {
        forum.setCreateTime(DateUtils.getNowDate());
        forum.setCreateUser(ShiroUtils.getUserId());
      /*  Forum forumTitle =  forumMapper.selectForumByTitle(forum.getTitle());
        if(!StringUtils.isEmpty(forumTitle)){
            throw new BusinessException(forum.getTitle()+"已存在");
        }*/
        if(StringUtils.isEmpty(forum.getImgUrl())){
            forum.setImgUrl(null);
        }
        return forumMapper.insertForum(forum);
    }

    /**
     * 修改论坛
     * 
     * @param forum 论坛
     * @return 结果
     */
    @Override
    public int updateForum(Forum forum)
    {
        forum.setUpdateTime(DateUtils.getNowDate());
        forum.setUpdateUser(ShiroUtils.getUserId());
        if(StringUtils.isEmpty(forum.getImgUrl())){
            forum.setImgUrl(null);
        }
     /*   Forum forumTitle =  forumMapper.selectForumByTitle(forum.getTitle());
        if(!StringUtils.isEmpty(forumTitle) && !forum.getTitle().equals(forumTitle.getTitle())){
            throw new BusinessException(forum.getTitle()+"已存在");
        }*/
        return forumMapper.updateForum(forum);
    }

    /**
     * 删除论坛对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteForumByIds(String ids)
    {
        String[] idsList = Convert.toStrArray(ids);
        List<Forum> forums = new ArrayList<>();
        Arrays.asList(idsList).forEach(item ->{
            Forum forum = new Forum();
            forum.setUpdateUser(ShiroUtils.getUserId());
            forum.setUpdateTime(DateUtils.getNowDate());
            forum.setId(Long.parseLong(item));
            forum.setStatus(1L);
            forums.add(forum);
        });
        return forumMapper.deleteForumByIds(forums);
    }

    /**
     * 删除论坛信息
     * 
     * @param id 论坛ID
     * @return 结果
     */
    @Override
    public int deleteForumById(Long id)
    {
        Forum forum = new Forum();
        forum.setUpdateUser(ShiroUtils.getUserId());
        forum.setUpdateTime(DateUtils.getNowDate());
        forum.setStatus(1L);
        forum.setId(id);
        return forumMapper.deleteForumById(forum);
    }

    /**
     * 校验论坛名称是否唯一
     *
     * @param title 用户名
     * @return
     */
    @Override
    public String checkForumByTitle(String title)
    {
        Forum forum = forumMapper.selectForumByTitle(title);;
        if (!StringUtils.isEmpty(forum))
        {
            return UserConstants.USER_NAME_NOT_UNIQUE;
        }
        return UserConstants.USER_NAME_UNIQUE;
    }

    @Override
    public String checkForumUpdateByTitle(String title) {
        Forum forum = forumMapper.selectForumByTitle(title);;
        if (!StringUtils.isEmpty(forum) && !title.equals(forum.getTitle()))
        {
            return UserConstants.USER_NAME_NOT_UNIQUE;
        }
        return UserConstants.USER_NAME_UNIQUE;
    }

    @Override
    public List<Forum> getClassLastMonth(String data, String userId) {
        return forumMapper.getClassLastMonth(data,userId);
    }
}
