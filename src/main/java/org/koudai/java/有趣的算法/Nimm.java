package org.koudai.java.有趣的算法;

import java.io.*;
import java.util.regex.Pattern;

public class Nimm {

    public static void main(String[] args) throws IOException {

        Game game = new Game();
        game.init();
        game.play();

    }


}

class Valid{

    static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[1-9]\\d*$");
        return pattern.matcher(str).matches();
    }

    public int verify(String ss){
        if(isInteger(ss)){
            return Integer.parseInt(ss);
        }else{
            return  0;
        }
    }
    public int verify(String ss, int step){
        int i;
        if(isInteger(ss)){
            i = Integer.parseInt(ss);
            if(i>step){
                return 0;
            }else
                return i;
        }else {
            return 0;
        }
    }
}

class Game{

    int total, step;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    Valid valid = new Valid();

    Game(){
        System.out.println("Nimm游戏！");
    }

    public void init() throws IOException {
        System.out.print("请输入石头的总数：");
        while ((total=valid.verify(br.readLine()))==0){
            System.out.print("请输入正整数：");
        }
        System.out.print("请输入每次取石头的上限：");
        while ((step=valid.verify(br.readLine()))==0){
            System.out.print("请输入正整数：");
        }
        System.out.println("石头总数："+total+",每次上限："+step+",游戏开始！\n");
    }

    public void play() throws IOException {
        int st;

        while ((true)){
            System.out.println("目前还剩石头："+total);
            System.out.print("你拿：");

            while ((st=valid.verify(br.readLine(),step))==0){
                System.out.print("请输入不超过上限的正整数：");
            }

            total = total-st;

            if(total==0){
                System.out.println("你赢了！");
                System.exit(0);
            }

            if(total<=step){
                System.out.println("计算机拿："+total+"\n计算机赢！");
                System.exit(0);
            }else{
                if(total%(step+1)!=0){
                    System.out.println("计算机拿："+total%(step+1));
                    total = total - total%(step+1);
                }else{
                    System.out.println("计算机拿：1");
                    total = total - 1;
                }

            }


        }


    }
}
