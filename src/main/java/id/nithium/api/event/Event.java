package id.nithium.api.event;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Event {

    private boolean cancelled;
}
