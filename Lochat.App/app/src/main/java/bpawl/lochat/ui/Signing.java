package bpawl.lochat.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import bpawl.lochat.MainActivity;
import bpawl.lochat.R;
import bpawl.lochat.databinding.SigningFragmentBinding;
import bpawl.lochat.ui.utils.ISignInRequestListener;
import bpawl.lochat.ui.utils.ISigningFailedListener;
import bpawl.lochat.viewmodels.LochatViewModel;
import bpawl.lochat.viewmodels.SigningViewModel;

public class Signing extends LochatFragment implements ISigningFailedListener, ISignInRequestListener {
    public SigningViewModel viewModel;
    public MainActivity activity;

    public static Signing newInstance() {
        return new Signing();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        activity = ((MainActivity) getActivity());
        activity.hideToolbar();

        final SigningFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.signing_fragment, container, false);
        viewModel = (SigningViewModel) _resolveViewModel(SigningViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        View view = binding.getRoot();
        View signInButton = view.findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.signIn();
            }
        });

        viewModel.setSignInRequestedListener(this);
        activity.setSigningFailedListener(this);

        return view;
    }

    @Override
    protected void _initViewModel(LochatViewModel viewModel) {
        SigningViewModel signingViewModel = (SigningViewModel) viewModel;
        _lochatApp.appComponent.inject(signingViewModel);
        signingViewModel.init();
    }

    @Override
    public void onSignInFailed() {
        viewModel.signingFailed();
    }

    @Override
    public void onSignInRequested() {
        activity.signIn();
    }
}
