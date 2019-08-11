package org.koudai.bigdata.hbase;

import org.apache.hadoop.hbase.NamespaceDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;

import java.util.ArrayList;
import java.util.List;

import static org.koudai.bigdata.hbase.HbaseTableDDLOperator.dropTableAutoDisable;

/**
 * @description Hbase命名空间操作员
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/10/16 19:02
 */
public class HbaseNSOperator {
    /**
     * 根据给定的NSName创建一个HbaseNS
     * @param NSName
     * @throws Exception
     */
    public static void createNamespace(String NSName)throws Exception{
        //1.获取一个Hbase连接对象
        Connection hbaseConnection = HbaseUtil.getHbaseConnetion();
        //2.获取HbaseAdmin对象
        Admin hbaseAdmin = hbaseConnection.getAdmin();
        //3.创建hbaseNamespace描述对象
        NamespaceDescriptor NS = NamespaceDescriptor.create(NSName).build();
        //4.使用hbaseAdmin对象根据hbaseNamespace描述对象创建一个hbaseNamespace
        hbaseAdmin.createNamespace(NS);
        System.out.println("命名空间" + NSName + "创建完成!");
        //关闭连接
        hbaseConnection.close();
    }

    /**
     * 列出当前Hbase集群中所有的Namespace
     * @throws Exception
     */

    /**
     * 获取给定NS下的所有表的名字
     * @param NSName
     * @return 所有表的名字
     * @throws Exception
     */
    public static List<String> getNSTables(Connection hbaseConnection,String NSName) throws Exception{
        //1.获取HbaseAdmin对象
        Admin hbaseAdmin = hbaseConnection.getAdmin();
        TableName[] tableNames = hbaseAdmin.listTableNamesByNamespace(NSName);
        List<String> tableStrNames = new ArrayList<>();
        for (TableName tableName : tableNames) {
            tableStrNames.add(tableName.getNameAsString());
        }
        return tableStrNames;
    }

    public static void listNamespaces() throws Exception{
        //1.获取一个Hbase连接对象
        Connection hbaseConnection = HbaseUtil.getHbaseConnetion();
        //2.获取HbaseAdmin对象
        Admin hbaseAdmin = hbaseConnection.getAdmin();
        NamespaceDescriptor[] namespaceDescriptors = hbaseAdmin.listNamespaceDescriptors();
        for (NamespaceDescriptor namespaceDescriptor : namespaceDescriptors) {
            System.out.println(namespaceDescriptor.getName());
        }
        System.out.println(namespaceDescriptors.length + " row(s)");
        hbaseConnection.close();
    }

    /**
     * 根据指定名称删除一个空的(其下没有表)Namespace
     * @param NSName
     * @throws Exception
     */
    public static void deleteEmptyNS(String NSName)throws Exception{
        Connection hbaseConnection = HbaseUtil.getHbaseConnetion();
        Admin hbaseAdmin = hbaseConnection.getAdmin();
        hbaseAdmin.deleteNamespace(NSName);
        System.out.println("空的NS：" + NSName + " 删除成功");
        hbaseConnection.close();
    }

    /**
     * 删除一个非空的NS，级联禁用并删除其内的表
     * @param NSName
     * @throws Exception
     */
    public static void deleteNotEmptyNSCascade(String NSName) throws Exception{
//        System.out.println("您确定要删除一个非空的Hbase命名空间吗？如果后悔请在倒计时结束前关闭本程序！");
//        for (int i = 10; i > 0; i--) {
//            Thread.sleep(1*1000L);
//            System.out.println(i);
//        }

        //1.获取一个Hbase连接对象
        Connection hbaseConnection = HbaseUtil.getHbaseConnetion();
        //2.获取HbaseAdmin对象
        Admin hbaseAdmin = hbaseConnection.getAdmin();
        //3.获取给定NS下的所有表的表名
        List<String> nsTableNames = getNSTables(hbaseConnection,NSName);
        System.out.println("已经获取到" + NSName + "下的所有表的名字");
        //4.逐个禁用并删除给定NS下的每个表
        for (String nsTableName : nsTableNames) {
            dropTableAutoDisable(hbaseConnection,NSName,nsTableName.split(":")[1]);
        }
        //5.将空的NS删除
        hbaseAdmin.deleteNamespace(NSName);
        System.out.println("非空的NS：" + NSName + " 删除成功");
        hbaseConnection.close();
    }

    /**
     * 删除一个给定的NS
     * @param NSName
     * @param isNSEmpty 是否是空的NS
     * @throws Exception
     */
    public static void deleteNS(String NSName,boolean isNSEmpty)throws Exception{
        if (isNSEmpty){
            deleteEmptyNS(NSName);
        }else {
            System.out.println("开始执行非空表：" + NSName + "的删除操作");
            deleteNotEmptyNSCascade(NSName);
        }
    }

}
