package com.udacity.catpoint.service;


import com.udacity.catpoint.app.StatusListener;
import com.udacity.catpoint.data.AlarmStatus;
import com.udacity.catpoint.data.ArmStatus;
import com.udacity.catpoint.data.SecurityRepository;
import com.udacity.catpoint.data.Sensor;
import com.udacity.images.service.FakeImageService;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;

public class SecurityService {

    private FakeImageService imageService;
    private SecurityRepository securityRepository;
    private Set<StatusListener> statusListeners = new HashSet<>();

    public SecurityService(SecurityRepository securityRepository, FakeImageService imageService) {
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

    public ArmStatus getRepoArmStatus() {
        return securityRepository.getArmStatus();
    }

    public void setRepoArmStatus(ArmStatus armStatus) {
        securityRepository.setArmStatus(armStatus);
    }

    public void setRepoAlarmStatus(AlarmStatus status) {
        securityRepository.setAlarmStatus(status);
        statusListeners.forEach(sl -> sl.notify(status));
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

    public ArmStatus getRepooArmingStatus() {
        return securityRepository.getArmStatus();
    }

    public void setRepoArmingStatus(ArmStatus armStatus) {
        if(armStatus == ArmStatus.DISARMED) {
            setRepoAlarmStatus(AlarmStatus.NO_ALARM);
        }
        securityRepository.setArmStatus(armStatus);
    }
}
