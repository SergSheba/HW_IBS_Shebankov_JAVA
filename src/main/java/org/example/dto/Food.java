package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Food {
    @JsonProperty("name")
    private String name;
    @JsonProperty("type")
    private String type;
    @JsonProperty("exotic")
    private boolean exotic;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isExotic() {
        return exotic;
    }

    public void setExotic(boolean exotic) {
        this.exotic = exotic;
    }


    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", exotic=" + exotic +
                '}';
    }
}
