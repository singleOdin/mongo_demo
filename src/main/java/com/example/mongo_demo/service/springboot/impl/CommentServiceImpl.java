package com.example.mongo_demo.service.springboot.impl;

import com.example.mongo_demo.dao.CommentDao;
import com.example.mongo_demo.pojo.enty.Comment;
import com.example.mongo_demo.pojo.vo.CommentVo;
import com.example.mongo_demo.service.springboot.CommentService;
import com.mongodb.client.MongoCollection;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author duxin
 * @date 2022年07月28日 13:52:06
 */
@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentDao commentDao;

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public void save(CommentVo commentVo) {
        commentDao.save(commentVo);
    }

    @Override
    public void insert(CommentVo commentVo) {
        commentDao.insert(commentVo);
    }

    @Override
    public void update(CommentVo commentVo) {
        commentDao.save(commentVo);
    }

    @Override
    public void deleted(CommentVo commentVo) {
        commentDao.delete(commentVo);
    }

    @Override
    public List<Comment> selectAll() {
        return commentDao.findAll();
    }

    @Override
    public Comment selectById(String id) {
        if (commentDao.findById(id).isPresent()) {
            return commentDao.findById(id).get();
        }
        return null;
    }

    @Override
    public Page<Comment> findByUserid(String id, int page , int size) {
        return commentDao.findByUserid(id, PageRequest.of(page-1, size));
    }

    @Override
    public List<Comment> selectAllByMongoTemplate() {
        return mongoTemplate.findAll(Comment.class, mongoTemplate.getCollectionName(Comment.class));
    }

    public void selectByMongoTemplate() {
        Comment comment = new Comment();
        comment.setContent("111111");
        mongoTemplate.save(comment);
    }
}
