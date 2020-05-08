package com.example.sys.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.sys.demo.VO.CollectionVO;
import com.example.sys.demo.entity.Collection;
import com.example.sys.demo.mapper.CollectionMapper;
import com.example.sys.demo.service.ICollectionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 陈莉
 * @since 2020-02-18
 */
@Service
public class CollectionServiceImpl extends ServiceImpl<CollectionMapper, Collection> implements ICollectionService {

    @Autowired
    private  CollectionMapper collectionMapper;

    @Override
    public void saveCollection(Collection collection) {
        collectionMapper.insert(collection);
    }

    @Override
    public int deleteCollection(Integer collectionId) {
        int list = collectionMapper.deleteById(collectionId);
        return list;
    }

    @Override
    public List<CollectionVO> selectAllList(Integer userId) {
        List<CollectionVO> collectionVOList = collectionMapper.selectAll(userId);
        return collectionVOList;
    }

    @Override
    public Collection selectOne(Integer userId, Integer videoId) {
        QueryWrapper<Collection> queryWrapper = new QueryWrapper<Collection>();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("video_id",videoId);
        Collection one = collectionMapper.selectOne(queryWrapper);
        return one;
    }
}
