package com.xiaobo.springboot2.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.TimeUnit;

public class CuratorClientWatch {
    public static void main(String[] args) throws Exception {
        CuratorFramework curatorFramework = CuratorClientUtils.getInstance();
        PathChildrenCache cache = new PathChildrenCache(curatorFramework, "/curator", true);
        cache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        cache.getListenable().addListener((curatorFramework1, pathChildrenCacheEvent) -> {

            switch (pathChildrenCacheEvent.getType()) {
                case CHILD_ADDED:
                    System.out.println("增加了子节点");
                    break;
                case CHILD_REMOVED:
                    System.out.println("删除了子节点");
                    break;
                case CHILD_UPDATED:
                    System.out.println("更新了子节点");
                    break;
                default:
                    break;
            }
        });
        curatorFramework.create().withMode(CreateMode.PERSISTENT).forPath("/curator/curator2", "event".getBytes());
        TimeUnit.SECONDS.sleep(1);
        curatorFramework.create().withMode(CreateMode.EPHEMERAL).forPath("/curator/curator3", "1".getBytes());
        TimeUnit.SECONDS.sleep(1);
        curatorFramework.setData().forPath("/curator/curator3", "2".getBytes());
        TimeUnit.SECONDS.sleep(1);
        curatorFramework.delete().forPath("/curator/curator3");
        System.in.read();


    }
}
