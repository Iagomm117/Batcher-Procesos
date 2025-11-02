package com.mycompany.batcher.procesos;

/**
 *
 * @author iagom
 */
class resources {
    private int cpu_cores;
    private String memory;

    public resources() {
    }
    
    

    public resources(int cpu_cores, String memory) {
        this.cpu_cores = cpu_cores;
        this.memory = memory;
    }

    public int getCpu_cores() {
        return cpu_cores;
    }

    public void setCpu_cores(int cpu_cores) {
        this.cpu_cores = cpu_cores;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }
    
    
    
}
