package com.example.mongo_demo.service.springboot;

import com.example.mongo_demo.pojo.enty.Comment;
import com.example.mongo_demo.pojo.vo.CommentVo;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author duxin
 * @date 2022年07月28日 13:51:44
 */
public interface CommentService {

    /**
     * 保存(包含插入、更新)
     *
     * @param commentVo commentVo
     */
    void save(CommentVo commentVo);

    /**
     * 插入
     *
     * @param commentVo commentVo
     */
    void insert(CommentVo commentVo);

    /**
     * 更新
     *
     * @param commentVo commentVo
     */
    void update(CommentVo commentVo);

    /**
     * 删除
     *
     * @param commentVo commentVo
     */
    void deleted(CommentVo commentVo);

    /**
     * 查询
     *
     * @return list
     */
    List<Comment> selectAll();


    /**
     * 查询
     *
     * @param id id
     * @return Comment
     */
    Comment selectById(String id);


    /**
     * 父级id查询
     *
     * @param parentId parentId
     * @param page page
     * @param size size
     * @return Comment
     */
    Page<Comment> findByUserid(String parentId , int page , int size);

    /**
     * 使用template查询
     *
     * @return List<Comment>
     */
    List<Comment> selectAllByMongoTemplate();
}
