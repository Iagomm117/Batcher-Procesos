package com.mycompany.batcher.procesos;

/**
 *
 * @author iagom
 */
public class job {
    private String id;
    private String name;
    private int priority;
    private resources resources;
    private workload duration_ms;

    public job(String id, String name, int priority, resources resources, workload duration_ms) {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.resources = resources;
        this.duration_ms = duration_ms;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public resources getResources() {
        return resources;
    }

    public void setResources(resources resources) {
        this.resources = resources;
    }

    public workload getDuration_ms() {
        return duration_ms;
    }

    public void setDuration_ms(workload duration_ms) {
        this.duration_ms = duration_ms;
    }
    
    
    
    
}
