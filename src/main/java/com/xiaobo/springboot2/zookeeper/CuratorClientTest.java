package com.xiaobo.springboot2.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.data.Stat;

public class CuratorClientTest {

    public static void main(String[] args) throws Exception {
        // 创建节点
        CuratorFramework curatorFramework = CuratorClientUtils.getInstance();
       /* String s = curatorFramework.create()
                // 同时创建父节点与子节点
                .creatingParentContainersIfNeeded()
                // PERSISTENT 永久节点  EPHEMERAL 临时节点
                .withMode(CreateMode.PERSISTENT)
                .forPath("/curator/curator1/curator12", "321".getBytes());
        System.out.println("创建节点: "+s + "内容为 321");
        curatorFramework.delete().deletingChildrenIfNeeded().forPath("/curator");
        System.out.println("删除成功!");*/
        Stat stat = new Stat();
        byte[] bytes = curatorFramework.getData().storingStatIn(stat).forPath("/curator/curator1/curator12");
        System.out.println(bytes.toString());
        curatorFramework.setData().forPath("/curator/curator1/curator12", "321".getBytes());


    }

}
