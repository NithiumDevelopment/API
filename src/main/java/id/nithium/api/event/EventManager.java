package id.nithium.api.event;

import id.nithium.api.exception.NithiumException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager {

    private final Map<Class<? extends Event>, List<EventListener<? extends Event>>> listeners = new HashMap<>();

    public <T extends Event> void registerListener(Class<T> eventClass, EventListener<T> listener) {
        listeners.computeIfAbsent(eventClass, k -> new ArrayList<>()).add(listener);
    }

    public <T extends Event> void callEvent(T event) {
        List<EventListener<? extends Event>> eventListeners = listeners.get(event.getClass());
        if (eventListeners != null) {
            for (EventListener<? extends Event> listener : eventListeners) {
                @SuppressWarnings("unchecked")
                        EventListener<T> eventListener = (EventListener<T>) listener;
                eventListener.onEvent(event);
            }
        }
    }
}
