package utils;

import java.util.concurrent.CyclicBarrier;

/**
 * 线程屏障
 *
 * @author
 * @date 2022/2/21
 */
public class CyclicBarrierWorker implements Runnable {
    private CyclicBarrier cyclicBarrier;

    public CyclicBarrierWorker(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
//        super.run();
        try {
            System.out.println(Thread.currentThread().getName() + "开始等待其他线程");
            cyclicBarrier.await();
            System.out.println(Thread.currentThread().getName() + "开始执行");
            // 工作线程开始处理，这里用Thread.sleep()来模拟业务处理
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "执行完毕");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
