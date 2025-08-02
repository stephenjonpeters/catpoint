package com.udacity.catpoint.service;


import com.udacity.catpoint.app.StatusListener;
import com.udacity.catpoint.data.AlarmStatus;
import com.udacity.catpoint.data.ArmStatus;
import com.udacity.catpoint.data.FakeSecurityRepository;
import com.udacity.catpoint.data.Sensor;
import com.udacity.images.service.FakeImageService;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SecurityService {

    private FakeImageService imageService;
    private FakeSecurityRepository securityRepository;
    private Set<StatusListener> statusListeners = new HashSet<>();

    public SecurityService(FakeSecurityRepository securityRepository, FakeImageService imageService) {
        this.securityRepository = securityRepository;
        this.imageService = imageService;
    }

    private void catDetected(Boolean cat) {
        if(cat && getRepoArmStatus() == ArmStatus.ARMED_HOME) {
            setRepoAlarmStatus(AlarmStatus.ALARM);
        } else if (cat && getRepoArmStatus() == ArmStatus.ARMED_AWAY) {
            setRepoAlarmStatus(AlarmStatus.ALARM);
        } else {
            setRepoAlarmStatus(AlarmStatus.NO_ALARM);
        }

        statusListeners.forEach(sl -> sl.catDetected(cat));
    }

    public void addStatusListener(StatusListener statusListener) {
        statusListeners.add(statusListener);
    }

    public void removeStatusListener(StatusListener statusListener) {
        statusListeners.remove(statusListener);
    }

    private void handleSensorActivated() {
        if(securityRepository.getArmStatus() == ArmStatus.DISARMED) {
            return; //no problem if the system is disarmed
        }
        switch(securityRepository.getAlarmStatus()) {
            case NO_ALARM:
                    setRepoAlarmStatus(AlarmStatus.PENDING_ALARM);
                    break;
            case PENDING_ALARM:
                    setRepoAlarmStatus(AlarmStatus.ALARM);
                    break;
        }
    }

    private void handleSensorDeactivated() {
        switch(securityRepository.getAlarmStatus()) {
            case PENDING_ALARM:
                   setRepoAlarmStatus(AlarmStatus.NO_ALARM);
                   break;
            case ALARM:
                   setRepoAlarmStatus(AlarmStatus.PENDING_ALARM);
                   break;
        }
    }

    public void changeSensorActivationStatus(Sensor sensor, Boolean active) {
        if(!sensor.getActive() && active) {
            handleSensorActivated();
        } else if (sensor.getActive() && !active) {
            handleSensorDeactivated();
        }
        sensor.setActive(active);
        securityRepository.updateSensor(sensor);
    }

    public void processImage(BufferedImage currentCameraImage) {
        catDetected(imageService.imageContainsCat(currentCameraImage, 50.0f));
    }

    public AlarmStatus getRepoAlarmStatus() {
        return securityRepository.getAlarmStatus();
    }

    public void setRepoAlarmStatus(AlarmStatus status) {
        securityRepository.setAlarmStatus(status);
        statusListeners.forEach(sl -> sl.notify(status));
    }

    public ArmStatus getRepoArmStatus() {
        return securityRepository.getArmStatus();
    }

    public void setRepoArmStatus(ArmStatus armStatus) {
        if(armStatus == ArmStatus.DISARMED) {
            setRepoAlarmStatus(AlarmStatus.NO_ALARM);
        }
        securityRepository.setArmStatus(armStatus);
    }

    public Set<Sensor> getRepoSensors() {
        return securityRepository.getSensors();
    }

    public void addRepoSensor(Sensor sensor) {
        securityRepository.addSensor(sensor);
    }

    public void removeRepoSensor(Sensor sensor) {
        securityRepository.removeSensor(sensor);
    }
    public boolean containsRepoSensor(Sensor sensor) {
        return securityRepository.containsSensor(sensor);
    }

    public void updateRepoSensor(Sensor sensor) {
        securityRepository.addSensor(sensor);
    }

    public void clearRepoSensors() {
        securityRepository.clearSensors();
    }
}
