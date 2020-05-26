package bpawl.lochat.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;
import bpawl.lochat.R;
import bpawl.lochat.model.Message;

public class ChatMessagesItemAdapter extends ArrayAdapter<Message> {
    private Context _context;
    private List<Message> _messages;

    public ChatMessagesItemAdapter(@NonNull Context context, @NonNull List<Message> objects) {
        super(context, 0, objects);
        _context = context;
        _messages = objects;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null)
            listItem = LayoutInflater.from(_context).inflate(R.layout.chat_message_item, parent, false);

        Message current = _messages.get(position);

        TextView messageAuthor = (TextView) listItem.findViewById(R.id.messageAuthor);
        messageAuthor.setText(current.Author.Username);

        TextView messageTime = (TextView) listItem.findViewById(R.id.messageDateTime);
        messageTime.setText(current.CreationTime.toString());

        TextView messageContent = (TextView) listItem.findViewById(R.id.messageContent);
        messageContent.setText(current.Text);

        return listItem;
    }
}
