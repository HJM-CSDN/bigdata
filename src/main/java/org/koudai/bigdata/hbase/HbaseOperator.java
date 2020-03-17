package org.koudai.bigdata.hbase;

import static org.koudai.bigdata.hbase.HbaseNSOperator.listNamespaces;
import static org.koudai.bigdata.hbase.HbaseTableDDLOperator.dropTable;
import static org.koudai.bigdata.hbase.HbaseTableDDLOperator.transformTableStatus;

/**
 * @description hbase操作api
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/10/16 10:43
 */
public class HbaseOperator {
    public static void main(String[] args) throws Exception {
        //1.测试创建指定名称的namespace
      //  createNamespace("ns1");

        //2.测试列出当前Hbase集群中的所有NS
        listNamespaces();

        //3.测试删除一个空的指定名称的NS
    //    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~");
    //    deleteEmptyNS("ns1"); //指定删除的namespace名称
    //    listNamespaces();

        //4.测试创建一个hbase表
    //    createTable("ns","emp1","person","salary","partment");//三个列族,列族个数可自定义

        //5.测试禁用一个表
     //   transformTableStatus("ns1","emp",false);

        //测试删除一个禁用状态的表
  //      dropTable("ns1","emp");

        //测试删除一个空的NS
//        deleteNS("huge",true);

        //测试删除一个非空的NS
  //      deleteNS("ns1",false);
    }
}
