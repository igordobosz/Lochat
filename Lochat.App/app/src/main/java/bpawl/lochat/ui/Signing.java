package bpawl.lochat.ui;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import bpawl.lochat.R;
import bpawl.lochat.databinding.SigningFragmentBinding;
import bpawl.lochat.viewmodels.LochatViewModel;
import bpawl.lochat.viewmodels.SigningViewModel;

public class Signing extends LochatFragment {
    public SigningViewModel viewModel;

    public static Signing newInstance() {
        return new Signing();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final SigningFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.signing_fragment, container, false);
        viewModel = (SigningViewModel) _resolveViewModel(SigningViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    protected void _initViewModel(LochatViewModel viewModel) {
        SigningViewModel signingViewModel = (SigningViewModel) viewModel;
        _lochatApp.appComponent.inject(signingViewModel);
        signingViewModel.init();
    }
}
