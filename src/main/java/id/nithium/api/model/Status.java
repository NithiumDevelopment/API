package id.nithium.api.model;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Status {
    @SerializedName("statusType")
    private StatusType statusType;
    @SerializedName("locked")
    private boolean locked;

    public enum StatusType {
        UP,
        DOWN;
    }
}
