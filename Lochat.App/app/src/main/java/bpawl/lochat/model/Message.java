package bpawl.lochat.model;

import com.google.gson.annotations.SerializedName;

import java.time.Instant;
import java.util.Objects;

public class Message {
    @SerializedName("id")
    public String Id;
    @SerializedName("authorId")
    public String AuthorId;
    @SerializedName("chatroomId")
    public String ChatroomId;
    @SerializedName("text")
    public String Text;
    @SerializedName("creationTime")
    public Instant CreationTime;
    @SerializedName("authorName")
    public String AuthorName;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(Id, message.Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }
}
