package ge.spaceint.models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class Users {
    @JsonProperty("page")
    public int page;
    @JsonProperty("per_page")
    public int per_page;
    @JsonProperty("total")
    public int total;
    @JsonProperty("total_pages")
    public int total_pages;
    @JsonProperty("data")
    public ArrayList<UsersData> data;
    @JsonProperty("support")
    public Support support;
}
