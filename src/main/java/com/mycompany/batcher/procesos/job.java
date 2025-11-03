package com.mycompany.batcher.procesos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author iagom
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class job {
    private String id;
    private String name;
    private int priority;
    private resources resources;
    private workload workload;

    public job() {
    }

    
    
    public job(String id, String name, int priority, resources resources, workload workload) {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.resources = resources;
        this.workload = workload;
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

    public workload getWorkload() {
        return workload;
    }

    public void setWorkload(workload workload) {
        this.workload = workload;
    }

 
    
    
    
    
}
