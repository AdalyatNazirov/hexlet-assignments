package exercise.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

// BEGIN
@Getter
@Setter
public class CarUpdateDTO {
    private JsonNullable<String> manufacturer;
    private JsonNullable<Integer> enginePower;
    private JsonNullable<String> model;
}
// END
