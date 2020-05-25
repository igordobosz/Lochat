package bpawl.lochat.model;

import java.time.LocalDateTime;

public class Message {
    public String Id;
    public String AuthorId;
    public User Author;
    public String ChatroomId;
    public ChatRoom Chatroom;
    public String Text;
    public LocalDateTime CreationTime;
}
