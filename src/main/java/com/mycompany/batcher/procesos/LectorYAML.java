package com.mycompany.batcher.procesos;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 *
 * @author iagom
 */
public class LectorYAML {

    public static void leerFicheroYAML(List<job> jobs) throws FileNotFoundException, IOException {
        File path = new File("src/main/resources");
        for (File f : path.listFiles()) {
            Reader yaml = new FileReader(f);
            ObjectMapper mapper = new YAMLMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            job job = mapper.readValue(yaml, job.class);
            job.setState("NEW");
            if (job.getResources().getMemory().contains("MB") || job.getResources().getMemory().contains("GB")) {
                if (job.getResources().getMemory().contains("GB")) {
                    String aux[] = job.getResources().getMemory().split(" ");
                    double mem = Double.parseDouble(aux[0]);
                    mem = mem * 1000;
                    int memory = (int) mem;
                    job.setResources(new resources(job.getResources().getCpu_cores(), Integer.toString(memory) + " MB"));
                }
                
                jobs.add(job);
            }
        }

    }

}
