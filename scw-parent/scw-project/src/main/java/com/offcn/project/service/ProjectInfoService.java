package com.offcn.project.service;

import com.offcn.project.pojo.*;

import java.util.List;

/**
 * @Auther: lhq
 * @Date: 2021/4/16 09:45
 * @Description:   查询项目的接口
 */
public interface ProjectInfoService {


    /**
     * 根据项目ID查询回报增量列表
     * @param projectId
     * @return
     */
    public List<TReturn> findReturnList(Integer projectId);

    public List<TTag> findAllTags();

    public List<TType> findAllTypes();


    /**
     * 查询所有项目列表
     * @return
     */
    public List<TProject> findAllProject();


    /**
     * 根据项目ID查询图片信息
     * @param projectId
     * @return
     */
    public List<TProjectImages> findImageByProjectId(Integer projectId);

    /**
     * 查询项目详情
     * @param projectId
     * @return
     */
    public TProject findProjectById(Integer projectId);


    /**
     * 查询回报增量详情
     * @param returnId
     * @return
     */
    public TReturn findReturnById(Integer returnId);
}
