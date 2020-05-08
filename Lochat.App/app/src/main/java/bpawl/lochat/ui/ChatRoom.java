package bpawl.lochat.ui;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import bpawl.lochat.R;
import bpawl.lochat.databinding.ChatRoomFragmentBinding;
import bpawl.lochat.viewmodels.ChatRoomViewModel;
import bpawl.lochat.viewmodels.LochatViewModel;

public class ChatRoom extends LochatFragment {
    private ChatRoomViewModel viewModel;

    public static ChatRoom newInstance() {
        return new ChatRoom();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final ChatRoomFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.chat_room_fragment, container, false);
        viewModel = (ChatRoomViewModel) _resolveViewModel(ChatRoomViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    protected void _initViewModel(LochatViewModel viewModel) {

    }
}
