package bpawl.lochat.ui;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import bpawl.lochat.R;
import bpawl.lochat.databinding.ChatMapFragmentBinding;
import bpawl.lochat.model.ChatRoom;
import bpawl.lochat.viewmodels.ChatMapViewModel;
import bpawl.lochat.viewmodels.LochatViewModel;

public class ChatMap extends LochatFragment implements OnMapReadyCallback {
    private ChatMapViewModel _viewModel;
    private MapView _mapView;
    private GoogleMap _map;

    public static ChatMap newInstance() {
        return new ChatMap();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final ChatMapFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.chat_map_fragment, container, false);
        _viewModel = (ChatMapViewModel) _resolveViewModel(ChatMapViewModel.class);
        binding.setViewModel(_viewModel);
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
        if(_viewModel.locationService.isAccessGranted()) {
            _map.setMyLocationEnabled(true);
        }
        updateLocation(_viewModel.getPosition().getValue());
        drawMarkers(_viewModel.getChatRooms().getValue());
        _viewModel.getChatRooms().observe(this, new Observer<List<ChatRoom>>() {
            @Override
            public void onChanged(List<ChatRoom> chatRooms) {
                drawMarkers(chatRooms);
            }
        });
        _map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                ChatRoom chat = (ChatRoom) marker.getTag();
                _viewModel.connectToChatRoom(chat);
                return true;
            }
        });
    }

    @Override
    protected void _initViewModel(LochatViewModel viewModel) {
        ChatMapViewModel chatMapViewModel = (ChatMapViewModel) viewModel;
        _lochatApp.appComponent.inject(chatMapViewModel);
        viewModel.init();
    }

    private void updateLocation(LatLng position) {
        _map.moveCamera(CameraUpdateFactory.newLatLng(position));
    }

    private void drawMarkers(List<ChatRoom> chatRooms) {
        _map.clear();
        for(ChatRoom chat : chatRooms) {
            Marker chatMarker = _map.addMarker(new MarkerOptions().position(new LatLng(chat.Latitude, chat.Longitude)).title(chat.Name));
            chatMarker.setTag(chat);
        }
    }
}
