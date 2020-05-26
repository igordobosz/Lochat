package bpawl.lochat.ui;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

import bpawl.lochat.R;
import bpawl.lochat.databinding.ChatRoomCreationFragmentBinding;
import bpawl.lochat.viewmodels.ChatRoomCreationViewModel;
import bpawl.lochat.viewmodels.LochatViewModel;
import bpawl.lochat.viewmodels.SigningViewModel;

public class ChatRoomCreation extends LochatFragment {
    private ChatRoomCreationViewModel viewModel;

    public static ChatRoomCreation newInstance() {
        return new ChatRoomCreation();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final ChatRoomCreationFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.chat_room_creation_fragment, container, false);
        viewModel = (ChatRoomCreationViewModel) _resolveViewModel(ChatRoomCreationViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        View view = binding.getRoot();
        Spinner duration = (Spinner) view.findViewById(R.id.chooseDurationSpinner);
        final ArrayAdapter<String> durationAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, new ArrayList<String>(viewModel.getAvailableDurations()));
        duration.setAdapter(durationAdapter);
        duration.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                viewModel.setSelectedDuration(durationAdapter.getItem(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        duration.setSelection(0);

        Spinner range = (Spinner) view.findViewById(R.id.chooseRangeSpinner);
        final ArrayAdapter<String> rangeAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, new ArrayList<String>(viewModel.getAvailableRanges()));
        range.setAdapter(rangeAdapter);
        range.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                viewModel.setSelectedRange(rangeAdapter.getItem(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        range.setSelection(0);

        return view;
    }

    @Override
    protected void _initViewModel(LochatViewModel viewModel) {
        ChatRoomCreationViewModel chatRoomCreationViewModel = (ChatRoomCreationViewModel) viewModel;
        _lochatApp.appComponent.inject(chatRoomCreationViewModel);
        chatRoomCreationViewModel.init();
    }
}
