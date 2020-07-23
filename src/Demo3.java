import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author cx
 * @Date 2020/7/23 10:11
 */

//线程安全示例
public class Demo3 {
    public static void main(String[] args) throws InterruptedException{
        final  int threadSize = 1000;
        //线程不安全示例
        // ThreadUnsafe example = new ThreadUnsafe();
        //线程安全示例1:Atomic
        //AtomicThreadSafe example = new AtomicThreadSafe();
        //线程安全示例2：synchronized
        SynchronizedThreadSafe example = new SynchronizedThreadSafe();

        final CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < threadSize; i++) {
            executorService.execute(() -> {
                example.add();
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println(example.get());
    }
}
