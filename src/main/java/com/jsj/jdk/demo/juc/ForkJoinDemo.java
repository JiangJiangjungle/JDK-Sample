package com.jsj.jdk.demo.juc;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * ForkJoin任务的测试示例
 */
public class ForkJoinDemo {
    /**
     * 模拟求和任务：根据数组长度执行对应时间的线程睡眠
     */
    public static class AccTask extends RecursiveTask<Boolean> {
        private final int start;
        private final int end;
        private final int threshold;

        public AccTask(int start, int end, int threshold) {
            this.start = start;
            this.end = end;
            this.threshold = threshold;
        }

        @Override
        protected Boolean compute() {
            if (this.end - this.start <= this.threshold) {
                try {
                    Thread.sleep(this.end - this.start);
                } catch (InterruptedException i) {
                    i.printStackTrace();
                }
                return true;
            }
            int mid = (this.start + this.end) / 2;
            AccTask subTask1 = new AccTask(this.start, mid, this.threshold);
            AccTask subTask2 = new AccTask(mid, this.end, this.threshold);
            invokeAll(subTask1, subTask2);
            return true;
        }
    }

    public static void main(String[] args) {
        int length = 10000;
        int cpuCores = Runtime.getRuntime().availableProcessors();
        int threshold = length / (2 * cpuCores);
        System.out.printf("cpu核数：%d，数组长度：%d\n", cpuCores, length);
        AccTask accTask = new AccTask(0, length, threshold);
        long start = System.currentTimeMillis();
        ForkJoinPool.commonPool().submit(accTask);
        accTask.join();
        long taskTime1 = System.currentTimeMillis()-start;
        System.out.printf("Fork/Join模拟累加计算结束，耗时%dms\n", taskTime1);
        start = System.currentTimeMillis();
        try {
            Thread.sleep(length);
        } catch (InterruptedException i) {
            i.printStackTrace();
        }
        long taskTime2 = System.currentTimeMillis()-start;
        System.out.printf("单线程模拟累加计算结束，耗时%dms\n", taskTime2);
    }
}
