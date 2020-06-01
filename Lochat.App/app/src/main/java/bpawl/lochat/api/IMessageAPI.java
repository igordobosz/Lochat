package bpawl.lochat.api;

import java.util.List;
import bpawl.lochat.api.RequestModel.GetMessagesForChatRoomRequest;
import bpawl.lochat.model.Message;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface IMessageAPI {
    @POST("Message/Create")
    Call<Message> createMessage (@Header("Authorization") String credentials, @Body Message toCreate);

    @POST("Message/Get")
    Call<List<Message>> getMessagesForChatRoom (@Header("Authorization") String credentials, @Body GetMessagesForChatRoomRequest request);
}