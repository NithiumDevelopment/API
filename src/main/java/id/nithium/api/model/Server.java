package id.nithium.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Server extends AbstractModel {

    private String name;
    private int port;
    private String containerId;
    private int serverType;
    private String modeType;
    private boolean restricted;
    private int maximumSize;

}
