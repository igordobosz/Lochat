package bpawl.lochat.ui;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import bpawl.lochat.R;
import bpawl.lochat.databinding.UsernameChangeFragmentBinding;
import bpawl.lochat.viewmodels.LochatViewModel;
import bpawl.lochat.viewmodels.UsernameChangeViewModel;

public class UsernameChange extends LochatFragment {
    public UsernameChangeViewModel viewModel;

    public static UsernameChange newInstance() {
        return new UsernameChange();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final UsernameChangeFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.username_change_fragment, container, false);
        viewModel = (UsernameChangeViewModel) _resolveViewModel(UsernameChangeViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    protected void _initViewModel(LochatViewModel viewModel) {
        UsernameChangeViewModel usernameChangeViewModel = (UsernameChangeViewModel) viewModel;
        _lochatApp.appComponent.inject(usernameChangeViewModel);
        usernameChangeViewModel.init();
    }
}
