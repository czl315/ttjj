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
public class NhjXjkTyjSend900 {
    private final static Logger logger = Logger.getLogger(NhjXjkTyjSend900.class);
    private static int tempStep =990;



    /**
     * 新品季
     *
     * @param args args
     */
    public static void main(String[] args) {
        // 初始化延迟0ms开始执行，每隔200ms重新执行一次任务。
        ScheduledExecutorService  pool = new ScheduledThreadPoolExecutor(100);
        pool.scheduleAtFixedRate(new Runnable() {
            public void run() {
                // task to run goes here
//                for (int i = tempStep; i < 10; i++) {
                    String url = "https://computerdigital.jd.com/admin/saveSignInUserNum";
                    StringBuffer urlParam = new StringBuffer();
                    urlParam.append("startAllPinKeyModNum="+tempStep+"&endAllPinKeyModNum="+tempStep);
                    try{
                        tempStep++;
                        HttpUtil.sendGet(url, urlParam.toString(), "");
//                        System.out.println(rs);
                    }catch (Exception e){
                        System.out.println("异常");
                    }
//                }

            }
        }, 0, 1, TimeUnit.SECONDS);

    }

}
