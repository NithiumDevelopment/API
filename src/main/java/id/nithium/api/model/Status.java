package id.nithium.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Status extends AbstractModel {
    private StatusType statusType;
    private boolean locked;

    public enum StatusType {
        UP,
        DOWN;
    }
}
