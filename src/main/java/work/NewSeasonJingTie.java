package work;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import utils.HttpUtil;

import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 新品季
 */
public class NewSeasonJingTie extends TimerTask {
    private final static Logger logger = Logger.getLogger(NewSeasonJingTie.class);

    static String cookie = "shshshfpa=7d458cc5-ef69-8cca-04f9-04e8eefb8031-1577076121; __jdu=15770761202431945958964; pinId=xcWTturruQA; shshshfpb=yjlcj3AiuwWhpqP6phZkXFA%3D%3D; __jdv=76161171|direct|-|none|-|1591945299888; areaId=1; ipLoc-djd=1-2809-0-0; shshshfp=4154bf8474fadcecaae419f9c9058aff; pin=czl315; unick=czl315; _tp=EOpsxLedIh%2Bha%2FKPkDpqCg%3D%3D; _pst=czl315; user-key=b33f3bbe-c314-4321-9cca-3b828ff5dc79; cn=49; TrackID=1j5aNi9FXnnxPIiOVZWH-bHo1BeIWq5lqynywK5V7NnJfmlwsJY8Pm81L6Twh3TFljY2Fp7XvNp3mo1ny1IWq2m1uef0T3v41awFzSZ6DSxI; thor=A718E266D9B2378CAFBC9387160E875110CE55110EA8365AD1E3F4FE2A9A392D6A6D90F36223F77C58A511CB434DF650A8896DFE2B28A43496006C991F0348884AD312738877666672FF9EA3B51C98FE8F2FA141799EEA8954AA9EFBDCE4394D9110DD945FF7D1FD0DA40C7326F24AE2C8472C8903A552E75468F6828D47682E; ceshi3.com=201; shshshsID=69438b643a25a11b9c107a1702852c34_1_1592184039515; __jda=122270672.15770761202431945958964.1577076120.1591945300.1592184030.6; __jdc=122270672; 3AB9D23F7A4B3C9B=PMRCFLCUO2QEAKTO3OLBOKLRFTIBQFWR423GYIVNGBMDU3YZKT53RRFSK4TL72V5IF5FRF24EEQKY5ANIUW7RDPGPU; __jdb=122270672.7.15770761202431945958964|6.1592184030";
    static String couponKey2020 ="+nDd5eaX5UIWb1Jtr/ubPg==";
    static String couponKey618 ="boosMMOMdKA/RoMdbEwJ9A==";

    /**
     * 新品季
     *
     * @param args args
     */
    public static void main(String[] args) {
        // 初始化延迟0ms开始执行，每隔200ms重新执行一次任务。
        ScheduledExecutorService  pool = new ScheduledThreadPoolExecutor(1);
        pool.scheduleAtFixedRate(new Runnable() {
            public void run() {
                // task to run goes here
                System.out.println("Hello !2020");
                jingTieExchange(cookie,couponKey2020);
            }
        }, 0, 100L, TimeUnit.MILLISECONDS);

        // 初始化延迟0ms开始执行，每隔200ms重新执行一次任务。
        ScheduledExecutorService  pool2 = new ScheduledThreadPoolExecutor(1);
        pool.scheduleAtFixedRate(new Runnable() {
            public void run() {
                // task to run goes here
                System.out.println("Hello !618");
                jingTieExchange(cookie,couponKey618);
            }
        }, 0, 100L, TimeUnit.MILLISECONDS);
    }

    /**
     * 京贴兑换优惠券
     * @param cookie
     */
    private static String jingTieExchange(String cookie,String couponKey) {
        String url = "https://rdcseason.m.jd.com/api/task/jingTieExchange";
        StringBuffer urlParam = new StringBuffer();
        urlParam.append("conponKey="+couponKey);
        String rs = HttpUtil.sendPost(url, urlParam.toString(), cookie);
        System.out.println(rs);
        return rs;
    }

    public void run() {

    }
}
