package ge.spaceint.models.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Setter;

@Setter
@Builder
public class UserLogin {
    @JsonProperty("email")
    public String email;
    @JsonProperty("password")
    public String password;
}
