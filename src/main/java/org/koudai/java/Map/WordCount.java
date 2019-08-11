package org.koudai.java.Map;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
/**
 * @description HashMap实现WordCount
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/11/21 20:03
 */
public class WordCount {
    public static void main(String args []) throws IOException {
        Hashtable<String, Integer>  wordCount = new Hashtable<String, Integer>();
        BufferedReader reader = new BufferedReader( new InputStreamReader( new FileInputStream( new File("D:\\IDEAProjects\\bigdata\\src\\main\\java\\org\\qianfeng\\java\\Map\\wc_input")),"utf-8"));
        BufferedWriter Writer = new BufferedWriter( new OutputStreamWriter( new FileOutputStream( new File("D:\\IDEAProjects\\bigdata\\src\\main\\java\\org\\qianfeng\\java\\Map\\wc_out")),"utf-8"));
        String s=null;
        int c=0;
        while ((s=reader.readLine())!=null) {
            String arry[] =s.split(" ");
            for (int i = 0; i < arry.length; i++) {
                if (!wordCount.containsKey(arry[i])) {
                    wordCount.put(arry[i], Integer.valueOf(1));
                } else {
                    wordCount.put(arry[i], Integer.valueOf(wordCount.get(arry[i]).intValue() + 1));
                }
            }
        }
        for (java.util.Map.Entry<String, Integer> j : wordCount.entrySet()) {
            String key = j.getKey();
            int value = j.getValue();
            Writer.append(key+" "+value+"\r\n");
        }
        //if the number of the word is 1,output the word and the number

        //将map.entrySet()转换成list
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(wordCount.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            //降序排序
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
                //return o1.getValue().compareTo(o2.getValue());
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        for (Map.Entry<String, Integer> mapping : list) {
            System.out.println(mapping.getKey() + ":" + mapping.getValue());
        }

    }

}
