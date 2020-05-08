package com.example.sys.demo.service;

import com.example.sys.demo.VO.CollectionVO;
import com.example.sys.demo.entity.Collection;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.sys.demo.entity.College;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 陈莉
 * @since 2020-02-18
 */
public interface ICollectionService extends IService<Collection> {

    /**用户收藏视频并保存数据至数据库*/
    void saveCollection(Collection collection);

    /**删除收藏视频*/
    int deleteCollection(Integer collectionId);

    /**求用户收藏的视频数据*/
    List<CollectionVO> selectAllList(Integer userId);

    /**请求视频收藏状态*/
    Collection selectOne(Integer userId,Integer videoId);



}
