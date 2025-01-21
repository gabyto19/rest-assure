package ge.spaceint.models.requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Setter;

@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Resource {
    @JsonProperty("id")
    public int id;
    @JsonProperty("name")
    public String name;
    @JsonProperty("year")
    public int year;
    @JsonProperty("color")
    public String color;
    @JsonProperty("pantone_value")
    public String pantone_value;
}
