package com.example.mongo_demo.controller;

import com.example.mongo_demo.pojo.enty.Comment;
import com.example.mongo_demo.pojo.vo.CommentVo;
import com.example.mongo_demo.service.springboot.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author duxin
 * @date 2022年07月28日 14:12:42
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    /**
     * 保存
     *
     * @param commentVo commentVo
     */
    @PostMapping("/save")
    public void save(@RequestBody CommentVo commentVo) {
        commentService.save(commentVo);
    }

    /**
     * 删除
     *
     * @param commentVo commentVo
     */
    @PostMapping("/deleted")
    public void deleted(CommentVo commentVo) {
        commentService.deleted(commentVo);
    }

    /**
     * 查询
     *
     * @return list
     */
    @GetMapping("selectAll")
    public List<Comment> selectAll() {
        return commentService.selectAll();
    }

    /**
     * id条件查询
     *
     * @param id id
     * @return Comment
     */
    @GetMapping("selectById")
    public Comment selectById(String id) {
        return commentService.selectById(id);
    }

    /**
     * id条件查询
     *
     * @param id id
     * @return Comment
     */
    @GetMapping("findByUserid")
    public Page<Comment> findByUserid(String id, int page, int size) {
        return commentService.findByUserid(id, page, size);
    }


    /**
     * id条件查询
     *
     * @return Comment
     */
    @GetMapping("findAllByTemplate")
    public List<Comment> findAllByTemplate() {
        return commentService.selectAllByMongoTemplate();
    }

}

