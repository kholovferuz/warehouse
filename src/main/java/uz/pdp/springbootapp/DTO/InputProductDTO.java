package uz.pdp.springbootapp.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class InputProductDTO {

    @NotNull(message = "Amount should not be empty")
    private Double amount;

    @NotNull(message = "Price should not be empty")
    private Double price;

    @NotNull(message = "ExpiryDate should not be empty")
    private Date expiryDate;

    // input

    @NotNull(message = "InputId should not be empty")
    private Integer inputId;

    // product
    @NotNull(message = "ProductId should not be empty")
    private Integer productId;
}
