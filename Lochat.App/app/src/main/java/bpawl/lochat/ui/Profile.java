package bpawl.lochat.ui;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import bpawl.lochat.R;
import bpawl.lochat.databinding.ProfileFragmentBinding;
import bpawl.lochat.model.ChatRoom;
import bpawl.lochat.ui.adapters.ChatListItemAdapter;
import bpawl.lochat.ui.utils.IDeleteChatRoomListener;
import bpawl.lochat.viewmodels.LochatViewModel;
import bpawl.lochat.viewmodels.ProfileViewModel;

public class Profile extends LochatFragment implements IDeleteChatRoomListener {

    private ProfileViewModel _viewModel;
    private ListView _userChatRooms;

    public static Profile newInstance() {
        return new Profile();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final ProfileFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.profile_fragment, container, false);
        _viewModel = (ProfileViewModel) _resolveViewModel(ProfileViewModel.class);
        binding.setViewModel(_viewModel);
        binding.setLifecycleOwner(this);
        View view = binding.getRoot();
        _userChatRooms = view.findViewById(R.id.userChatRooms);
        _inflateChatList();
        return binding.getRoot();
    }

    @Override
    protected void _initViewModel(LochatViewModel viewModel) {
        ProfileViewModel profileViewModel = (ProfileViewModel) viewModel;
        _lochatApp.appComponent.inject(profileViewModel);
    }

    private void _inflateChatList() {
        ArrayList<ChatRoom> chatRooms = new ArrayList<ChatRoom>(_viewModel.getUserCreatedChatRooms());
        ChatListItemAdapter adapter = new ChatListItemAdapter(getContext(), chatRooms, this);
        _userChatRooms.setAdapter(adapter);
    }

    @Override
    public void OnDeleteChatRoom(String id) {
        _viewModel.deleteChatRoom(id);
        _inflateChatList();
    }
}
