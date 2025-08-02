package com.udacity.catpoint.service;

import com.udacity.catpoint.data.*;
import com.udacity.images.service.FakeImageService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/* TODO
Application Requirements to Test:
A) If alarm is armed and a sensor becomes activated, put the system into pending alarm status.
B) If alarm is armed and a sensor becomes activated and the system is already pending alarm, set the alarm status to alarm.
C) If pending alarm and all sensors are inactive, return to no alarm state.
D) If alarm is active, change in sensor state should not affect the alarm state.
E) If a sensor is activated while already active and the system is in pending state, change it to alarm state.
F) If a sensor is deactivated while already inactive, make no changes to the alarm state.
G) If the image service identifies an image containing a cat while the system is armed-home, put the system into alarm status.
H) If the image service identifies an image that does not contain a cat, change the status to no alarm as long as the sensors are not active.
I) If the system is disarmed, set the status to no alarm.
J) If the system is armed, reset all sensors to inactive.
K) If the system is armed-home while the camera shows a cat, set the alarm status to alarm.

 */

class SecurityServiceTest {
    FakeImageService imageService;
    FakeSecurityRepository securityRepository;
    SecurityService securityService;

    @BeforeEach
    void setUp() {
        imageService = new FakeImageService();
        securityRepository = new FakeSecurityRepository();
        securityService = new SecurityService(securityRepository, imageService);

        Sensor activeWindow = new Sensor("active-window", SensorType.WINDOW);
        activeWindow.setActive(true);
        securityService.addRepoSensor(activeWindow);

        Sensor activeDoor = new Sensor("active-door", SensorType.DOOR);
        activeDoor.setActive(true);
        securityService.addRepoSensor(activeDoor);

        Sensor inactiveWindow = new Sensor("inactive-window", SensorType.WINDOW);
        inactiveWindow.setActive(false);
        securityService.addRepoSensor(inactiveWindow);

        Sensor inactiveDoor = new Sensor("inactive-door", SensorType.DOOR);
        inactiveDoor.setActive(false);
        securityService.addRepoSensor(activeDoor);
    }

    @Test
    void testA(){
    }
/*

    @Test
    void testA (){
        securityService.setRepoAlarmStatus(AlarmStatus.ALARM);
        inActiveWindow.setActive(true);
        securityService.updateRepoSensor(inActiveWindow);
        AlarmStatus alarmStatus = securityService.getRepoAlarmStatus();
        System.out.println("alarmStatus = " + alarmStatus);
        assertEquals(AlarmStatus.PENDING_ALARM,alarmStatus);
    }
*/

}