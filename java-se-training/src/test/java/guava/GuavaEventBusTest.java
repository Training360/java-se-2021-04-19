package guava;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GuavaEventBusTest {

    EventBus eventBus = new EventBus();

    // Tanuló teszt
    // Benne hagyom a projektbe
    @Test
    void sendEvent() {
        List<String> events = new ArrayList<>();

        eventBus.register(new Object() {
            @Subscribe
            public void handleEvent(String event) {
                events.add(event);
            }
        });

        eventBus.post("JohnDoeEvent");
        eventBus.post("JackDoeEvent");

        assertEquals(List.of("JohnDoeEvent", "JackDoeEvent"),
                events);
    }
}
