package org.unicom.bigdata.hbase;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @description Hbase表数据操作员
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/10/16 20:59
 */
public class HbaseTableDMLOperator {
    public static void main(String[] args) throws Exception{
        Connection hbaseConnection = HbaseUtil.getHbaseConnetion();
        //测试put数据
        put(hbaseConnection,"ns","emp1","a","person","uname","huge");
        put(hbaseConnection,"ns","emp1","a","person","sex","gay");
        put(hbaseConnection,"ns","emp1","a","person","age","50");
        put(hbaseConnection,"ns","emp1","a","salary","salary","250");
        put(hbaseConnection,"ns","emp1","a","salary","salary_level","0");
        put(hbaseConnection,"ns","emp1","a","partment","id","1");
        put(hbaseConnection,"ns","emp1","a","partment","name","development");
        put(hbaseConnection,"ns","emp1","10","person","uname","hujie");
        put(hbaseConnection,"ns","emp1","10","person","sex","gay");
        put(hbaseConnection,"ns","emp1","10","person","age","38");
        put(hbaseConnection,"ns","emp1","10","salary","salary","50");
        put(hbaseConnection,"ns","emp1","10","salary","salary_level","0");
        put(hbaseConnection,"ns","emp1","test","partment","id","2");
        put(hbaseConnection,"ns","emp1","test","partment","name","aftersale");
        put(hbaseConnection,"ns","emp1","test","person","uname","hujie");
        put(hbaseConnection,"ns","emp1","test","person","sex","gay");
        put(hbaseConnection,"ns","emp1","test","person","age","290");
        put(hbaseConnection,"ns","emp1","test","salary","salary","550");
        put(hbaseConnection,"ns","emp1","test","salary","salary_level","0");
        put(hbaseConnection,"ns","emp1","test","partment","id","2");
        put(hbaseConnection,"ns","emp1","test","partment","name","aftersale");

        //测试get数据
//        get(hbaseConnection,"ns","emp","3","person","uname");
        //测试扫描器
        scan(hbaseConnection,"ns","emp1","1","a","person");
        hbaseConnection.close();
    }
    /**
     * 往指定表的指定rowkey中添加指定cfname和colname的colvalue
     * @param hbaseConnection
     * @param NSName
     * @param tableName
     * @param rowKey
     * @param cfName
     * @param colName
     * @param colValue
     * @throws Exception
     */
    public static void put(Connection hbaseConnection, String NSName, String tableName, String rowKey, String cfName, String colName, String colValue)throws Exception{
        //1.根据给定的NS名和表名创建table对象
        Table table = hbaseConnection.getTable(TableName.valueOf(NSName + ":" + tableName));
        //2.根据rowkey创建put对象
        Put put = new Put(rowKey.getBytes());
        //3.往指定的rowkey中的列族中插入数据
        put.addColumn(cfName.getBytes(),colName.getBytes(),colValue.getBytes());
        //4.将上述put对象添加到表中 即：将指定rowkey、cfname、colname、colvalue的数据添加到表中
        table.put(put);
        //5.关闭表
        table.close();
    }

