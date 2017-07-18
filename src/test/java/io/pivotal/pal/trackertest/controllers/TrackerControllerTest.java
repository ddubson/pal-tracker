package io.pivotal.pal.trackertest.controllers;

import io.pivotal.pal.tracker.controllers.TrackerController;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TrackerControllerTest {
    @Test
    public void itSaysHello() throws Exception {
        TrackerController controller = new TrackerController("Welcome Message");

        assertThat(controller.sayHello()).isEqualTo("Welcome Message");
    }

}
