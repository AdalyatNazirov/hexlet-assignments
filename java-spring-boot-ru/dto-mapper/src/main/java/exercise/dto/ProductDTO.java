package exercise.dto;

import lombok.Getter;
import lombok.Setter;
import org.mapstruct.Mapping;

import java.time.LocalDate;

@Setter
@Getter
public class ProductDTO {
    private long id;
    private String title;
    private int price;
    private long vendorCode;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
