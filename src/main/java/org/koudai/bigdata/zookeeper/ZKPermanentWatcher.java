package org.koudai.bigdata.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

/**
 * @description 持久化监听器示例
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/9/19 20:48
 */
public class ZKPermanentWatcher {
    public static void main(String[] args) throws Exception{
        String zkURI = "min1:2181,min2:2181,min3:2181";
        ZooKeeper zkCli = ZKOperator.getZKCli(zkURI,2000);
        CountDownLatch latch = new CountDownLatch(1);//闭锁
        //持久化监控节点上下线
        permanentWatchOnLineOffline(zkCli,"/h");
        //持久化监控节点内容变化
        permanentWatchContentChange(zkCli,"/h");
        latch.await();
    }
    /**
     * 使用给定的zk客户端持久化监听给定节点的上下线情况
     * @param zkCli 给定的zk客户端
     * @param znodeAbspath 要监听的节点的绝对路径
     */
    public static void permanentWatchOnLineOffline(ZooKeeper zkCli,String znodeAbspath) throws Exception{
        zkCli.exists(znodeAbspath, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                if (watchedEvent.getType().getIntValue()==1){
                    System.out.println("节点：" + watchedEvent.getPath() + "\t上线了");
                }else{
                    System.out.println("节点：" + watchedEvent.getPath() + "\t下线了");
                }
                try {
                    //重新监听上下线事件
                    zkCli.exists(znodeAbspath,this);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 使用给定的zk客户端持久化监控文件内容的变化
     * @param zkCli 给定的zk客户端
     * @param znodeAbspath 要监控的节点
     * @throws Exception
     */
    public static void permanentWatchContentChange(ZooKeeper zkCli,String znodeAbspath) throws Exception{
        zkCli.getData(znodeAbspath, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                if (watchedEvent.getType().getIntValue()==3){
                    try {
                        System.out.println("节点：" + watchedEvent.getPath() + "的内容变成" + new String(zkCli.getData(znodeAbspath,false,null)) +"了");
                        zkCli.getData(znodeAbspath,this,null);
                    } catch (KeeperException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },null);
    }
}

