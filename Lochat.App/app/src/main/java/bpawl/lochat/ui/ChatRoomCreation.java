package bpawl.lochat.ui;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import bpawl.lochat.R;
import bpawl.lochat.databinding.ChatRoomCreationFragmentBinding;
import bpawl.lochat.viewmodels.ChatRoomCreationViewModel;
import bpawl.lochat.viewmodels.LochatViewModel;

public class ChatRoomCreation extends LochatFragment {
    private ChatRoomCreationViewModel viewModel;

    public static ChatRoomCreation newInstance() {
        return new ChatRoomCreation();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final ChatRoomCreationFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.chat_room_creation_fragment, container, false);
        viewModel = (ChatRoomCreationViewModel) _resolveViewModel(ChatRoomCreationViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    protected void _initViewModel(LochatViewModel viewModel) {

    }
}
