package org.koudai.bigdata.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import  org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.List;

/**
 * @Description: zookeeperOperator
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/9/19 10:31
 */

public class ZKOperator {

    /**
     * 程序入口
     * @param args
     */
    public static void main(String[] args)throws Exception {
        String zkURI = "min1:2181,"+"min2:2181,"+"min3:2181";
        System.out.println("开始连接zookeeper集群");
        long start = System.currentTimeMillis();
        ZooKeeper zkCli = getZKCli(zkURI, 2000);
        long stop = System.currentTimeMillis();
        System.out.println("连接ZK耗时：" + (stop-start));
//        //创建临时节点
//        boolean createResult = createZnode(zkCli, "/test", "千寻2", true);
//        if (createResult){
//            System.out.println("创建临时节点成功！");
//        }
//        Thread.sleep(10*1000);
        //删除给定节点
//        if (deleteZnode(zkCli,"/test",1)){
//            System.out.println("节点/test删除成功");
//        }else {
//            System.out.println("节点/test删除失败");
//        }

        //        //修改指定节点内容
        if(updateZnode(zkCli,"/test","我胡哥",0)){
            System.out.println("/test节点内容修改成功");
        }

//        //查询指定节点的内容
        showZnodeInfo(zkCli,"/test");

//        //查询指定节点是否存在
        if (checkZnodeExist(zkCli,"/test")){
            System.out.println("节点存在");
        }
    }

    /**
     * 使用给定的zk客户端创建指定名称、内容、类型的znode
     * @param zkCli 给定的zk客户端
     * @param znodeAbsPath 要创建的节点的绝对路径
     * @param znodeContent 要创建的节点的内容
     * @param isEPHEMERAL 节点的类型是否是临时节点
     * @return 创建操作是否成功
     */
    public static boolean createZnode(ZooKeeper zkCli,String znodeAbsPath,String znodeContent,boolean isEPHEMERAL){
        CreateMode znodeType = null;
        if (isEPHEMERAL){
            znodeType = CreateMode.EPHEMERAL;
        }else {
            znodeType = CreateMode.PERSISTENT;
        }
        try{
            zkCli.create(znodeAbsPath,znodeContent.getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE,znodeType);
            return  true;
        }catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return  false;
    }

    /**
     * 使用给定的zk客户端删除指定节点
     * @param zkCli 给定的zk客户端
     * @param znodeAbsPath 指定的zk节点
     * @param nodeDataVersion 指定节点的版本
     * @return 删除操作是否成功
     */
    public static boolean deleteZnode(ZooKeeper zkCli,String znodeAbsPath,int nodeDataVersion){
        try {
            zkCli.delete(znodeAbsPath,nodeDataVersion);
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 使用给定的zk客户端和给定的内容来更新指定的znode
     * @param zkCli 给定的zk客户端
     * @param znodeAbsPath 要更新的znode的绝对路径
     * @param znodeContent 要更新的内容
     * @param nodeDataVersion 指定节点的版本
     * @return 更新操作的结果
     */
    public static boolean updateZnode(ZooKeeper zkCli,String znodeAbsPath,String znodeContent,int nodeDataVersion){
        try {
            zkCli.setData(znodeAbsPath,znodeContent.getBytes(),nodeDataVersion);
            return true;
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 使用给定的zk客户端查询指定节点的详情
     * @param zkCli 给定zk客户端
     * @param znodeAbsPath 要查询的节点的绝对路径
     */
    public static void showZnodeInfo(ZooKeeper zkCli,String znodeAbsPath) {
        try {
            Stat stat = new Stat();
            System.out.println("本次连接的SessionID：" + zkCli.getSessionId());
            System.out.println(znodeAbsPath + "节点的内容：" + new String(zkCli.getData(znodeAbsPath,false,stat)));
            System.out.println(znodeAbsPath + "节点的元信息：" + stat.toString());
            List<String> childrens = zkCli.getChildren(znodeAbsPath, false);
            System.out.println(znodeAbsPath + "节点的所有子节点如下：");
            for (int i = 0; i < childrens.size(); i++) {
                System.out.println(childrens.get(i));
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     *  使用给定的zk客户端检测指定的节点是否存在
     *  true：表示存在
     *  false：表示不存在
     * @param zkCli 给定的zk客户端
     * @param znodeAbsPath 给定的zknode
     * @return 节点存在与否的结果
     */
    public static boolean checkZnodeExist(ZooKeeper zkCli,String znodeAbsPath){
        try {
            return zkCli.exists(znodeAbsPath, false)==null?false:true;
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 连接到一个指定的zk集群，并返回连接好的客户端
     * @param zkURI zk集群地址
     * @param timeOut 连接时候的超时时间
     * @return 连接好的客户端
     */
    public static ZooKeeper getZKCli(String zkURI,int timeOut) throws Exception{
        ZooKeeper zkCli = new ZooKeeper(zkURI, timeOut, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("==========" + watchedEvent.getType() + "====" + watchedEvent.getState());
            }
        });
        //连接状态值   CONNECTING, ASSOCIATING, CONNECTED, CONNECTEDREADONLY,
//        System.out.println("-------------------" + zkCli.getState());

        //等待zk连接完成并返回连接好的zkCli对象
        while(true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //等待zookeeper客户端连接服务器成功的，查看连接时的状态信息。
            if (zkCli.getState().isConnected()){
                return zkCli;
            }
        }
    }
}
