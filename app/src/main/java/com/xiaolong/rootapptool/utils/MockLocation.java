package com.xiaolong.rootapptool.utils;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;

import com.xiaolong.rootapptool.MyApplication;

public class MockLocation {

    private LocationManager mLocationManager;
    private static MockLocation mockLocation;

    public static MockLocation getInstance() {
        if (mockLocation == null) {
            mockLocation = new MockLocation();
        }
        return mockLocation;
    }

    private MockLocation() {
        mLocationManager = (LocationManager) MyApplication.app.getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
    }


    public void setMockLocation() {
//        mLocationManager.removeTestProvider(LocationManager.GPS_PROVIDER);
        mLocationManager.addTestProvider
                (
                        LocationManager.GPS_PROVIDER,
                        "requiresNetwork" == "",
                        "requiresSatellite" == "",
                        "requiresCell" == "",
                        "hasMonetaryCost" == "",
                        "supportsAltitude" == "",
                        "supportsSpeed" == "",
                        "supportsBearing" == "",

                        android.location.Criteria.POWER_LOW,
                        android.location.Criteria.ACCURACY_FINE
                );

        Location newLocation = new Location(LocationManager.GPS_PROVIDER);

        newLocation.setLatitude(100.00);
        newLocation.setLongitude(100.00);
        newLocation.setTime(System.currentTimeMillis());
        newLocation.setAccuracy(500);
        newLocation.setVerticalAccuracyMeters(1f);
        newLocation.setBearingAccuracyDegrees(20f);
        newLocation.setExtras(new Bundle());
        newLocation.setElapsedRealtimeNanos(System.currentTimeMillis());
        newLocation.setSpeedAccuracyMetersPerSecond(1f);
        mLocationManager.setTestProviderEnabled
                (
                        LocationManager.GPS_PROVIDER,
                        true
                );

        mLocationManager.setTestProviderStatus
                (
                        LocationManager.GPS_PROVIDER,
                        LocationProvider.AVAILABLE,
                        null,
                        System.currentTimeMillis()
                );

        mLocationManager.setTestProviderLocation
                (
                        LocationManager.GPS_PROVIDER,
                        newLocation
                );
    }
}
