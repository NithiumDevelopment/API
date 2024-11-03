package id.nithium.api.model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Status extends AbstractModel {
    @SerializedName("statusType")
    private StatusType statusType;
    @SerializedName("locked")
    private boolean locked;

    public Status() {
    }

    public enum StatusType {
        UP,
        DOWN;
    }
}
