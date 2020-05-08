package bpawl.lochat.ui;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import bpawl.lochat.R;
import bpawl.lochat.databinding.ChatListFragmentBinding;
import bpawl.lochat.viewmodels.ChatListViewModel;
import bpawl.lochat.viewmodels.LochatViewModel;

public class ChatList extends LochatFragment {
    private ChatListViewModel viewModel;

    public static ChatList newInstance() {
        return new ChatList();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final ChatListFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.chat_list_fragment, container, false);
        viewModel = (ChatListViewModel) _resolveViewModel(ChatListViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    protected void _initViewModel(LochatViewModel viewModel) {

    }
}
