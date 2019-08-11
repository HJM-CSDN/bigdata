package org.koudai.bigdata.zookeeper;

import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

/**
 * @description 完整ZK应用程序示例
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/9/19 15:27
 */
public class ZKApp {
    public static void main(String[] args) throws Exception{
        String zkURI = "min1:2181,min2:2181,min3:2181";
        ZooKeeper zkCli = ZKOperator.getZKCli(zkURI, 2000);
        CountDownLatch latch = new CountDownLatch(1);
        //监听节点上下线
        zkCli.exists("/ttt",new ZKWatcher());
        //监听内容变化
        zkCli.getData("/ttt",new ZKWatcher(),null);
        //监听子节点变化
        zkCli.getChildren("/ttt",new ZKWatcher(),null);
        latch.await();
    }
}
