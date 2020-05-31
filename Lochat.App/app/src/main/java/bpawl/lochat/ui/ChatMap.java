package bpawl.lochat.ui;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import bpawl.lochat.R;
import bpawl.lochat.databinding.ChatMapFragmentBinding;
import bpawl.lochat.viewmodels.ChatMapViewModel;
import bpawl.lochat.viewmodels.LochatViewModel;
import bpawl.lochat.viewmodels.ProfileViewModel;

public class ChatMap extends LochatFragment implements OnMapReadyCallback {
    private ChatMapViewModel viewModel;
    private MapView _mapView;
    private GoogleMap _map;

    public static ChatMap newInstance() {
        return new ChatMap();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final ChatMapFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.chat_map_fragment, container, false);
        viewModel = (ChatMapViewModel) _resolveViewModel(ChatMapViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        View view = binding.getRoot();
        _mapView = (MapView) view.findViewById(R.id.chatRoomsMap);
        _mapView.onCreate(savedInstanceState);
        _mapView.getMapAsync(this);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        _mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        _mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        _mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        _mapView.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        _mapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        _mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        _mapView.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        _map = googleMap;

        // TODO: Remove
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        _map.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        _map.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    protected void _initViewModel(LochatViewModel viewModel) {
        ChatMapViewModel chatMapViewModel = (ChatMapViewModel) viewModel;
        _lochatApp.appComponent.inject(chatMapViewModel);
    }
}
