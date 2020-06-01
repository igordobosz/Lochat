package bpawl.lochat.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import javax.inject.Inject;

import bpawl.lochat.model.User;
import bpawl.lochat.services.IFragmentNavigation;
import bpawl.lochat.services.IUserManager;
import bpawl.lochat.services.utils.IRequestFailedListener;
import bpawl.lochat.services.utils.IRequestSuccessfulListener;

public class UsernameChangeViewModel extends LochatViewModel implements IRequestSuccessfulListener<User>, IRequestFailedListener {
    private int _minUserNameLength = 3;
    private int _maxUserNameLength = 20;


    private MutableLiveData<String> _userName;
    private MutableLiveData<Boolean> _isProcessing;
    private MediatorLiveData<Boolean> _hasUserNameInvalidCharacters;
    private MediatorLiveData<Boolean> _hasUserNameInvalidLength;
    private MediatorLiveData<Boolean> _isUserNameValid;

    @Inject
    public IFragmentNavigation fragmentNavigation;

    @Inject
    public IUserManager userManager;

    @Override
    public void init() {
        _userName = new MutableLiveData<String>(userManager.getUser().Username);
        _isProcessing = new MutableLiveData<Boolean>(false);

        _isUserNameValid = new MediatorLiveData<Boolean>();
        _isUserNameValid.addSource(_userName, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                _isUserNameValid.setValue(!_hasInvalidCharacters(s) && !_hasInvalidLength(s));
            }
        });

        _hasUserNameInvalidCharacters = new MediatorLiveData<Boolean>();
        _hasUserNameInvalidCharacters.addSource(_userName, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                _hasUserNameInvalidCharacters.setValue(_hasInvalidCharacters(s));
            }
        });

        _hasUserNameInvalidLength = new MediatorLiveData<Boolean>();
        _hasUserNameInvalidLength.addSource(_userName, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                _hasUserNameInvalidLength.setValue(_hasInvalidLength(s));
            }
        });

        super.init();
    }

    public MutableLiveData<String> getUserName() {
        return _userName;
    }

    public LiveData<Boolean> getIsProcessing() {
        return _isProcessing;
    }

    public LiveData<Boolean> getIsUserNameValid() {
        return _isUserNameValid;
    }

    public LiveData<Boolean> getHasUserNameInvalidCharacters() {
        return _hasUserNameInvalidCharacters;
    }

    public LiveData<Boolean> getHasUserNameInvalidLength() {
        return _hasUserNameInvalidLength;
    }

    public int getMinUserNameLength() {
        return _minUserNameLength;
    }

    public int getMaxUserNameLength() {
        return _maxUserNameLength;
    }

    public void saveUsername() {
        if (_isUserNameValid.getValue()) {
            _isProcessing.setValue(true);
            userManager.changeUsername(_userName.getValue(), this, this);
        }
    }

    private boolean _hasInvalidCharacters(String s) {
        return !s.isEmpty() && !s.matches("^[a-zA-Z0-9]+$");
    }

    private boolean _hasInvalidLength(String s) {
        int length = s.length();
        return length < _minUserNameLength || length > _maxUserNameLength;
    }

    @Override
    public void onRequestFailed() {
        _isProcessing.setValue(false);
    }

    @Override
    public void onRequestSuccessful(User result) {
        _isProcessing.setValue(false);
        fragmentNavigation.back();
    }
}