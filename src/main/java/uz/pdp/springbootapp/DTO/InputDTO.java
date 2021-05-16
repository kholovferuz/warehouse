package uz.pdp.springbootapp.DTO;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InputDTO {
    private Timestamp date;

    @NotNull(message = "InvoiceNumber should not be empty")
    private String invoiceNumber;

    @NotNull(message = "WarehouseId should not be empty")
    private Integer warehouseId;


    @NotNull(message = "SupplierId should not be empty")
    private Integer supplierId;


    @NotNull(message = "CurrencyId should not be empty")
    private Integer currencyId;
}
