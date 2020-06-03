package bpawl.lochat.services;

import android.content.Context;

import com.google.android.gms.location.FusedLocationProviderClient;

import bpawl.lochat.services.utils.IIsLocationServiceLoadedListener;
import bpawl.lochat.services.utils.ILocationUpdatedListener;

public interface ILocationService {
    void init(FusedLocationProviderClient locationProvider, Context context);
    boolean isLoaded();
    double getLongitude();
    double getLatitude();
    void listenForLoaded(IIsLocationServiceLoadedListener listener);
    void getLocationUpdates(ILocationUpdatedListener listener);
    boolean isAccessGranted();
}
