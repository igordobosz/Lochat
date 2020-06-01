package bpawl.lochat.model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    public String Id;
    @SerializedName("username")
    public String Username;
    @SerializedName("email")
    public String Email;
}