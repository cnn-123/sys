package com.example.sys.demo.controller;


import com.example.sys.demo.VO.CollectionVO;
import com.example.sys.demo.common.JSONResult;
import com.example.sys.demo.entity.Collection;
import com.example.sys.demo.service.impl.CollectionServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 陈莉
 * @since 2020-02-18
 */
@Api(value = "用户收藏业务")
@RestController
@RequestMapping("/demo/collection")
public class CollectionController {
    @Autowired
    private CollectionServiceImpl collectionService;

    /**用户收藏视频并保存至数据库*/
    @ApiOperation(value = "用户收藏视频",notes = "用户收藏视频接口")
    @PostMapping("/saveCollection")
    public JSONResult saveCollection(@RequestBody Collection collection){
        collection.setCollectionTime(LocalDateTime.now());
        collectionService.saveCollection(collection);
        return JSONResult.ok();
    }

    /**请求用户收藏的视频数据*/
    @ApiOperation(value = "用户请求收藏视频数据",notes = "用户请求收藏视频数据接口")
    @ApiImplicitParam(name = "userId",value = "用户id",dataType = "int",
             paramType = "query",required = true)
    @PostMapping("/selectCollection")
    public JSONResult selectCollection(Integer userId){
        List<CollectionVO> collectionVOList = collectionService.selectAllList(userId);
        return JSONResult.ok(collectionVOList);
    }


    /**删除收藏视频*/
    @ApiOperation(value = "用户删除收藏视频",notes = "用户删除收藏视频接口")
    @ApiImplicitParam(name = "collectionId",value = "收藏id",dataType = "int",
            paramType = "query",required = true)
    @PostMapping("/deleteCollection")
    public JSONResult deleteCollection(Integer collectionId){
        int param = collectionService.deleteCollection(collectionId);
        return JSONResult.ok(param);
    }

    /**请求视频收藏状态*/
    @PostMapping("/getStatus")
    public JSONResult getStatus(Integer userId,Integer videoId){
        Collection collection = collectionService.selectOne(userId,videoId);
        if (collection==null){
            return JSONResult.ok(false);
        }else {
            return JSONResult.ok(true);
        }
    }
}


