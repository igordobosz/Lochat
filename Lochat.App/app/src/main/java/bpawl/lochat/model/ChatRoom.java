package bpawl.lochat.model;

import java.time.LocalDateTime;
import java.util.Collection;

public class ChatRoom {
    public String Id;
    public User Owner;
    public String Name;
    public LocalDateTime CreationTime;
    public LocalDateTime TerminationTime;
    public double Latitude;
    public double Longitude;
    public boolean IsDynamic;
    public Collection<Message> Messages;

    public String getRemainingTimeString() {
        return "55 min";
    }
}
