package bpawl.lochat.ui;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import bpawl.lochat.R;
import bpawl.lochat.databinding.ChatRoomFragmentBinding;
import bpawl.lochat.model.Message;
import bpawl.lochat.ui.adapters.ChatMessagesItemAdapter;
import bpawl.lochat.viewmodels.ChatRoomViewModel;
import bpawl.lochat.viewmodels.LochatViewModel;

public class ChatRoom extends LochatFragment {
    private ChatRoomViewModel _viewModel;
    private ListView _messagesListView;
    private List<Message> _messages;
    private ChatMessagesItemAdapter _adapter;

    public static ChatRoom newInstance() {
        return new ChatRoom();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        final ChatRoomFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.chat_room_fragment, container, false);
        _viewModel = (ChatRoomViewModel) _resolveViewModel(ChatRoomViewModel.class);
        binding.setViewModel(_viewModel);
        binding.setLifecycleOwner(this);

        View view = binding.getRoot();
        _messagesListView = view.findViewById(R.id.chatMessages);
        _inflateChatList();

        _viewModel.getMessages().observe(getViewLifecycleOwner(), new Observer<List<Message>>() {
            @Override
            public void onChanged(List<Message> messages) {
                _refreshChatList();
            }
        });

        return view;
    }

    private void _inflateChatList() {
        _messages = new ArrayList<Message>();
        _messages.addAll(_viewModel.getMessages().getValue());
        _adapter = new ChatMessagesItemAdapter(getContext(), _messages);
        _messagesListView.setAdapter(_adapter);
        _messagesListView.setSelection(_adapter.getCount() - 1);
    }

    private void _refreshChatList() {
        _messages.clear();
        _messages.addAll(_viewModel.getMessages().getValue());
        _adapter.notifyDataSetChanged();
        _messagesListView.setSelection(_adapter.getCount() - 1);
    }

    @Override
    protected void _initViewModel(LochatViewModel viewModel) {
        ChatRoomViewModel chatRoomViewModel = (ChatRoomViewModel) viewModel;
        _lochatApp.appComponent.inject(chatRoomViewModel);
        viewModel.init();
    }
}
