package com.jsj.jdk.impl.threadpool;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 仿照jdk源码实现ThreadPool
 *
 * @author jsj
 * @date 2019-01-08
 */
public class MyThreadPool {
    private final int corePoolSize;
    private final int maxPoolSize;
    private final long keepAliveTime;
    private ThreadFactory threadFactory;
    /**
     * 需要执行的任务队列
     */
    private BlockingQueue<Runnable> taskQueue;

    /**
     * 记录线程池中的Worker对象，其中Worker对象是线程的封装,加锁以后才能执行添加或删除操作
     */
    private final HashSet<Worker> workers = new HashSet<>();
    private final AtomicInteger threadCount = new AtomicInteger(0);
    private final ReentrantLock mainLock = new ReentrantLock();

    public MyThreadPool(int corePoolSize, int maxPoolSize, long keepAliveTime, TimeUnit unit, ThreadFactory threadFactory, BlockingQueue<Runnable> taskQueue) {
        this.corePoolSize = corePoolSize;
        this.maxPoolSize = maxPoolSize;
        this.keepAliveTime = unit.toNanos(keepAliveTime);
        this.threadFactory = threadFactory;
        this.taskQueue = taskQueue;
    }

    public void exec(Runnable command) {
        if (command == null) throw new NullPointerException();
        //若当前线程数小于核心池数量，直接创建线程执行
        if (threadCount.get() < corePoolSize) {
            if (addWorker(command, true)) {
                return;
            }
        }
        //当前线程数大于corePoolSize，则加入taskQueue,若加入失败则再次创建线程执行该task
        if (!taskQueue.offer(command)) {
            System.out.printf("TaskQueue已满! 创建临时线程执行task %s\n", command.toString());
            if (!addWorker(command, false)) {
                reject(command);
            }
        }
    }

    /**
     * 添加新线程
     *
     * @param firstTask 是否执行任务
     * @param core      是否核心池
     * @return
     */
    private boolean addWorker(Runnable firstTask, boolean core) {
        //检查线程数约束并使count++;
        retry:
        for (; ; ) {
            int size = core ? corePoolSize : maxPoolSize;
            for (; ; ) {
                if (threadCount.get() >= size) {
                    return false;
                }
                int c = threadCount.get();
                if (threadCount.compareAndSet(c, c + 1)) {
                    break retry;
                }
            }
        }
        boolean workerStarted = false;
        boolean workerAdded = false;
        //新建线程并封装成Worker对象
        Worker w = new Worker(firstTask);
        final Thread t = w.thread;
        final ReentrantLock lock = this.mainLock;
        lock.lock();
        try {
            //添加该线程
            workers.add(w);
            // precheck that t is startable
            if (t.isAlive()) throw new IllegalThreadStateException();
            workerAdded = true;
        } finally {
            lock.unlock();
        }
        if (workerAdded) {
            //线程启动
            t.start();
            workerStarted = true;
        }
        return workerStarted;
    }

    /**
     * 线程获取任务并执行
     *
     * @param w
     */
    final void runWorker(Worker w) {
        Runnable task = w.firstTask;
        w.firstTask = null;
        while (task != null || (task = getTask()) != null) {
            try {
                task.run();
            } finally {
                task = null;
            }
        }
        //获取任务为null，则移除该线程
        processWorkerExit(w);
    }

    /**
     * 移除线程
     *
     * @param w
     */
    private void processWorkerExit(Worker w) {
        final ReentrantLock mainLock = this.mainLock;
        mainLock.lock();
        try {
            workers.remove(w);
        } finally {
            mainLock.unlock();
        }
    }

    /**
     * 获取任务
     *
     * @return
     */
    private Runnable getTask() {
        // Did the last poll() time out?
        boolean timedOut = false;
        //死循环
        for (; ; ) {
            int wc = threadCount.get();

            // 线程获取任务是否需要计时
            boolean timed = wc > corePoolSize;
            // 若线程获取task超时||当前线程数大于maxPoolSize||workQueue为空？ 线程数减1，返回null
            if ((wc > maxPoolSize || (timed && timedOut)) && (wc > 1 || taskQueue.isEmpty())) {
                wc = threadCount.get();
                if (threadCount.compareAndSet(wc, wc - 1)) return null;
                continue;
            }
            try {
                // 大于核心池数量限制的线程需要计时获取task
                Runnable r = timed ? taskQueue.poll(keepAliveTime, TimeUnit.NANOSECONDS) : taskQueue.take();
                //task获取并返回
                if (r != null) return r;
                //task获取超时
                timedOut = true;
            } catch (InterruptedException retry) {
                //线程中断
                timedOut = false;
            }
        }
    }

    /**
     * 任务拒绝策略
     *
     * @param command
     * @throws RuntimeException
     */
    private void reject(Runnable command) throws RuntimeException {
        System.out.printf("已达到最大线程数%d! 拒绝任务：%s\n", this.maxPoolSize, command.toString());
    }

    /**
     * 执行线程的封装类
     */
    private class Worker implements Runnable {
        Runnable firstTask;
        Thread thread;

        public Worker(Runnable firstTask) {
            this.firstTask = firstTask;
            this.thread = threadFactory.newThread(this);
        }

        @Override
        public void run() {
            runWorker(this);
        }
    }

    /**
     * 简单测试
     *
     * @param args
     */
    public static void main(String[] args) {
        MyThreadPool myThreadPool = new MyThreadPool(2, 20, 3000, TimeUnit.MILLISECONDS,
                new NamedThreadFactory(), new LinkedBlockingQueue<>(20));
        for (int i = 0; i < 100; i++) {
            myThreadPool.exec(() -> {
                System.out.printf("%s： Task Executed by %s\n", LocalDateTime.now(), Thread.currentThread().getName());
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
