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
import bpawl.lochat.viewmodels.ChatRoomCreationViewModel;

public class ChatRoomCreation extends Fragment {

    private ChatRoomCreationViewModel mViewModel;

    public static ChatRoomCreation newInstance() {
        return new ChatRoomCreation();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.chat_room_creation_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ChatRoomCreationViewModel.class);
        // TODO: Use the ViewModel
    }

}
