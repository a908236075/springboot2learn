package com.xiaobo.springboot2.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
 
/**
 * Curator客户端工具类
 */
public class CuratorClientUtils {
 
    // 集群连接地址
    private final static  String CONNECTSTRING = "192.168.8.128:2181";
 
    private static CuratorFramework curatorFramework;
 
    public static CuratorFramework getInstance(){
        curatorFramework = CuratorFrameworkFactory.newClient(
                CONNECTSTRING,5000,5000,new ExponentialBackoffRetry(1000,3));
        curatorFramework.start();
        return curatorFramework;
    }
}