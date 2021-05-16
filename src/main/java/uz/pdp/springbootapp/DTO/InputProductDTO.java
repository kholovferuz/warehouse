package uz.pdp.springbootapp.DTO;

import lombok.Data;
import uz.pdp.springbootapp.Entity.Input;
import uz.pdp.springbootapp.Entity.Product;

import java.util.Date;

@Data
public class InputProductDTO {
    private Double amount;
    private Double price;
    private Date expiryDate;

    // input
    private Integer inputId;

    // product
    private Integer productId;
}
