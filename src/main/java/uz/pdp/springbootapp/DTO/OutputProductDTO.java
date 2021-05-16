package uz.pdp.springbootapp.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@Getter
@Setter
public class OutputProductDTO {

    @NotNull(message = "Amount should not be empty")
    private Double amount;
    @NotNull(message = "Price should not be empty")
    private Double price;

    // output
    @NotNull(message = "OutputId should not be empty")
    private Integer outputId;

    // product
    @NotNull(message = "ProductId should not be empty")
    private Integer productId;
}
