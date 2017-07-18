package io.pivotal.pal.tracker.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EnvController {

    private final String port;
    private final String instanceIndex;
    private final String memoryLimit;
    private final String cfInstanceAddress;

    public EnvController(@Value("${PORT:NOT SET}") String port,
                         @Value("${CF_INSTANCE_INDEX:NOT SET}") String instanceIndex,
                         @Value("${MEMORY_LIMIT:NOT SET}") String memoryLimit,
                         @Value("${CF_INSTANCE_ADDR:NOT SET}") String cfInstanceAddress) {

        this.port = port;
        this.instanceIndex = instanceIndex;
        this.memoryLimit = memoryLimit;
        this.cfInstanceAddress = cfInstanceAddress;
    }

    @GetMapping("/env")
    public Map<String, String> getEnv() {
        Map<String, String> env = new HashMap<>();
        env.put("PORT", port);
        env.put("CF_INSTANCE_INDEX", instanceIndex);
        env.put("CF_INSTANCE_ADDR", cfInstanceAddress);
        env.put("MEMORY_LIMIT", memoryLimit);
        return env;
    }
}
