package ge.spaceint.models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ResourcesData {
    @JsonProperty("url")
    public int id;
    @JsonProperty("url")
    public String name;
    @JsonProperty("url")
    public int year;
    @JsonProperty("url")
    public String color;
    @JsonProperty("url")
    public String pantone_value;
}
