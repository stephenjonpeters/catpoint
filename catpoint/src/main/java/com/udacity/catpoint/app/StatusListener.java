package com.udacity.catpoint.app;


import com.udacity.catpoint.data.AlarmStatus;

public interface StatusListener {
    void notify(AlarmStatus status);
    void catDetected(boolean catDetected);
    void sensorStatusChanged();
}
