package bpawl.lochat.viewmodels;

import android.os.Handler;
import androidx.lifecycle.MutableLiveData;
import javax.inject.Inject;
import bpawl.lochat.services.IFragmentNavigation;
import bpawl.lochat.services.IUserManager;
import bpawl.lochat.ui.utils.ISignInRequestListener;

public class SigningViewModel extends LochatViewModel {
    private ISignInRequestListener _listener;

    @Inject
    public IFragmentNavigation fragmentNavigation;

    @Inject
    public IUserManager userManager;

    private MutableLiveData<Boolean> _showSignInButton;
    private MutableLiveData<Boolean> _showSpinner;

    @Override
    public void init() {
        _showSignInButton = new MutableLiveData<>(false);
        _showSpinner = new MutableLiveData<>(true);
        super.init();
    }

    public MutableLiveData<Boolean> getShowSignInButton() {
        return _showSignInButton;
    }

    public MutableLiveData<Boolean> getShowSpinner() {
        return _showSpinner;
    }

    public void setSignInRequestedListener(ISignInRequestListener listener) {
        _listener = listener;
    }

    public void signIn() {
        if(_listener != null) {
            _showSignInButton.setValue(false);
            _showSpinner.setValue(true);
            _listener.onSignInRequested();
        }
    }

    public void signingFailed() {
        _showSignInButton.setValue(true);
        _showSpinner.setValue(false);
    }
}