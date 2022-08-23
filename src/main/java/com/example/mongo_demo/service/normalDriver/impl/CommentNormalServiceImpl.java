package com.example.mongo_demo.service.normalDriver.impl;

import com.example.mongo_demo.service.normalDriver.CommentNormalService;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author duxin
 * @date 2022年08月22日 09:42:42
 */
public class CommentNormalServiceImpl implements CommentNormalService {

    private final static String STR = "str";
    private final static String ENABLE = "enable";
    private final static String X509MANAGER = "x509Manager";
    private final static String ALLOW = "allow";


    @Override
    public MongoClient connection1() {
        //实例化一个不带任何参数的MongoClient对象，以连接到端口在本地主机上运行的MongoDB实例27017
        return MongoClients.create();
    }

    @Override
    public MongoClient connection2() {
        //显式指定主机名，以连接到在端口上的指定主机上运行的MongoDB实例27017
        return MongoClients.create(MongoClientSettings.builder()
                .applyToClusterSettings(builder -> builder.hosts(Collections.singletonList(new ServerAddress("121.40.118.159"))))
                .build());
    }

    @Override
    public MongoClient connection3() {
        //显式指定主机名，以连接到在端口上的指定主机IP与指定端口号上运行的MongoDB实例
        return MongoClients.create(MongoClientSettings.builder()
                .applyToClusterSettings(builder -> builder.hosts(Collections.singletonList(new ServerAddress("121.40.118.159", 27017))))
                .build());
    }

    @Override
    public MongoClient connection4() {
        return MongoClients.create("mongodb://121.40.118.159:27017");
    }

    @Override
    public MongoClient connection5(String connection) {
        //TLS/LLS
        MongoClient mongoClient = null;
        if (STR.equals(connection)) {
            //1.指定TLS/SSL ConnectionString 将ssl=true/false 或 tls=true/false
            mongoClient = MongoClients.create("mongodb://121.40.118.159:27017/?ssl=true");
        }
        if (ENABLE.equals(connection)) {
            //2.指定TLS/SSL ConnectionSettings，将enable属性设为true
            MongoClientSettings build = MongoClientSettings.builder().applyToSslSettings(builder -> builder.enabled(true)).build();
            mongoClient = MongoClients.create(build);
        }
        if (X509MANAGER.equals(connection)) {
            // SSLContext: 此类的实例表示安全套接字协议的实现， 它是SSLSocketFactory、SSLServerSocketFactory和SSLEngine的工厂。
            // 通过 SSLContext 指定 MongoClientSettings
            X509TrustManager x509TrustManager = new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {}

                @Override
                public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {}

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };

            SSLContext sslContext = null;

            try {
                sslContext = SSLContext.getInstance("SSL");
                //初始化SSLContext实例
                sslContext.init(null, new TrustManager[]{x509TrustManager}, new SecureRandom());
            } catch (Exception e) {
                e.printStackTrace();
            }

            SSLContext finalSslContext = sslContext;
            MongoClientSettings settings = MongoClientSettings.builder().applyToSslSettings(builder -> {
                builder.enabled(true);
                builder.context(finalSslContext);
            }).build();

            mongoClient = MongoClients.create(settings);
        }
        if (ALLOW.equals(connection)) {
            // 禁用主机名验证
            // 默认情况下，驱动程序确保服务器SSL证书中包含的主机名与构造时提供的主机名匹配
            MongoClientSettings build = MongoClientSettings.builder().applyToSslSettings(builder -> {
                builder.enabled(true);
                builder.invalidHostNameAllowed(true);
            }).build();
            mongoClient = MongoClients.create(build);
        }

        return mongoClient;
    }
}
