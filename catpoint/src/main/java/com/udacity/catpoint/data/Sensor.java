package com.udacity.catpoint.data;

import com.google.common.collect.ComparisonChain;

import java.util.UUID;

public class Sensor  implements Comparable<Sensor>  {
    public UUID sensorId;
    public String name;
    public Boolean active;
    public SensorType sensorType;

    public Sensor(String name, SensorType sensorType) {
        this.name = name;
        this.sensorType = sensorType;
        this.sensorId = UUID.randomUUID();
        this.active = Boolean.FALSE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public SensorType getSensorType() {
        return sensorType;
    }

    public void setSensorType(SensorType sensorType) {
        this.sensorType = sensorType;
    }

    public UUID getSensorId() {
        return sensorId;
    }

    public void setSensorId(UUID sensorId) {
        this.sensorId = sensorId;
    }

    @Override
    public int compareTo(Sensor o) {
        return ComparisonChain.start()
                .compare(this.name, o.name)
                .compare(this.sensorType.toString(), o.sensorType.toString())
                .compare(this.sensorId, o.sensorId)
                .result();
    }
}
