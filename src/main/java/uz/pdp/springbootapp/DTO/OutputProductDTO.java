package uz.pdp.springbootapp.DTO;

import lombok.Data;


@Data
public class OutputProductDTO {
    private Double amount;
    private Double price;

    // output
    private Integer outputId;

    // product
    private Integer productId;
}
