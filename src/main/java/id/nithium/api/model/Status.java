package id.nithium.api.model;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class Status extends AbstractModel {
    private StatusType statusType;
    private boolean locked;

    public enum StatusType {
        UP,
        DOWN;
    }
}
