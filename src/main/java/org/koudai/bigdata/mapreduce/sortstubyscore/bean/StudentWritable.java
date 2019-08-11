package org.koudai.bigdata.mapreduce.sortstubyscore.bean;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @description 自定义的可以在MR框架中使用Student类型
 * @author: Mmy mamingyume@foxmail.com
 * @create: 2018-09-28 16:27:23
 **/
public class StudentWritable implements WritableComparable {
    String name;//学生姓名
    int score;//总成绩

    /**
     * 规定两个学生对象怎么比较
     * @param o
     * @return
     */
    @Override
    public int compareTo(Object o) {
        return this.score<((StudentWritable)o).score?1:-1;
    }

    /**
     * 用来决定怎么将一个学生对象输出
     * @param out
     * @throws IOException
     */
    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(name);
        out.writeInt(score);
    }

    /**
     * 用来决定怎么将一个学生对象输入
     * @param in
     * @throws IOException
     */
    @Override
    public void readFields(DataInput in) throws IOException {
        this.name = in.readUTF();
        this.score = in.readInt();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
