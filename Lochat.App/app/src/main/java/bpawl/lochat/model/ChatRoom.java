package bpawl.lochat.model;

import com.google.gson.annotations.SerializedName;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Collection;

public class ChatRoom {
    @SerializedName("id")
    public String Id;
    @SerializedName("ownerId")
    public String OwnerId;
    @SerializedName("name")
    public String Name;
    @SerializedName("creationTime")
    public Instant CreationTime;
    @SerializedName("terminationTime")
    public Instant TerminationTime;
    @SerializedName("latitude")
    public double Latitude;
    @SerializedName("longitude")
    public double Longitude;

    public String getRemainingTimeString() {
        return "55 min";
    }
}
