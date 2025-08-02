package com.udacity.catpoint.data;

import java.util.Set;

/**
 * Interface showing the methods our security repository will need to support
 */
public interface SecurityRepository {
    void addSensor(Sensor sensor);
    void removeSensor(Sensor sensor);
    void updateSensor(Sensor sensor);
    void setAlarmStatus(AlarmStatus alarmStatus);
    AlarmStatus getAlarmStatus();
    void setArmStatus(ArmStatus armStatus);
    ArmStatus getArmStatus();
    Set<Sensor> getSensors();


}
