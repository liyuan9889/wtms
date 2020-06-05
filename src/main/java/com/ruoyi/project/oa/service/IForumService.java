package com.ruoyi.project.oa.service;


import com.ruoyi.project.oa.domain.Forum;

import java.util.List;

/**
 * 论坛Service接口
 * 
 * @author ruoyi
 * @date 2020-04-18
 */
public interface IForumService 
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
     * 批量删除论坛
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteForumByIds(String ids);

    /**
     * 删除论坛信息
     * 
     * @param id 论坛ID
     * @return 结果
     */
    public int deleteForumById(Long id);

    /**
     *  校验论坛名称是否唯一
     * @param title
     * @return
     */
    public String checkForumByTitle(String title);

    /**
     *  校验论坛名称是否唯一
     * @param title
     * @return
     */
    public String checkForumUpdateByTitle(String title);
}
