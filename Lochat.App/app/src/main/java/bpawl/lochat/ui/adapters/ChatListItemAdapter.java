package bpawl.lochat.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import bpawl.lochat.R;
import bpawl.lochat.model.ChatRoom;
import bpawl.lochat.ui.utils.IDeleteChatRoomListener;

public class ChatListItemAdapter extends ArrayAdapter<ChatRoom> {
    private Context _context;
    private List<ChatRoom> _chatRooms;
    private IDeleteChatRoomListener _listener;

    public ChatListItemAdapter(@NonNull Context context, @NonNull List<ChatRoom> objects, IDeleteChatRoomListener listener) {
        super(context, 0, objects);
        _context = context;
        _chatRooms = objects;
        _listener = listener;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null)
            listItem = LayoutInflater.from(_context).inflate(R.layout.chat_room_item, parent, false);

        final ChatRoom current = _chatRooms.get(position);

        TextView chatTitle = (TextView) listItem.findViewById(R.id.chatTitle);
        chatTitle.setText(current.Name);

        TextView chatDesc = (TextView) listItem.findViewById(R.id.chatDesc);
        chatDesc.setText(current.getRemainingTimeString());

        Button chatDel = (Button) listItem.findViewById(R.id.chatDel);
        chatDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _listener.OnDeleteChatRoom(current.Id);
            }
        });

        return listItem;
    }
}