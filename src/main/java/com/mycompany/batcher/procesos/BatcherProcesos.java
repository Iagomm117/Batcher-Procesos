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

    private static int CPU_Cores = 4;
    private static int RAM = 2048;

    public static void main(String[] args) throws IOException {
        List<job> readyList = new ArrayList();
        List<job> waitingList = new ArrayList();
        HashMap<String,job> running = new HashMap<>();
        LectorYAML.leerFicheroYAML();
        
        job job = new job();
        
        String[] aux = job.getResources().getMemory().split(" ");
        int memory = Integer.parseInt(aux[0]);
        
        if(job.getState().equals("NEW")){
            
            if(job.getResources().getCpu_cores() < CPU_Cores && memory < RAM){
                
                job.setState("READY");
                CPU_Cores = CPU_Cores - job.getResources().getCpu_cores();
                RAM = RAM - memory;
                readyList.add(job);
            }
            else{
                job.setState("WAITING");
                waitingList.add(job);
            }
        }
        

    }
    

    
    
    
}
