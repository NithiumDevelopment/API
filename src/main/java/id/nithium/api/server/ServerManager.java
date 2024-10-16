package id.nithium.api.server;

import id.nithium.api.PrivateAPI;
import id.nithium.api.exception.NithiumException;
import id.nithium.libraries.proxima.canopussimpleserver.Server;
import id.nithium.libraries.proxima.simpletask.Task;
import id.nithium.libraries.proxima.simpletask.TaskType;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class ServerManager {

    private final PrivateAPI privateAPI = PrivateAPI.getInstance();
    @Getter
    @Setter
    private List<Server> servers = new ArrayList<>();

    public void createServer(String name) {
        if (getServer(name) != null) throw new NithiumException("Cannot create server name: " + name + " already exists.");

        privateAPI.getRedisManager().publishObject("CreateServer", Task.builder()
                .taskType(TaskType.CREATE_SERVER)
                .name(name)
                .build());
    }

    public void destroyServer(String name) {
        if (getServer(name) == null) throw new NithiumException("Cannot destroy server name: " + name + " not exists.");

        privateAPI.getRedisManager().publishObject("DestroyServer", Task.builder()
                .taskType(TaskType.DESTROY_SERVER)
                .name(name)
                .build());
    }

    public void loadServers() {
        privateAPI.getRedisManager().publishObject("LoadServers", Task.builder()
                .taskType(TaskType.LOAD_SERVERS)
                .name("")
                .build());
    }

    public void loadServer(String name) {
        if (getServer(name) != null) throw new NithiumException("Cannot load server name: " + name + " already loaded.");

        privateAPI.getRedisManager().publishObject("LoadServer", Task.builder()
                .taskType(TaskType.LOAD_SERVER)
                .name(name)
                .build());
    }

    public Server getServer(String name) {
        for (Server server : getServers()) {
            if (server.getName().equals(name)) {
                return server;
            }
        } return null;
    }
}
