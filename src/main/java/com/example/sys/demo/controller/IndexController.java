package com.example.sys.demo.controller;


import com.example.sys.demo.VO.VideoVO;
import com.example.sys.demo.common.JSONResult;
import com.example.sys.demo.entity.VideoInfo;
import com.example.sys.demo.service.impl.UserinfoServiceImpl;
import com.example.sys.demo.service.impl.VideoInfoServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 陈莉
 * @since 2020-02-11
 */
@Api(value = "主页视频数据请求")
@RestController
@RequestMapping("/demo")
public class IndexController {
    @Autowired
    private VideoInfoServiceImpl videoInfoService;

    @Autowired
    private UserinfoServiceImpl userinfoService;

    /**请求视频数据*/
    @ApiOperation(value = "请求视频数据",notes = "请求视频数据接口")
    @ApiImplicitParam(name = "videoId",value = "视频id",dataType = "int",
                        paramType = "query",required = false)
    @PostMapping(value = "/recommend")
    public JSONResult recommend(Integer videoId){
        if (videoId == null) {
            List<VideoVO> videoVOList = videoInfoService.select();
            return JSONResult.ok(videoVOList);
        }else {
            VideoInfo videoInfo = videoInfoService.selectOne(videoId);
            VideoVO videoVO = new VideoVO();
            BeanUtils.copyProperties(videoInfo,videoVO);
            videoVO.setUserinfoName(userinfoService.selectList(videoInfo.getUserId()).getUserinfoName());
            return JSONResult.ok(videoVO);
        }
    }

    @PostMapping(value = "/recommendMy")
    public JSONResult recommendMy(Integer userId){
        List<VideoVO> videoVOList = videoInfoService.select();
        List<VideoVO> videoVOS = new ArrayList<>();
        for (VideoVO videoVO : videoVOList){
            String videoCollege = userinfoService.selectList(videoVO.getUserId()).getUserinfoCollege();
            String userCollege = userinfoService.selectList(userId).getUserinfoCollege();
            if (videoCollege.equals(userCollege)){
                videoVOS.add(videoVO);
            }
        }
        return JSONResult.ok(videoVOS);
    }
}
