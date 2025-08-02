package com.udacity.catpoint.data;

import java.util.Set;

public class FakeSecurityRepository implements SecurityRepository{

    private static FakeSecurityRepository instance;
    Sensor window = new Sensor("window",SensorType.WINDOW);
    Sensor door = new Sensor("door",SensorType.DOOR);
    private Set<Sensor> sensors = Set.of(window,door);
    private AlarmStatus alarmStatus = AlarmStatus.NO_ALARM;
    private ArmStatus armStatus = ArmStatus.DISARMED;
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
    public void setAlarmStatus(AlarmStatus alarmStatus) {
        this.alarmStatus = alarmStatus;
    }

    @Override
    public AlarmStatus getAlarmStatus() {
        return alarmStatus;
    }

    @Override
    public ArmStatus getArmStatus() {
        return armStatus;
    }

    @Override
    public void setArmStatus(ArmStatus armStatus) {
        this.armStatus = armStatus;
    }
}
