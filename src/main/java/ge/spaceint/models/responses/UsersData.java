package ge.spaceint.models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class UsersData {
    @JsonProperty("id")
    public int id;
    @JsonProperty("email")
    public String email;
    @JsonProperty("first_name")
    public String first_name;
    @JsonProperty("last_name")
    public String last_name;
    @JsonProperty("avatar")
    public String avatar;
}
