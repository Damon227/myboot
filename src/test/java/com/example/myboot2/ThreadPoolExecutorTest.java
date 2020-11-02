package com.example.myboot2;

import org.springframework.format.datetime.standard.DateTimeContext;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author damon
 * @date 2020-08-11
 */
public class ThreadPoolExecutorTest {

    public static void main(String[] args){
        //ScheduleThreadPoolTest();
        FixedThreadPoolTest();
    }

    private static void ScheduleThreadPoolTest(){
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);

        // 创建固定间隔时间执行的任务，initialDelay 时间后首次执行，前一个任务开始执行后，间隔 period 开始执行新任务，不管前一个任务是否结束。
        //executor.scheduleAtFixedRate(new MyRunnable(), 50, 5000, TimeUnit.MILLISECONDS);

        // 创建固定延迟时间执行的任务，initialDelay 时间后首次执行，前一个任务执行完后等待 delay 开始执行新任务
        executor.scheduleWithFixedDelay(new MyRunnable(), 50, 5000, TimeUnit.MILLISECONDS);
    }

    private static void FixedThreadPoolTest(){
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
        for (int i = 0; i < 10; i++) {
            executor.execute(new MyRunnable());
        }
    }

    static class MyRunnable implements Runnable{

        int executedCount = 0;

        @Override
        public void run() {
            try {
                System.out.printf("--  第" + executedCount + "次  --");
                System.out.printf("\n");
                System.out.printf("开始 " + getCurrentTime());
                System.out.printf("\n");
                Thread.sleep(2000);
                System.out.printf("结束 " + getCurrentTime());
                System.out.printf("\n");

                executedCount++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        private String getCurrentTime(){
            return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS")).format(new Date());
        }
    }
}
