package id.nithium.api.model;

import lombok.Getter;

@Getter
public enum Environment {
    FULL(2),
    BETA(1),
    DEVELOPMENT(0);

    private int id;

    Environment(int id) {
        this.id = id;
    }

    public static Environment getEnvironmentById(int id) {
        for (Environment environment : values()) {
            if (environment.getId() == id) {
                return environment;
            }
        } return null;
    }
}