    /**
     * 在给定的NS下的指定的表中按照给定的查询条件查询数据
     * @param hbaseConnection
     * @param NSName
     * @param tableName
     * @param rowKey
     * @param cfName
     * @param colName
     * @throws Exception
     */
    public static void get(Connection hbaseConnection, String NSName, String tableName, String rowKey, String cfName, String colName) throws Exception{
        //1.根据给定的NS名和表名创建table对象
        Table table = hbaseConnection.getTable(TableName.valueOf(NSName + ":" + tableName));
        //2.根据rowkey构造Get对象
        Get get = new Get(rowKey.getBytes());
        if (cfName!=null && !cfName.equals("")){//查询条件中指定了列族名
            if (colName != null && !cfName.equals("")){//查询条件中同时制定了列族名和列族内的列名
                get.addColumn(cfName.getBytes(),colName.getBytes());
            }else {//只指定了列族名
                get.addFamily(cfName.getBytes());
            }
        }
        Result result = table.get(get);
        for(Cell cell: result.listCells()){
            System.out.println("行键："+ rowKey +"\t");
            System.out.print("列族:" + Bytes.toString(cell.getFamilyArray(), cell.getFamilyOffset(), cell.getFamilyLength())+"\t");
            System.out.print("列:"+Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength())+"\t");
            System.out.print("值:"+Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength())+"\t");
            System.out.println("时间戳:"+cell.getTimestamp());
        }
        table.close();
    }


    /**
     * 在给定的NS下的指定的表中按照给定的扫描器进行数据扫描
     * @param hbaseConnection
     * @param NSName
     * @param tableName
     * @param startRow
     * @param stopRow
     * @param cfName
     * @throws Exception
     */
    public static void scan(Connection hbaseConnection, String NSName, String tableName,String startRow,String stopRow,String cfName)throws Exception{
        Table table=hbaseConnection.getTable(TableName.valueOf(NSName + ":" + tableName));
        //获取scan对象
        Scan scan=new Scan();
        //定义要扫描的列族和其下的列（暂时不写活）
        scan.addFamily(cfName.getBytes());
        //设置开始行建 方法已过时
        scan.setStartRow(Bytes.toBytes(startRow));
        //设置结束行建 方法已过时
        scan.setStopRow(Bytes.toBytes(stopRow));//结果集中包含此行
        //扫描全部符合条件的行
        ResultScanner results = table.getScanner(scan);//结果集中不包含此行
        //循环处理结果集中的每个Result(一个行)
        for(Result rs:results){
            showResult(rs);
        }
        results.close();
        table.close();
    }

    /**
     * 获取全部行的数据
     * @param oneRowResult 表名
     * @throws Exception
     */
    public static void showResult(Result oneRowResult) throws Exception{
        if(oneRowResult!=null){
            for(Cell cell : oneRowResult.listCells()){
                System.out.print("行鍵："+new String(CellUtil.cloneRow(cell))+"\t");
                System.out.print("列族："+new String(CellUtil.cloneFamily(cell))+"\t");
                System.out.print("列："+new String(CellUtil.cloneQualifier(cell))+"\t");
                System.out.print("值："+new String(CellUtil.cloneValue(cell))+"\t");
                System.out.println("時間戳："+cell.getTimestamp());
            }
        }
    }

    /**
     * 删除给定NS下给定表的给定rowkey的指定cfname的指定colname的值
     * @param hbaseConneciton
     * @param NSName
     * @param tableName
     * @param cfName
     * @throws Exception
     */
    public static void delete(Connection hbaseConneciton,String NSName,String tableName,String rowkey,String cfName,String colName)throws Exception{
        Table table = hbaseConneciton.getTable(TableName.valueOf(NSName + ":" + tableName));
        Delete delete = new Delete(rowkey.getBytes());
        if (cfName != null || cfName != "") {
            if (colName == null  || colName == ""){
                delete.addFamily(cfName.getBytes());
            }else {
                delete.addColumn(cfName.getBytes(),colName.getBytes());
            }
            //cfName不为空
        }/*else {
            //cfName为空,delete对象到此就封装完毕了,不用写
        }*/
        table.delete(delete);
        table.close();
    }

    public static void 增删查(Connection hbaseConnection, String NSName, String tableName, Map<String,Map<String,List<String>>> items,String option) throws Exception{
        if (option==null || option=="" || (option != "delete" && option != "get")){
            System.out.println("请给定正确的操作方式：可用的项为 get delete");
        }else {
            Table table = hbaseConnection.getTable(TableName.valueOf(NSName + ":" + tableName));
            if (option=="get") {
                Set<String> rowKeys = items.keySet();
                for (String rowKey : rowKeys) {
                    Get get = new Get(rowKey.getBytes());
                    Map<String, List<String>> rowInfo = items.get(rowKey);
                    Set<String> cfNames = rowInfo.keySet();
                    for (String cfName : cfNames) {
                        if (cfName != null && cfName != "") {//cfName不为空
                            List<String> colNames = rowInfo.get(cfName);
                            if (colNames.size() == 0) {//只给定了列族名而没有给定任意列名
                                get.addFamily(cfName.getBytes());
                            } else {
                                for (String colName : colNames) {
                                    if (colName == null || colName == "") {
                                    } else {
                                        get.addColumn(cfName.getBytes(),colName.getBytes());
                                    }
                                }
                            }
                        }
                    }
                    Result result = table.get(get);
                    for(Cell cell: result.listCells()){
                        System.out.println("行键："+ rowKey +"\t");
                        System.out.print("列族:" + Bytes.toString(cell.getFamilyArray(), cell.getFamilyOffset(), cell.getFamilyLength())+"\t");
                        System.out.print("列:"+Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength())+"\t");
                        System.out.print("值:"+Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength())+"\t");
                        System.out.println("时间戳:"+cell.getTimestamp());
                    }
                }
            }else {//delete

            }
            table.close();
        }

    }

}



















