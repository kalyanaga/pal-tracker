package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class EnvController {

    String port;
    String memoryLimit;
    String cfInstanceIndex;
    String cfInstanceAddr;

    public EnvController(@Value("${PORT: NOT SET}") String port,
                         @Value("${MEMORY_LIMIT: NOT SET}") String memoryLimit ,
                         @Value("${CF_INSTANCE_INDEX: NOT SET}") String cfInstanceIndex,
                         @Value("${CF_INSTANCE_ADDR: NOT SET}") String cfInstanceAddr )
    {
      this.port = port;
      this.memoryLimit = memoryLimit;
      this.cfInstanceIndex = cfInstanceIndex;
      this.cfInstanceAddr = cfInstanceAddr;
    }

    @GetMapping("/env")
    public HashMap<String, String> getEnvs() {
        return getEnv();






    }

   public HashMap<String, String> getEnv(){
        HashMap<String,String> envs = new HashMap<>();
        envs.put("PORT",port);
        envs.put("MEMORY_LIMIT",memoryLimit);
        envs.put("CF_INSTANCE_INDEX",cfInstanceIndex);
        envs.put("CF_INSTANCE_ADDR",cfInstanceAddr);
        return envs;
    }

}
