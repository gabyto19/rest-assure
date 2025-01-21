package ge.spaceint.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class HelperFunctions {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static<T> T deserialize(Response response, Class<T> pojoClass) {
        try{
            return objectMapper.readValue(response.getBody().asString(), pojoClass);
        } catch (JsonProcessingException e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static<K> String serialize(K payloadObject) {
        try{
            return objectMapper.writeValueAsString(payloadObject);
        } catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static String getCurrentUtcDate() {
        // Get the current UTC date
        LocalDate currentDate = Instant.now().atZone(ZoneId.of("UTC")).toLocalDate();
        return currentDate.toString();
    }

    public static String parseDate(String dateTimeString) {
        // Parse the input string into an Instant
        Instant instant = Instant.parse(dateTimeString);

        // Convert the Instant to a LocalDate in UTC (or your desired timezone)
        LocalDate date = instant.atZone(ZoneId.of("UTC")).toLocalDate();

        // Format the LocalDate as a string (optional, can be skipped if you only need LocalDate)
        return date.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public static String getDuration(long millis){
        Duration duration = Duration.ofMillis(millis);
        long SS = duration.toSecondsPart();
        return SS + " Seconds";

    }
}
