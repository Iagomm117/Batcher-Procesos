package com.mycompany.batcher.procesos;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
    private static final List<job> doneList = new ArrayList();
    private static final List<job> failedList = new ArrayList();
    private static long endTime;

    public static void main(String[] args) throws IOException, InterruptedException {
            
        menu();

    }

    private static void menu() throws IOException, InterruptedException {
        System.out.println("Elige le metodo para de planificacion de recursos");
        System.out.println("1. FCFS");
        System.out.println("2. Round Robin");
        Scanner scan = new Scanner(System.in);
        int numero = scan.nextInt();
        if (numero == 1) {
            LectorYAML.leerFicheroYAML(jobs);

            for (int i = 0; i < jobs.size(); i++) {
                job job = jobs.get(i);
                cambiarEstado(job);
                FCFS();
                lanzarProcesoFCFS(job.getId(), job.getWorkload().getDuration_ms(), job.getResources().getCpu_cores(), job.getResources().getMemory(), job);
                monitorFCFS();
                cambiarEstado(job);
                
            }
        } else if (numero == 2) {
            System.out.println("No se pudo hacer ya que no esta disponible");
            System.exit(0);
        }
        else{
            System.err.print("Lo que se ha introducido no esta en el menu");
            System.exit(-1);
        }
    }
    
    private static void monitorFCFS(){
        System.out.print("_______________________________________________\n");
        System.out.print("| BATCHER MONITOR - Politica: FCFS            |\n");
        System.out.print("_______________________________________________\n");
        System.out.println("| Recursos: CPU " + (freeCpu) + "/" + CPU_Cores + "  | RAM " + (freeRAM) + "/" + RAM +"MB          |\n");
        System.out.print("_______________________________________________\n");
        System.out.println("| READY   (" +readyList.size()+")             |");
        System.out.println("| WAITING (" + waitingList.size()+")          |");
        System.out.println("| DONE     ("+ doneList.size()+")             |");
        System.out.println("| FAILED   (" + failedList.size()+")          |");
        System.out.println("| RUNNING   (" + running.size()+")          |");
        
    }
    private static void cambiarEstado(job job) {
        String[] aux = job.getResources().getMemory().split(" ");
        int memory = Integer.parseInt(aux[0]);
        if (job.getState().equals("NEW") || job.getState().equals("WAITING")) {
            if (job.getResources().getCpu_cores() <= freeCpu && memory <= freeRAM) {
                freeCpu = freeCpu - job.getResources().getCpu_cores();
                freeRAM = freeRAM - memory;
                job.setState("READY");
                readyList.add(job);
            } else {
                job.setState("WAITING");
                waitingList.add(job);
            }
        }
        if (job.getState().equals("DONE") || job.getState().equals("FAILED")) {
            freeCpu = freeCpu + job.getResources().getCpu_cores();
            freeRAM = freeRAM + memory;
        }
    }

    public static void FCFS() {
        while (!readyList.isEmpty()) {
            job job = readyList.get(0);
            readyList.remove(job);
            job.setState("RUNNING");
            job.setArrivalTime(System.currentTimeMillis());
            running.add(job);
        }

    }

    public static void lanzarProcesoFCFS(String id, int duration_ms, int cpu_cores, String mem_mb, job job) throws IOException, InterruptedException {
        String[] aux = mem_mb.split(" ");
        int memory = Integer.parseInt(aux[0]);
        String cp = System.getProperty("java.class.path");
        ProcessBuilder pb = new ProcessBuilder("java", "-cp", cp, "com.mycompany.batcher.procesos.WorkerMain", id, Integer.toString(duration_ms), Integer.toString(cpu_cores), mem_mb);

        pb.inheritIO();

        Process p = pb.start();
        p.waitFor();
        if (p.exitValue() == 0) {
            running.remove(job);
            doneList.add(job);
            freeCpu = freeCpu + job.getResources().getCpu_cores();
            freeRAM = freeRAM + memory;
        } else {
            running.remove(job);
            failedList.add(job);
            freeCpu = freeCpu + job.getResources().getCpu_cores();
            freeRAM = freeRAM + memory;
        }

    }
}
