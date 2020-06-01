package bpawl.lochat.ui;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;

import bpawl.lochat.R;
import bpawl.lochat.databinding.ProfileFragmentBinding;
import bpawl.lochat.model.ChatRoom;
import bpawl.lochat.ui.adapters.ChatListItemAdapter;
import bpawl.lochat.ui.utils.IDeleteChatRoomListener;
import bpawl.lochat.viewmodels.LochatViewModel;
import bpawl.lochat.viewmodels.ProfileViewModel;
import bpawl.lochat.viewmodels.utils.IViewModelInitListener;

public class Profile extends LochatFragment implements IDeleteChatRoomListener, IViewModelInitListener {

    private ProfileViewModel _viewModel;
    private ListView _userChatRooms;
    private ArrayList<ChatRoom> _chatRooms;
    private ChatListItemAdapter _adapter;

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
        _userChatRooms.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                _viewModel.selectChatRoom(_chatRooms.get(position).Id);
            }
        });

        _viewModel.setInitListener(this);

        return view;
    }

    @Override
    protected void _initViewModel(LochatViewModel viewModel) {
        ProfileViewModel profileViewModel = (ProfileViewModel) viewModel;
        _lochatApp.appComponent.inject(profileViewModel);
        profileViewModel.init();
    }

    private void _inflateChatList() {
        _chatRooms = new ArrayList<ChatRoom>(_viewModel.getUserCreatedChatRooms().getValue());
        _adapter = new ChatListItemAdapter(getContext(), _chatRooms, this);
        _userChatRooms.setAdapter(_adapter);
    }

    @Override
    public void OnDeleteChatRoom(ChatRoom chatRoom) {
        _viewModel.deleteChatRoom(chatRoom.Id);
    }

    @Override
    public void onViewModelInit() {
        _inflateChatList();
        _viewModel.getUserCreatedChatRooms().observe(this, new Observer<Collection<ChatRoom>>() {
            @Override
            public void onChanged(Collection<ChatRoom> chatRooms) {
                _chatRooms.clear();
                _chatRooms.addAll(chatRooms);
                _adapter.notifyDataSetChanged();
            }
        });
    }
}
