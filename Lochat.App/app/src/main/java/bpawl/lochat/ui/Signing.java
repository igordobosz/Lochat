package bpawl.lochat.ui;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import bpawl.lochat.R;
import bpawl.lochat.databinding.SigningFragmentBinding;
import bpawl.lochat.viewmodels.SigningViewModel;
import bpawl.lochat.viewmodels.ViewModelProviderFactory;

public class Signing extends Fragment {
    @Inject
    private ViewModelProviderFactory _viewModelProviderFactory;

    public SigningViewModel viewModel;

    public static Signing newInstance() {
        return new Signing();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final SigningFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.signing_fragment, container, false);
        viewModel = ViewModelProviders.of(this, _viewModelProviderFactory).get(SigningViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
