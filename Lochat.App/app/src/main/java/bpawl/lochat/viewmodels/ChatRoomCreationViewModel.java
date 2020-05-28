package bpawl.lochat.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

import javax.inject.Inject;
import bpawl.lochat.services.IChatManager;
import bpawl.lochat.services.IFragmentNavigation;

public class ChatRoomCreationViewModel extends LochatViewModel {
    @Inject
    public IFragmentNavigation fragmentNavigation;

    @Inject
    public IChatManager chatManager;


    private HashMap<String, Integer> _availableDurations;
    private HashMap<String, Integer> _availableRanges;

    private String _selectedDuration;
    private String _selectedRange;

    private MutableLiveData<String> _chatName;
    private MediatorLiveData<Boolean> _isChatNameValid;

    @Override
    public void init() {
        _availableDurations = new HashMap<>();
        _availableDurations.put("12 godzin", 6);
        _availableDurations.put("24 godziny", 12);
        _availableDurations.put("48 godzin", 24);

        _availableRanges = new HashMap<>();
        _availableRanges.put("1 kilometr", 1);
        _availableRanges.put("3 kilometry", 3);
        _availableRanges.put("5 kilometrów", 5);

        _chatName = new MutableLiveData<String>("");
        _isChatNameValid = new MediatorLiveData<Boolean>();
        _isChatNameValid.addSource(_chatName, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                _isChatNameValid.setValue(_chatName.getValue().trim().length() > 0);
            }
        });
    }

    public String[] getAvailableDurations() {
        return _availableDurations.keySet().stream().sorted().toArray(String[]::new);
    }

    public String[] getAvailableRanges() {
        return _availableRanges.keySet().stream().sorted().toArray(String[]::new);
    }

    public String getSelectedDuration() {
        return _selectedDuration;
    }

    public void setSelectedDuration(String selectedDuration) {
        this._selectedDuration = selectedDuration;
    }

    public String getSelectedRange() {
        return _selectedRange;
    }

    public void setSelectedRange(String selectedRange) {
        this._selectedRange = selectedRange;
    }

    public MutableLiveData<String> getChatName() {
        return _chatName;
    }

    public LiveData<Boolean> getIsChatNameValid() {
        return _isChatNameValid;
    }

    public void createChatRoom() {
        if(_chatName.getValue().trim().length() > 0) {
            chatManager.createChatRoom(_chatName.getValue(), _availableDurations.getOrDefault(_selectedDuration, 6), _availableRanges.getOrDefault(_selectedRange, 1));
            fragmentNavigation.back();
        }
    }
}