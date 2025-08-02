package com.udacity.catpoint.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FakeSecurityRepository  {
    Set<Sensor> sensors = new HashSet<>();
    AlarmStatus alarmStatus = AlarmStatus.NO_ALARM;
    ArmStatus armStatus = ArmStatus.DISARMED;

    public void addSensor(Sensor sensor) {
        sensors.add(sensor);
    }

    public void removeSensor(Sensor sensor) {
        sensors.remove(sensor);
    }

    public void updateSensor(Sensor sensor) {
        sensors.add(sensor);
    }

    public Set<Sensor> getSensors() {
        return sensors;
    }

    public boolean containsSensor(Sensor sensor) {
        return sensors.contains(sensor);
    }

    public void setAlarmStatus(AlarmStatus alarmStatus) {
        alarmStatus = alarmStatus;
    }

    public AlarmStatus getAlarmStatus() {
        return alarmStatus;
    }

    public ArmStatus getArmStatus() {
        return armStatus;
    }

    public void setArmStatus(ArmStatus armStatus) {
        armStatus = armStatus;
    }

    public void clearSensors(){
        sensors.clear();
    }
}
