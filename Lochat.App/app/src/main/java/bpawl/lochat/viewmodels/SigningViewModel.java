package bpawl.lochat.viewmodels;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import bpawl.lochat.Services.ISampleService;

public class SigningViewModel extends ViewModel {
    private static final String SP_KEY = "lochat-user-data";
    private static final String USERNAME_KEY = "user-name";

    private int _minUserNameLength = 3;
    private int _maxUserNameLength = 20;

    private SharedPreferences _sp;

    private MutableLiveData<String> _userName;
    private MediatorLiveData<Boolean> _hasUserNameInvalidCharacters;
    private MediatorLiveData<Boolean> _hasUserNameInvalidLength;
    private MediatorLiveData<Boolean> _isUserNameValid;

    private ISampleService _sampleService;

    @Inject
    public SigningViewModel(ISampleService sampleService, SharedPreferences preferences) {
        super();
        _sp = preferences;

        _userName = new MutableLiveData<String>(_getUserNameFromStorage());

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

        _sampleService = sampleService;
        _maxUserNameLength = _sampleService.getValue();
    }

    public MutableLiveData<String> getUserName() { return _userName; }

    public LiveData<Boolean> getIsUserNameValid() {
        return _isUserNameValid;
    }

    public LiveData<Boolean> getHasUserNameInvalidCharacters() { return _hasUserNameInvalidCharacters; }

    public LiveData<Boolean> getHasUserNameInvalidLength() { return _hasUserNameInvalidLength; }

    public int getMinUserNameLength() {
        return _minUserNameLength;
    }

    public int getMaxUserNameLength() {
        return _maxUserNameLength;
    }

    public void saveUsername() {
        if (_isUserNameValid.getValue()) {
            _saveUserNameInStorage();
        }
    }

    private String _getUserNameFromStorage() {
        return _sp.getString(USERNAME_KEY, "");
    }

    private void _saveUserNameInStorage() {
        SharedPreferences.Editor editor = _sp.edit();
        editor.putString(USERNAME_KEY, _userName.getValue());
        editor.commit();
    }

    private boolean _hasInvalidCharacters(String s) {
        return !s.isEmpty() && !s.matches("^[a-zA-Z0-9]+$");
    }

    private boolean _hasInvalidLength(String s) {
        int length = s.length();
        return length < _minUserNameLength || length > _maxUserNameLength;
    }
}