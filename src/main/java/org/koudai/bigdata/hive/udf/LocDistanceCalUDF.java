package org.koudai.bigdata.hive.udf;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;
import org.apache.log4j.Logger;


/**
 * @description 经纬度计算距离
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2019/2/24 12:50
 */
public class LocDistanceCalUDF extends UDF {
    private static Logger log = Logger.getLogger(LocDistanceCalUDF.class);
    private Text nullText = new Text("");
    /**
     * 根据经纬度计算地球两点间的距离
     * 经纬度：
     * longitude and latitude
     */
    private static double distanceCal(double lat1,double lng1,double lat2,double lng2){
        double dx = lng1 -lng2;//经度差值
        double dy = lat1 -lat2;//纬度差值
        double b = (lat1 + lat2)/2.0;//平均纬度
        double Lx = Math.toRadians(dx)*6367000.0*Math.cos(Math.toRadians(b));//东西距离
        double Ly = 6367000.0 * Math.toRadians(dy);//南北距离
        return Math.sqrt(Lx*Lx + Ly*Ly);//用平面矩形对角距离公式计算总距离（米）
    }
    /**
     * 重写evaluate放法
     * 参数为两个位置（纬度，经度）的形式
     */
    public static Text evaluate( Text latitudeText1,Text longitudeText1, Text latitudeText2,Text longitudeText2){

        if (longitudeText1 == null || latitudeText1 == null || longitudeText2 == null || latitudeText2 == null){
            return null;
        }
        if (StringUtils.isEmpty(longitudeText1.toString())||StringUtils.isEmpty(longitudeText2.toString())||StringUtils.isEmpty(latitudeText1.toString())||StringUtils.isEmpty(latitudeText2.toString())){
            return null;
        }
        double lng1 = Double.valueOf(longitudeText1.toString());
        double lng2 = Double.valueOf(longitudeText2.toString());
        double lat1 = Double.valueOf(latitudeText1.toString());
        double lat2 = Double.valueOf(latitudeText2.toString());

        double distance = distanceCal(lat1,lng1,lat2,lng2);
        return new Text(String.valueOf(distance));
    }
    /**
     * 重写evaluate方法
     * 参数为两个位置（经度，纬度）的形式
     */
    public static Text evaluate(Text locationA,Text locationB){
        if (locationA == null || locationB == null){
            return null;
        }
        if (StringUtils.isEmpty(locationA.toString())||StringUtils.isEmpty(locationB.toString())){
            return null;
        }
        String locationA2String = locationA.toString();
        String locationB2String = locationB.toString();
        double lng1 = Double.valueOf(locationA2String.split(",")[0]);
        double lat1 = Double.valueOf(locationA2String.split(",")[1]);
        double lng2 = Double.valueOf(locationB2String.split(",")[0]);
        double lat2 = Double.valueOf(locationB2String.split(",")[1]);

        double distance = distanceCal(lng1,lat1,lng2,lat2);
        return new Text(String.valueOf(distance));
    }

    /**
     * 测试
     */
    public static void main(String[] args) {
        Text locationA = new Text("39.9047253699,116.4072154982");//北京
        Text locationB = new Text("37.4336512471,118.6746560557");//东营
        Text locationC = new Text("40.0739290000,116.3499150000");//北京回龙观龙跃苑四区
        Text locationD = new Text("24.6751190000,118.2393420000");//厦门翔安区马巷镇黎安田边里
        Text distance = evaluate(locationA,locationB);
        Text distance2 = evaluate(locationC,locationD);
        System.out.println("两个经纬度之间的距离为："+Double.valueOf(distance.toString())/1000.0+"千米");
        System.out.println("经纬度之间的距离为："+Double.valueOf(distance2.toString())/1000.0+"千米");
        System.out.println("参考:");
        System.out.println("在经纬网图上，可以根据经纬度量算两点之间的距离。全球各地纬度1°\n的间隔长度都相等（因为所有经线的长度都相等），大约是111km/1°。赤道上经度1°对应在地面上的弧长大约也是111km。");

        //第二种参数形式
        Text lng1 = new Text("116.3499150000");
        Text lat1 = new Text("40.0739290000");
        Text lng2 = new Text("118.2393420000");
        Text lat2 = new Text("24.6751190000");
        Text distance3 = evaluate(lat1,lng1,lat2,lng2);
        System.out.println("的物理距离为"+distance3+"米");
    }
}
