package org.koudai.bigdata.hbase;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.CompareOperator;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.*;
/**
 * @description Hbase的扫描器
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/10/17 12:58
 */
public class HbaseScanner {
    public static void main(String[] args) throws Exception{
        Connection hbaseConnection = HbaseUtil.getHbaseConnetion();
        //测试行过滤器
 //       filterData(hbaseConnection,"ns","emp",makeRowFilter());
        //测试列族过滤器
 //       filterData(hbaseConnection,"ns","emp",makeCFFilter());
        //测试列过滤器
//        filterData(hbaseConnection,"ns","emp",makeQualifierFilter());
        //测试单值比较器
//        filterData(hbaseConnection,"ns","emp1",makeSingleColumnValueFilter());
        //测试FirstKeyOnlyFilter
//        filterData(hbaseConnection,"ns","emp1",makeFirstKeyOnlyFilter());
        //测试FilterList 查询年龄中包含8的雇员的名字
//        filterData(hbaseConnection,"ns","emp1",makeFileterList());

        //查询年龄以2开头的雇员的薪水的全量信息
        filterData(hbaseConnection,"ns","emp1",makeFileterList1());//自定义一个新的比较链--第一题需求场景

        //查询查询姓名中包含h字母的雇员的名字和age
        //    思路:先写两个比较器,第一个过滤出包含h字母的雇员所有信息
        //    第二个通过正则过滤出名字和age列
        //    然后用比较链将两个比较器连接起来
        filterData(hbaseConnection,"ns","emp1",makeQualifierFilter2());
        hbaseConnection.close();
    }

    /**
     * 制造一个行过滤器
     * @return rowFilter
     */
    public static RowFilter makeRowFilter(){
       RowFilter rowFilter = new RowFilter(CompareOperator.GREATER_OR_EQUAL,new BinaryPrefixComparator("2".getBytes()));
        return rowFilter;
    }

    /**
     * 制造一个列族过滤器
     * @return FamilyFilter
     */
    public static FamilyFilter makeCFFilter(){
        return  new FamilyFilter(CompareOperator.EQUAL,new BinaryPrefixComparator("p".getBytes()));
    }

    /**
     * 制造一个列过滤器
     * @return
     */
    public static QualifierFilter makeQualifierFilter(){
        return  new QualifierFilter(CompareOperator.EQUAL,new RegexStringComparator("uname|age"));
//        return  new QualifierFilter(CompareOperator.EQUAL,new SubstringComparator("r"));
//        return  new QualifierFilter(CompareOperator.EQUAL,new BinaryComparator("uname".getBytes()));
//        return  new QualifierFilter(CompareOperator.EQUAL,new BinaryPrefixComparator("s".getBytes()));
    }

    /**
     * 制造一个单列值比较器
     * @return
     */
    public static SingleColumnValueFilter makeSingleColumnValueFilter(){
        //以下括号中参数:列族名,列名,比较操作符,比较条件
        return new SingleColumnValueFilter("person".getBytes(),"age".getBytes(),CompareOperator.EQUAL,new SubstringComparator("8"));
//        return new SingleColumnValueFilter("person".getBytes(),"age".getBytes(),CompareOperator.GREATER,"30".getBytes());
    }

    /**
     * 制造一个FirstKeyOnlyFilter 其返回值：每一行的第一个列族的第一个列的名字和值-不常用
     * @return
     */
    public static FirstKeyOnlyFilter makeFirstKeyOnlyFilter(){
        return new FirstKeyOnlyFilter();
    }

    //比较链
    public static FilterList makeFileterList(){
        //多个过滤器之间连接的方式
        //and FilterList.Operator.MUST_PASS_ALL  两个
        //or FilterList.Operator.MUST_PASS_ONE
        FilterList fl = new FilterList(FilterList.Operator.MUST_PASS_ALL);
        fl.addFilter(makeSingleColumnValueFilter());//查询年龄中包含8的雇员的所有信息
        fl.addFilter(makeQualifierFilter());//限定只查询名字
        return fl;
    }

