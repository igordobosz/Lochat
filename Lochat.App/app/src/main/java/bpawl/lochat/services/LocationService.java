package bpawl.lochat.services;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import bpawl.lochat.services.utils.IIsLocationServiceLoadedListener;
import bpawl.lochat.services.utils.ILocationUpdatedListener;

public class LocationService implements ILocationService, OnCompleteListener<Location> {
    private FusedLocationProviderClient _locationProvider;
    private LocationRequest _locationRequest;
    private LocationCallback _locationCallback;
    private Context _context;

    private double _longitude = 0;
    private double _latitude = 0;
    private boolean _isLoaded = false;

    private IIsLocationServiceLoadedListener _loadedListener;
    private ILocationUpdatedListener _locationUpdatedListener;

    @Override
    public void init(FusedLocationProviderClient locationProvider, Context context) {
        _context = context;
        _locationProvider = locationProvider;
        if(isAccessGranted()){
            _locationProvider.getLastLocation().addOnCompleteListener(this);
        }
    }

    @Override
    public double getLongitude() {
        return _longitude;
    }

    @Override
    public double getLatitude() {
        return _latitude;
    }

    @Override
    public boolean isLoaded() {
        return _isLoaded;
    }

    @Override
    public void listenForLoaded(IIsLocationServiceLoadedListener listener) {
        _loadedListener = listener;
    }

    @Override
    public void getLocationUpdates(ILocationUpdatedListener listener) {
        if(isAccessGranted()) {
            _locationUpdatedListener = listener;
            if (_locationCallback != null) {
                _locationProvider.removeLocationUpdates(_locationCallback);
            }
            _locationRequest = LocationRequest.create().setInterval(10000).setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            _locationCallback = new LocationCallback() {
                @Override
                public void onLocationResult(LocationResult locationResult) {
                    _onLocationResult(locationResult);
                }
            };
            _locationProvider.requestLocationUpdates(_locationRequest, _locationCallback, Looper.getMainLooper());
        }
    }

    @Override
    public void onComplete(@NonNull Task<Location> task) {
        if (task.isSuccessful() && task.getResult() != null) {
            Location result = task.getResult();
            _longitude = result.getLongitude();
            _latitude = result.getLatitude();
            _isLoaded = true;
            if (_loadedListener != null) {
                _loadedListener.onLocationServiceLoaded();
            }
        } else {
            _locationProvider.getLastLocation().addOnCompleteListener(this);
        }
    }

    private void _onLocationResult(LocationResult locationResult) {
        if (_locationUpdatedListener != null && locationResult != null) {
            Location location = locationResult.getLastLocation();
            _longitude = location.getLongitude();
            _latitude = location.getLatitude();
            _locationUpdatedListener.onLocationUpdated();
        }
    }

    public boolean isAccessGranted() {
        return ContextCompat.checkSelfPermission(_context, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(_context, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }
}