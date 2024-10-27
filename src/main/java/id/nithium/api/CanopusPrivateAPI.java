package id.nithium.api;

import id.nithium.api.model.Server;
import id.nithium.api.model.Status;
import id.nithium.api.type.DataType;
import lombok.SneakyThrows;

public class CanopusPrivateAPI {

    private final PrivateAPI privateAPI;
    private final DataType dataType = DataType.DATA_3;

    public CanopusPrivateAPI(PrivateAPI privateAPI) {
        this.privateAPI = privateAPI;
    }

    @SneakyThrows
    public Status getStatus() {
        return privateAPI.get(DataType.DATA_3, "status", Status.class);
    }

    @SneakyThrows
    public void setStatus(Status.StatusType statusType, boolean locked) {
        Status status1 = new Status(statusType, locked);
        privateAPI.post(DataType.DATA_3, privateAPI.GSON.toJson(status1), "status", Status.class);
    }

    @SneakyThrows
    public Server getServer(String serverName) {
        return privateAPI.get(DataType.DATA_3, "server?name=" + serverName, Server.class);
    }

    @SneakyThrows
    public Server getServer(int port) {
        return privateAPI.get(DataType.DATA_3, "server?port=" + port, Server.class);
    }
}