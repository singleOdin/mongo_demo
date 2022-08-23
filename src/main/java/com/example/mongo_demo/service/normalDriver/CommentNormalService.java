package com.example.mongo_demo.service.normalDriver;

import com.mongodb.client.MongoClient;

/**
 * @author duxin
 * @date 2022年08月22日 09:42:12
 */
public interface CommentNormalService {

    /**
     * 连接方式1
     * @return MongoClient
     */
    MongoClient connection1();

    /**
     * 连接方式2
     * @return MongoClient
     */
    MongoClient connection2();

    /**
     * 连接方式3
     * @return MongoClient
     */
    MongoClient connection3();

    /**
     * 连接方式4
     * @return MongoClient
     */
    MongoClient connection4();

    /**
     * 连接方式 TLS/LLS
     * @param connection str/enable/x509Manager/allow
     * @return MongoClient
     */
    MongoClient connection5(String connection);

}
