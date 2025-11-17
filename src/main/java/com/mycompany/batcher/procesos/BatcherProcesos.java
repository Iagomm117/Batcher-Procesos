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
    private static final List<job> running = new ArrayList();

    public static void main(String[] args) throws IOException {

        LectorYAML.leerFicheroYAML(jobs);

        for (int i = 0; i < jobs.size(); i++) {
            job job = jobs.get(i);
            cambiarEstado(job);
        }

    }

    private static void cambiarEstado(job job) {
        String[] aux = job.getResources().getMemory().split(" ");
        int memory = Integer.parseInt(aux[0]);
        if (job.getState().equals("NEW")) {
            if (job.getResources().getCpu_cores() <= freeCpu && memory <= freeRAM) {
                freeCpu = freeCpu - job.getResources().getCpu_cores();
                freeRAM = freeRAM - memory;
                job.setState("READY");
                System.out.println("READY");
                readyList.add(job);
            } else {
                job.setState("WAITING");
                System.out.println("WAITING");
                waitingList.add(job);
            }
        }
        if (job.getState().equals("DONE")) {
            freeCpu = freeCpu + job.getResources().getCpu_cores();
            freeRAM = freeRAM + memory;
        }
    }

    private static void FCFS(List<job> readyList, List<job> running) {
        while (!readyList.isEmpty()) {
            job job = readyList.get(0);
            readyList.remove(job);
            job.setState("RUNNING");
            job.setArrivalTime(System.currentTimeMillis());
            running.add(job);
        }

    }

    private static void RoundRobin(List<job> readyList, List<job> running, long quatumMs) {
        while (!readyList.isEmpty()) {
            job job = readyList.get(0);
            readyList.remove(job);
            job.setState("RUNNING");
            job.setArrivalTime(System.currentTimeMillis());
            running.add(job);
            
            
            readyList.add(job);
        }

    }

}
