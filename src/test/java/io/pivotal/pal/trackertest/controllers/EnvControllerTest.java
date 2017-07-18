package io.pivotal.pal.trackertest.controllers;

import io.pivotal.pal.tracker.controllers.EnvController;
import org.junit.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class EnvControllerTest {
    @Test
    public void getEnv() throws Exception {
        EnvController controller = new EnvController(
                "Port",
                "InstanceIndex",
                "Memory Limit",
                "CfInstanceAddress"
        );

        Map<String, String> env = controller.getEnv();

        assertThat(env.get("PORT")).isEqualTo("Port");
        assertThat(env.get("CF_INSTANCE_INDEX")).isEqualTo("InstanceIndex");
        assertThat(env.get("MEMORY_LIMIT")).isEqualTo("Memory Limit");
        assertThat(env.get("CF_INSTANCE_ADDR")).isEqualTo("CfInstanceAddress");
    }

}