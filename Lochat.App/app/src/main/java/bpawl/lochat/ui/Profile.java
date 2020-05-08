package bpawl.lochat.ui;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import bpawl.lochat.R;
import bpawl.lochat.databinding.ProfileFragmentBinding;
import bpawl.lochat.viewmodels.LochatViewModel;
import bpawl.lochat.viewmodels.ProfileViewModel;

public class Profile extends LochatFragment {

    public static Profile newInstance() {
        return new Profile();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final ProfileFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.profile_fragment, container, false);
        ProfileViewModel viewModel = (ProfileViewModel) _resolveViewModel(ProfileViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    protected void _initViewModel(LochatViewModel viewModel) {

    }
}
