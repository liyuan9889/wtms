package com.ruoyi.project.oa.mapper;

import java.util.List;

import com.ruoyi.project.oa.domain.Forum;

/**
 * 论坛Mapper接口
 * 
 * @author ruoyi
 * @date 2020-04-18
 */
public interface ForumMapper 
{
    /**
     * 查询论坛
     * 
     * @param id 论坛ID
     * @return 论坛
     */
    public Forum selectForumById(Long id);

    /**
     * 查询论坛列表
     * 
     * @param forum 论坛
     * @return 论坛集合
     */
    public List<Forum> selectForumList(Forum forum);

    /**
     * 新增论坛
     * 
     * @param forum 论坛
     * @return 结果
     */
    public int insertForum(Forum forum);

    /**
     * 修改论坛
     * 
     * @param forum 论坛
     * @return 结果
     */
    public int updateForum(Forum forum);

    /**
     * 删除论坛
     * 
     * @param id 论坛ID
     * @return 结果
     */
    public int deleteForumById(Forum id);

    /**
     * 批量删除论坛
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteForumByIds(List<Forum> ids);

    /**
     * 根据title查询数据
     * @param title
     * @return
     */
    public Forum selectForumByTitle(String title);
}
