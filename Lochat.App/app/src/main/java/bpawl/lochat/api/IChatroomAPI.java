package bpawl.lochat.api;

import java.util.List;

import bpawl.lochat.api.RequestModel.GetChatRoomByLocationRequest;
import bpawl.lochat.api.RequestModel.GetChatRoomByOwnerRequest;
import bpawl.lochat.model.ChatRoom;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface IChatroomAPI {
    @POST("Chatroom/Create")
    Call<ChatRoom> createChatroom (@Header("Authorization") String credentials, @Body ChatRoom toCreate);

    @DELETE("Chatroom/Delete")
    Call<Response> deleteChatroom (@Header("Authorization") String credentials, @Body ChatRoom toDelete);

    @POST("Chatroom/Get")
    Call<List<ChatRoom>> getChatRoomsByOwner (@Header("Authorization") String credentials, @Body GetChatRoomByOwnerRequest request);

    @POST("Chatroom/Get")
    Call<List<ChatRoom>> getChatRoomsByLocation (@Header("Authorization") String credentials, @Body GetChatRoomByLocationRequest request);
}