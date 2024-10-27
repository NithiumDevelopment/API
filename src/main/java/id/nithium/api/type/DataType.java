package id.nithium.api.type;

import lombok.Getter;

@Getter
public enum DataType {
    DATA_1("d1/"),
    DATA_2("d2/"),
    DATA_3("d3/");

    private String name;

    DataType(String name) {
        this.name = name;
    }
}
