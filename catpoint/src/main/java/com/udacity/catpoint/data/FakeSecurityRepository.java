package com.udacity.catpoint.data;

import java.util.Set;

public class FakeSecurityRepository implements SecurityRepository{

    private static FakeSecurityRepository instance;
    private Set<Sensor> sensors;
    private AlarmStatus alarmStatus = AlarmStatus.NO_ALARM;
    private ArmStatus armingStatus = ArmStatus.DISARMED;
    private FakeSecurityRepository(){}

    public static synchronized FakeSecurityRepository getInstance() {
        if (instance == null) {
            instance = new FakeSecurityRepository();
        }
        return instance;
    }


    @Override
    public void addSensor(Sensor sensor) {
        sensors.add(sensor);
    }

    @Override
    public void removeSensor(Sensor sensor) {
        sensors.remove(sensor);
    }

    @Override
    public void updateSensor(Sensor sensor) {
        sensors.remove(sensor);
        sensors.add(sensor);
    }

    @Override
    public Set<Sensor> getSensors() {
        return sensors;
    }

    @Override
    public AlarmStatus getAlarmStatus() {
        return this.alarmStatus;
    }

    @Override
    public void setAlarmStatus(AlarmStatus alarmStatus) {
        this.alarmStatus = alarmStatus;
    }

    @Override
    public ArmStatus getArmingStatus() {
        return armingStatus;
    }

    @Override
    public void setArmingStatus(ArmStatus armingStatus) {
        this.armingStatus = armingStatus;
    }
}
