package bpawl.lochat.viewmodels;

import android.content.SharedPreferences;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import javax.inject.Inject;

public class SigningViewModel extends LochatViewModel {

    private static final String USERNAME_KEY = "user-name";

    private int _minUserNameLength = 3;
    private int _maxUserNameLength = 20;


    private MutableLiveData<String> _userName;
    private MediatorLiveData<Boolean> _hasUserNameInvalidCharacters;
    private MediatorLiveData<Boolean> _hasUserNameInvalidLength;
    private MediatorLiveData<Boolean> _isUserNameValid;

    @Inject
    public SharedPreferences sp;

    @Override
    public void init() {
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
    }

    public MutableLiveData<String> getUserName() {
        return _userName;
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
            _saveUserNameInStorage();
        }
    }

    private String _getUserNameFromStorage() {
        return sp.getString(USERNAME_KEY, "");
    }

    private void _saveUserNameInStorage() {
        SharedPreferences.Editor editor = sp.edit();
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