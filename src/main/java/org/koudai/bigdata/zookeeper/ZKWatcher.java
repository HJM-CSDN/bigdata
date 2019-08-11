package org.koudai.bigdata.zookeeper;

/**
 * @description zookeeper监控者
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/9/19 15:29
 */

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
public class ZKWatcher implements Watcher {

    @Override
    public void process(WatchedEvent watchedEvent) {
//        五种事件类型
//        None(-1),
//        NodeCreated(1),
//        NodeDeleted(2),
//        NodeDataChanged(3),
//        NodeChildrenChanged(4);

        if (watchedEvent.getType().getIntValue()==1){
            System.out.println(watchedEvent.getPath() + "节点增加了");
        }

        if (watchedEvent.getType().getIntValue()==2){
            System.out.println(watchedEvent.getPath() + "节点删除了~~~~~~~~~~~");
        }

        if (watchedEvent.getType().getIntValue()==3){
            System.out.println(watchedEvent.getPath() + "节点内容发生变化了");
        }

        if (watchedEvent.getType().getIntValue()==4){
            System.out.println(watchedEvent.getPath() + "节点的子节点发生变化了");
        }
    }
}
