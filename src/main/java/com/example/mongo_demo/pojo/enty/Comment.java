package com.example.mongo_demo.pojo.enty;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;

/**
 * @author duxin
 * @date 2022年07月27日 17:07:51
 */
@Data
@Document(collection="comment")
public class Comment implements Serializable {

    /**
     * 吐槽内容
     */
    @Field("content")
    private String content;
    /**
     * 发布日期
     */
    private Date publishtime;

    /**
     * 发布人ID
     */
    @Indexed
    private String userid;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 评论的日期时间
     */
    private Date createdatetime;
    /**
     * 点赞数
     */
    private Integer likenum;
    /**
     * 回复数
     */
    private Integer replynum;
    /**
     * 状态
     */
    private String state;
    /**
     * 上级ID
     */
    private String parentid;
    /**
     * 文章id
     */
    private String articleid;
}
