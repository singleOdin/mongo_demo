package com.example.mongo_demo.dao;

import com.example.mongo_demo.pojo.enty.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author duxin
 * @date 2022年07月28日 13:50:20
 */
public interface CommentDao extends MongoRepository<Comment, String> {

    /**
     * 父级id条件查询
     *
     * @param parentid parentid
     * @param pageable pageable
     * @return Page
     */
    Page<Comment> findByUserid(String parentid , Pageable pageable);

}
