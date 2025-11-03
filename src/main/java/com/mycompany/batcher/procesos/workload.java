package com.mycompany.batcher.procesos;

/**
 *
 * @author iagom
 */
class workload {
    private int duration_ms;

    public workload(){
        
    }
    
    public workload(int duration_ms) {
        this.duration_ms = duration_ms;
    }

    public int getDuration_ms() {
        return duration_ms;
    }

    public void setDuration_ms(int duration_ms) {
        this.duration_ms = duration_ms;
    }

    
    
}
