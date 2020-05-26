package bpawl.lochat.ui;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import bpawl.lochat.LochatApplication;
import bpawl.lochat.viewmodels.LochatViewModel;

public abstract class LochatFragment extends Fragment {
    protected LochatApplication _lochatApp;

    protected abstract void _initViewModel(LochatViewModel viewModel);

    protected LochatViewModel _resolveViewModel(Class viewModelClass) {
        LochatViewModel viewModel = (LochatViewModel) (new ViewModelProvider(this)).get(viewModelClass);
        _initViewModel(viewModel);
        return viewModel;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        _lochatApp = (LochatApplication) getActivity().getApplication();
    }

    public void onNavigatedTo() { }
}
