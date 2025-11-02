package com.mycompany.batcher.procesos;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 *
 * @author iagom
 */
public class LectorYAML {
    public static void leerFicheroYAML() throws FileNotFoundException, IOException{
    Reader yaml = new FileReader("src/main/resources/job.yaml");
    ObjectMapper mapper = new YAMLMapper();
    job job = mapper.readValue(yaml, job.class);
        System.out.println(job.getId() + " " + job.getName() + " " + job.getPriority() + " " + job.getResources().getCpu_cores() + " " + job.getResources().getMemory()+ " " + job.getDuration_ms());
    }
    
    
    
}