    /**
     * 使用单个过滤器 或 FileterList 过滤给定NS下的给定表中的数据
     * @param hbaseConnection
     * @param NSName
     * @param tableName
     */
    public static void filterData(Connection hbaseConnection,String NSName,String tableName,Filter filter) throws Exception{
        Table table = hbaseConnection.getTable(TableName.valueOf(NSName + ":" + tableName));
        Scan scan = new Scan();
        scan.setFilter(filter);
        table.getScanner(scan);
        ResultScanner rs = table.getScanner(scan);
        for (Result r : rs) {
            showResult(r);
        }
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
                System.out.print("rowkey："+new String(CellUtil.cloneRow(cell))+"\t\t");
                System.out.print("cf："+new String(CellUtil.cloneFamily(cell))+"\t\t");
                System.out.print("col："+new String(CellUtil.cloneQualifier(cell))+"\t\t");
                System.out.print("value："+new String(CellUtil.cloneValue(cell))+"\t\t");
                System.out.println("timestamp："+cell.getTimestamp());
            }
        }
    }

    //第一题:
    //查询年龄以2开头的雇员的薪水的全量信息
    /**
     * 制造一个单列值比较器
     * @return
     */
    public static SingleColumnValueFilter makeSingleColumnValueFilter1(){
        SingleColumnValueFilter singleColumnValueFilter = new SingleColumnValueFilter("person".getBytes(), "age".getBytes(), CompareOperator.EQUAL, new BinaryPrefixComparator("2".getBytes()));
        singleColumnValueFilter.setFilterIfMissing(true);//忽略不包含这个列的行
        return singleColumnValueFilter;
//        return new SingleColumnValueFilter("person".getBytes(),"age".getBytes(),CompareOperator.GREATER,"30".getBytes());
    }
    //查询年龄以2开头的雇员的薪水的salary信息
    public static QualifierFilter makeQualifierFilter1(){
        return  new QualifierFilter(CompareOperator.EQUAL,new RegexStringComparator("salary"));
//        return  new QualifierFilter(CompareOperator.EQUAL,new SubstringComparator("r"));
//        return  new QualifierFilter(CompareOperator.EQUAL,new BinaryComparator("uname".getBytes()));
//        return  new QualifierFilter(CompareOperator.EQUAL,new BinaryPrefixComparator("s".getBytes()));
    }
    //第一题自定义的比较链
    public static FilterList makeFileterList1(){
        //多个过滤器之间连接的方式
        //and  FilterList.Operator.MUST_PASS_ALL
        //or  FilterList.Operator.MUST_PASS_ONE
        FilterList fl = new FilterList(FilterList.Operator.MUST_PASS_ALL);
        fl.addFilter(makeSingleColumnValueFilter1());//查询年龄中以2开头的雇员的所有信息
        fl.addFilter(makeQualifierFilter1());//限定只查询salary
        return fl;
    }
    //第二题:
    //查询姓名包含h的雇员的薪水的全量信息
    /**
     * 制造一个单列值比较器
     * @return
     */
    public static SingleColumnValueFilter makeSingleColumnValueFilter2(){
        SingleColumnValueFilter singleColumnValueFilter = new SingleColumnValueFilter("person".getBytes(), "uname".getBytes(), CompareOperator.EQUAL, new SubstringComparator("h"));
        singleColumnValueFilter.setFilterIfMissing(true);//忽略不包含这个列的行
        return singleColumnValueFilter;
//        return new SingleColumnValueFilter("person".getBytes(),"age".getBytes(),CompareOperator.GREATER,"30".getBytes());
    }
    //查询雇员的名字和age
    public static QualifierFilter makeQualifierFilter2(){
        return  new QualifierFilter(CompareOperator.EQUAL,new RegexStringComparator("uname|age"));//正则
//        return  new QualifierFilter(CompareOperator.EQUAL,new SubstringComparator("r"));
//        return  new QualifierFilter(CompareOperator.EQUAL,new BinaryComparator("uname".getBytes()));
//        return  new QualifierFilter(CompareOperator.EQUAL,new BinaryPrefixComparator("s".getBytes()));
    }
    //第一题自定义的比较链
    public static FilterList makeFileterList2(){
        //多个过滤器之间连接的方式
        //and  FilterList.Operator.MUST_PASS_ALL
        //or  FilterList.Operator.MUST_PASS_ONE
        FilterList fl = new FilterList(FilterList.Operator.MUST_PASS_ALL);
        fl.addFilter(makeSingleColumnValueFilter2());//查询姓名包含h的雇员的所有信息
        fl.addFilter(makeQualifierFilter2());//限定只查询uname和age
        return fl;
    }
}
