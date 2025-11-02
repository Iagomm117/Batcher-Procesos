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
    private int duration_ms;

    public job() {
    }

    
    
    public job(String id, String name, int priority, resources resources, int duration_ms) {
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

    public int getDuration_ms() {
        return duration_ms;
    }

    public void setDuration_ms(int duration_ms) {
        this.duration_ms = duration_ms;
    }
    
    
    
    
}
