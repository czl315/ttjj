package work;

import org.apache.log4j.Logger;
import utils.HttpUtil;

import java.util.TimerTask;
import java.util.concurrent.*;

/**
 * 新品季
 */
public class NhjXjkTyjSendThread extends TimerTask {
    private final static Logger logger = Logger.getLogger(NhjXjkTyjSendThread.class);
    private static int tempStep = 216;



    /**
     * 新品季
     *
     * @param args args
     */
    public static void main(String[] args) {
        ExecutorService cachedThreadPool = Executors.newFixedThreadPool(5);
        cachedThreadPool.execute(new Runnable() {
            public void run() {
                // task to run goes here
                for (int i = 216; i < 300; i++) {
                    String url = "https://computerdigital.jd.com/admin/saveSignInUserNum";
                    StringBuffer urlParam = new StringBuffer();
                    urlParam.append("startAllPinKeyModNum="+i+"&endAllPinKeyModNum="+i);
                    tempStep++;
                    HttpUtil.sendGet(url, urlParam.toString(), "");
//                    System.out.println(rs);
                }

            }
        });

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
