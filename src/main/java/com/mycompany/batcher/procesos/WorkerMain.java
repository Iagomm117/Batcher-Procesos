package com.mycompany.batcher.procesos;

/**
 *
 * @author iagom
 */
public class WorkerMain {
    
    public static void main(String[] args) throws InterruptedException{
        if(args.length == 4){
            long ms = Long.parseLong(args[1]);
            int s = (int) (ms / 1000);
            for (int i = 1; i < s; i++) {
                Thread.sleep(i* 1000);
                System.out.println("jobId: " + args[0] + ", progress " + (double) i*100/s);
                System.exit(0);
            }
        }
        else{
            System.exit(1);
        }
        
    }
}
