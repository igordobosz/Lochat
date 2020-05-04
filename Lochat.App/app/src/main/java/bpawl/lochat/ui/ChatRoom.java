package bpawl.lochat.ui;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bpawl.lochat.R;
import bpawl.lochat.viewmodels.ChatRoomViewModel;

public class ChatRoom extends Fragment {

    private ChatRoomViewModel mViewModel;

    public static ChatRoom newInstance() {
        return new ChatRoom();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.chat_room_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ChatRoomViewModel.class);
        // TODO: Use the ViewModel
    }

}
