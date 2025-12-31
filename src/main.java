import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class WorkerThread implements Runnable {  
    private String message;  
    public WorkerThread(String s){  
        this.message=s;  
    }  
     public void run() {  
        System.out.println(Thread.currentThread().getName()+" (Start) message = "+message);  
        processmessage();//call processmessage method that sleeps the thread for 2 seconds  
        System.out.println(Thread.currentThread().getName()+" (End)");//prints thread name  
    }  
    private void processmessage() {  
        try {  Thread.sleep(2000);  } catch (InterruptedException e) { e.printStackTrace(); }  
    }  
}  

public class HelloWorld{

     public static void main(String []args){
         ExecutorService executor = Executors.newFixedThreadPool(5);//creating a pool of 5 threads  
        for (int i = 0; i < 10; i++) {  
            Runnable worker = new WorkerThread("WorkerThread #" + i);  
            executor.execute(worker);//calling execute method of ExecutorService  
          }  
        executor.shutdown();  
        while (!executor.isTerminated()) {   }  
  
        System.out.println("Finished all threads");  
     }
}
/*
https://github.com/synthetichealth/synthea/blob/master/src/main/java/org/mitre/synthea/engine/Generator.java
      java.util.concurrent
ExecutorService threadPool = Executors.newFixedThreadPool(threadPoolSize);

 threadPool.submit(() -> generatePerson(index, seed));
 try {
      threadPool.shutdown();
      while (!threadPool.awaitTermination(30, TimeUnit.SECONDS)) {
        System.out.println("Waiting for threads to finish... " + threadPool);
      }
    } catch (InterruptedException e) {
      System.out.println("Generator interrupted. Attempting to shut down associated thread pool.");
      threadPool.shutdownNow();
    }
*/
