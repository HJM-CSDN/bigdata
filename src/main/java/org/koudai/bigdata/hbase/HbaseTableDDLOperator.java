package org.koudai.bigdata.hbase;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @description Hbase表操作员
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/10/16 20:15
 */
public class HbaseTableDDLOperator {
    /**
     * 创建一个HbaseTable
     * @param NSName    表所在NS的name
     * @param tableName   表名
     * @param cfNames     多个列族名
     */
    public static  void createTable(String NSName,String tableName,String ...cfNames)throws Exception{

        //1.获取一个Hbase连接对象
        Connection hbaseConnection = HbaseUtil.getHbaseConnetion();
        //2.获取HbaseAdmin对象
        Admin hbaseAdmin = hbaseConnection.getAdmin();
        //3.构造一个表描述符构造器对象
        TableDescriptorBuilder tdb = TableDescriptorBuilder.newBuilder(TableName.valueOf(NSName+":"+tableName));
        //4.根据传进来的多个列族名构造多个列族描述符对象
        List<ColumnFamilyDescriptor> cfs = new ArrayList<>();
        for (String cfName : cfNames) {
            //通过构造列族描述符构造器 用其构造列族描述符对象
            cfs.add(ColumnFamilyDescriptorBuilder.newBuilder(cfName.getBytes()).build());
        }
        //5.将列族对象添加到表描述符构造器上
        tdb.setColumnFamilies(cfs);
        //6.使用表描述符构造器 构造表
        TableDescriptor t1 = tdb.build();
        //7.根据表描述符对象创建表
        hbaseAdmin.createTable(t1);
        hbaseConnection.close();
    }

    /**
     * 对给定的表将其转换为启用或禁用状态
     * @param NSName  命名空间
     * @param tableName 表名
     * @param isToEnable 状态
     * @throws Exception
     */
    public static void transformTableStatus(String NSName,String tableName,boolean isToEnable)throws Exception{
        Connection hbaseConnection = HbaseUtil.getHbaseConnetion();
        transformTableStatus(hbaseConnection,NSName,tableName,isToEnable);
        hbaseConnection.close();
    }

    /**
     * 对给定的表将其转换为启用或禁用状态
     * 多次调用本方法禁用或启用多个表的时候，使用同一个连接对象
     * 注意：在方法内不能关闭连接
     * @param NSName
     * @param tableName
     * @param isToEnable
     */
    public static void transformTableStatus(Connection hbaseConnection,String NSName,String tableName,boolean isToEnable) throws Exception {
        //1.获取HbaseAdmin对象
        Admin hbaseAdmin = hbaseConnection.getAdmin();
        if (isToEnable){
            if (hbaseAdmin.isTableEnabled(TableName.valueOf(NSName + ":" + tableName))){
                System.out.println(NSName + ":" + tableName + "表已经是启用状态，启用动作无需执行");
            }else {
                hbaseAdmin.enableTable(TableName.valueOf(NSName+":"+tableName));
                System.out.println("启用表：" + NSName+":"+tableName + "成功");
            }
        }else {
            if (hbaseAdmin.isTableDisabled(TableName.valueOf(NSName + ":" + tableName))){
                System.out.println(NSName + ":" + tableName + "表已经是禁用状态，禁用动作无需执行");
            }else {
                hbaseAdmin.disableTable(TableName.valueOf(NSName+":"+tableName));
                System.out.println("禁用表：" + NSName+":"+tableName + "成功");
            }
        }
        //注意：这里不能关闭连接
//        hbaseConnection.close();
    }
    public static  void dropTable(String NSName,String tableName) throws Exception{
        //1.获取一个Hbase连接对象
        Connection hbaseConnection = HbaseUtil.getHbaseConnetion();
        dropTable(hbaseConnection,NSName,tableName);
        hbaseConnection.close();
    }

    /**
     * 通过给定的连接对象和指定的NS名和表名，删除指定的表
     *
     * @param hbaseConnection
     * @param NSName
     * @param tableName
     * @throws Exception
     */
    public static void dropTable(Connection hbaseConnection,String NSName,String tableName) throws Exception{
        //1.获取HbaseAdmin对象
        Admin hbaseAdmin = hbaseConnection.getAdmin();
        //2.删除表
        hbaseAdmin.deleteTable(TableName.valueOf(NSName + ":" + tableName));
        System.out.println("删除表：" + NSName + ":" + tableName + "成功！");
    }

    /**
     * 通过给定的连接对象和指定的NS名和表名，删除指定的表
     *
     * @param hbaseConnection
     * @param NSName
     * @param tableName
     * @throws Exception
     */
    public static void dropTableAutoDisable(Connection hbaseConnection,String NSName,String tableName) throws Exception{
        System.out.println("-----------------自动禁用并删除" + NSName + ":"  + tableName +"表开始-----------------");
        //1.禁用表
        transformTableStatus(hbaseConnection,NSName,tableName,false);
        //2.删除表
        dropTable(hbaseConnection,NSName,tableName);
        System.out.println("-----------------自动禁用并删除" + NSName + ":"  + tableName +"表成功-----------------"+System.lineSeparator());
    }

}
