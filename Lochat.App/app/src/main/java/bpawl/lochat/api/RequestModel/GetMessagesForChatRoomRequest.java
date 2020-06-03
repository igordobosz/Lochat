package bpawl.lochat.api.RequestModel;

import com.google.gson.annotations.SerializedName;

public class GetMessagesForChatRoomRequest {
    @SerializedName("chatroomId")
    public String chatroomId;
}
