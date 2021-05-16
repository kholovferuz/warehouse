package uz.pdp.springbootapp.DTO;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    @NotNull(message = "Name should not be empty")
    private String name;

    @NotNull(message = "CategoryId should not be empty")
    private Integer categoryId;

    @NotNull(message = "MeasurementId should not be empty")
    private Integer measurementId;

    @NotNull(message = "PhotoId should not be empty")
    private Integer photoId;


    @NotNull(message = "Code should not be empty")
    private String code;
}
