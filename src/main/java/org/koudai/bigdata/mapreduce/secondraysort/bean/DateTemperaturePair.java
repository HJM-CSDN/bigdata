package org.koudai.bigdata.mapreduce.secondraysort.bean;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @description 日期时间对 数据类型
 * @author: Mmy 2018-6-8 08:41:57
 * @create: 2018-06-13 16:22:54
 **/
public class DateTemperaturePair implements WritableComparable<DateTemperaturePair> {
    private Text yearMonth = new Text();//自然键
    private Text day = new Text();
    private IntWritable wendu = new IntWritable();//次键
    @Override
    public int compareTo(DateTemperaturePair compareObj) {
        int compareResult = this.yearMonth.compareTo(compareObj.getYearMonth());
        if (compareResult == 0){
            compareResult = wendu.compareTo(compareObj.getWendu());
        }
        return compareResult;//升序排序 asc
//        return -1*compareValue; //降序排序 desc
    }


    /**
     * 将自定义对象写出到HDFS文件中
     *  业务需求的数据长相如下：
     *  2012-01：5，10，35,45
     * @param out
     * @throws IOException
     */
    @Override
    public void write(DataOutput out) throws IOException {
        //写出到HDFS文件中的第一列
        out.writeUTF(this.getYearMonth().toString());
        //写出到HDFS文件中的第二列
        out.writeUTF(this.getWendu().toString());

    }

    /**
     * 读取字段并赋值到自定义对象上
     *  将一行文本中的多个字段按照指定的业务逻辑封装到我们的自定义对象上
     *  原始数据长相如下：
     *  2012,01,01,5
     * @param in
     * @throws IOException
     */
    @Override
    public void readFields(DataInput in) throws IOException {
//        //读取第一个字段
//        String year = in.readUTF();
//        //读取第二个字段
//        String month = in.readUTF();
//        //将读取到的第一个字段和第二个字段拼接起来并赋值到yearMonth属性上
//        this.yearMonth.set(year + "-" + month);
//        //读取第三个字段并赋值到day属性上
//        this.day.set(in.readUTF());
//        //读取第四个字段并赋值到wendu属性上
//        this.wendu.set(Integer.parseInt(in.readUTF()));
        this.yearMonth=new Text(in.readUTF());
        this.wendu=new IntWritable(Integer.parseInt(in.readUTF()));
    }

    public Text getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(Text yearMonth) {
        this.yearMonth = yearMonth;
    }

    public Text getDay() {
        return day;
    }

    public void setDay(Text day) {
        this.day = day;
    }

    public IntWritable getWendu() {
        return wendu;
    }

    public void setWendu(IntWritable wendu) {
        this.wendu = wendu;
    }
}
