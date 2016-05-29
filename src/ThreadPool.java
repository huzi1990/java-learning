import java.util.LinkedList;
import java.util.List;

/**
 * 线程池实现
 * @author:zixiao.hzx
 * @version $Id:ThreadPool.java,v0.1 2016-05-18 下午6:41 zixiao.hzx Exp $$
 */
public class ThreadPool {

    private static int workNum;

    private static int finishedNum;

    private Worker[] workers;

    private List<Runnable> jobQueue = new LinkedList<Runnable>();

    private static ThreadPool threadPool;

    public ThreadPool() {
        ThreadPool.workNum = 2;
        for(int i=0;i<workNum;i++){
            Worker worker = new Worker();
            workers[i] = worker;
            Thread thread = new Thread(worker);
            thread.start();
        }
    }

    public ThreadPool(int workNum) {
        ThreadPool.workNum = workNum;

        workers = new Worker[workNum];
        //创建时即启动
        for (Worker worker : workers) {
            Thread thread = new Thread(worker);
            thread.start();
        }

    }

    public  void excuter(Runnable runnable){

        synchronized (jobQueue){
            jobQueue.add(runnable);
            jobQueue.notifyAll();
        }
    }

    //静态方法获得单例
    public static ThreadPool getThreadPool(int workNum){
        if(threadPool==null) {
             threadPool = new ThreadPool();
        }
        return threadPool;
    }

    public static ThreadPool getThreadPool(){

        if(threadPool==null){
            threadPool = new ThreadPool();
        }
        return threadPool;
    }

    private class Worker implements Runnable {

        private boolean isRunning = true;

        @Override public void run() {

            Runnable runnable = null;
            //一直运行的线程
            while (isRunning) {
                //保证同步操作
                synchronized (jobQueue) {
                    //如果工作队列为空,等待一段时间
                    while (isRunning && jobQueue.isEmpty()) {
                        try {
                            jobQueue.wait(200);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    //取出队列中最先的一个
                    runnable = jobQueue.remove(0);
                }
                if(runnable!=null) {
                    //真正的运行
                    runnable.run();
                }
                runnable = null;
            }
        }

        public boolean stopWorker() {
            isRunning = false;
            return isRunning;
        }
    }

    public static void main(String[] args){
        ThreadPool threadPool = ThreadPool.getThreadPool();
        threadPool.excuter(new OwnThread());
        threadPool.excuter(new OwnThread());
        threadPool.excuter(new Runnable() {
            @Override public void run() {
                try {
                    Thread.sleep(3000);
                    System.out.println("xxx");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        threadPool.excuter(new OwnThread());

    }

}

class OwnThread implements Runnable{
    public static int count = 1;

    @Override public void run() {
        System.out.println("thread"+count+++"is running...");
    }
}
