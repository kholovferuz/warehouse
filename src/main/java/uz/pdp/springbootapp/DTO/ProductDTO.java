package uz.pdp.springbootapp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private String name;

    private Integer categoryId;

    private Integer measurementId;

    private Integer photoId;

    private String code;
}
