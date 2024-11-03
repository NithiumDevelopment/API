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

}
