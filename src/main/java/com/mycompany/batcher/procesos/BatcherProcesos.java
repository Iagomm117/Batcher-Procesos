package com.mycompany.batcher.procesos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author iagom
 */
public class BatcherProcesos {

    private static final int CPU_Cores = 2;
    private static final int RAM = 2048;
    private static int freeCpu = CPU_Cores;
    private static int freeRAM = RAM;
    private static final List<job> jobs = new ArrayList();
    private static final List<job> readyList = new ArrayList();
    private static final List<job> waitingList = new ArrayList();
    private static final HashMap<String,job> running = new HashMap<>();

    public static void main(String[] args) throws IOException {
        
        
        LectorYAML.leerFicheroYAML(jobs);
        
        
        for (int i = 0; i < jobs.size(); i++) {
            job job = jobs.get(i);
            cambiarEstado(job);
        }
        
        
        

    }
    
    public static void cambiarEstado(job job){
        String[] aux = job.getResources().getMemory().split(" ");
        int memory = Integer.parseInt(aux[0]);
        if(job.getState().equals("NEW")){
            if(job.getResources().getCpu_cores() <= freeCpu && memory <= freeRAM){  
                freeCpu = freeCpu - job.getResources().getCpu_cores();
                freeRAM = freeRAM - memory;
                job.setState("READY");
                System.out.println("READY");
                readyList.add(job);
            }
            else{
                job.setState("WAITING");
                System.out.println("WAITING");
                waitingList.add(job);
            }
        }
        if(job.getState().equals("DONE")){
            freeCpu = freeCpu + job.getResources().getCpu_cores();
            freeRAM = freeRAM + memory;
        }
    }
    
    public static void FCFS (List<job> readyList,int freeCpu, int freeRAM){
        
    }
    
}
