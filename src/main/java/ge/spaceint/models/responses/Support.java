package ge.spaceint.models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Support {
    @JsonProperty("url")
    public String url;
    @JsonProperty("text")
    public String text;
}
