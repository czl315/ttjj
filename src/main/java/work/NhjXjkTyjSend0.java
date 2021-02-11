package work;

import org.apache.log4j.Logger;
import utils.HttpUtil;

import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 新品季
 */
public class NhjXjkTyjSend0 extends TimerTask {
    private final static Logger logger = Logger.getLogger(NhjXjkTyjSend0.class);
    private static int tempStep = 0;//100



    /**
     * 新品季
     *
     * @param args args
     */
    public static void main(String[] args) {
        // 初始化延迟0ms开始执行，每隔200ms重新执行一次任务。
        ScheduledExecutorService  pool = new ScheduledThreadPoolExecutor(10);
        pool.scheduleAtFixedRate(new Runnable() {
            public void run() {
                // task to run goes here
//                for (int i = tempStep; i < 10; i++) {
                    String url = "https://computerdigital.jd.com/admin/saveSignInUserNum";
                    StringBuffer urlParam = new StringBuffer();
                    urlParam.append("startAllPinKeyModNum="+tempStep+"&endAllPinKeyModNum="+tempStep);
                    String rs = HttpUtil.sendGet(url, urlParam.toString(), "");
                    System.out.println(rs);
                    tempStep++;
//                }

            }
        }, 0, 1, TimeUnit.SECONDS);

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
