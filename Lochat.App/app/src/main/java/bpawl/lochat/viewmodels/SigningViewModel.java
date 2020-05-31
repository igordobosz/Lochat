package bpawl.lochat.viewmodels;

import android.os.Handler;
import androidx.lifecycle.MutableLiveData;
import javax.inject.Inject;
import bpawl.lochat.services.IFragmentNavigation;
import bpawl.lochat.services.IUserManager;
import bpawl.lochat.ui.ChatMap;
import bpawl.lochat.ui.utils.ISigningCompleteListener;

public class SigningViewModel extends LochatViewModel {
    @Inject
    public IFragmentNavigation fragmentNavigation;

    @Inject
    public IUserManager userManager;

    private MutableLiveData<Boolean> _showSignInButton;
    private MutableLiveData<Boolean> _showSpinner;
    private ISigningCompleteListener _signingCompleteListener;

    @Override
    public void init() {
        _showSignInButton = new MutableLiveData<>(false);
        _showSpinner = new MutableLiveData<>(true);
        _trySilentSignIn();
    }

    public MutableLiveData<Boolean> getShowSignInButton() {
        return _showSignInButton;
    }

    public MutableLiveData<Boolean> getShowSpinner() {
        return _showSpinner;
    }

    public void signIn() {
        _showSignInButton.setValue(false);
        _showSpinner.setValue(true);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                userManager.signIn();
                _signingComplete();
            }
        }, 1000);
    }

    public void setSigningCompleteListener(ISigningCompleteListener listener) {
        _signingCompleteListener = listener;
    }

    private void _trySilentSignIn() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                _showSpinner.setValue(false);
                _showSignInButton.setValue(true);
            }
        }, 1000);
    }

    private void _signingComplete() {
        if(_signingCompleteListener != null) {
            _signingCompleteListener.onSigningComplete();
        }
        fragmentNavigation.navigateToFragment(ChatMap.class.getName());
    }
}