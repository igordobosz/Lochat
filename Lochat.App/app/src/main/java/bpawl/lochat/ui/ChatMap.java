package bpawl.lochat.ui;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import bpawl.lochat.R;
import bpawl.lochat.databinding.ChatMapFragmentBinding;
import bpawl.lochat.viewmodels.ChatMapViewModel;
import bpawl.lochat.viewmodels.LochatViewModel;

public class ChatMap extends LochatFragment {
    private ChatMapViewModel viewModel;

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
        return binding.getRoot();
    }

    @Override
    protected void _initViewModel(LochatViewModel viewModel) {

    }
}
