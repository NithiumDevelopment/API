package id.nithium.api.server;

import id.nithium.api.PrivateAPI;
import id.nithium.libraries.proxima.canopussimpleserver.Server;
import id.nithium.libraries.proxima.model.DataSetter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "of")
public class ServerSetter {

    private final PrivateAPI privateAPI = PrivateAPI.getInstance();
    @NonNull
    private final Server server;

    public void changeData(String data, Object newData) {
        privateAPI.getRedisManager().publishObject("ServerDataChange", DataSetter.of(server, data, newData));
    }

    public void setEnvironment(String environment) {
        changeData("Environment", environment);
        server.setEnvironment(environment);
    }

    public void setServerType(int id) {
        changeData("ServerType", id);
        server.setServerType(id);
    }

    public void setModeType(String modeType) {
        changeData("ModeType", modeType);
        server.setModeType(modeType);
    }

    public void setMaximumSize(int size) {
        changeData("MaximumSize", size);
        server.setMaximumSize(size);
    }

    public void setRestricted(boolean restricted) {
        changeData("Restricted", restricted);
        server.setRestricted(restricted);
    }
}
