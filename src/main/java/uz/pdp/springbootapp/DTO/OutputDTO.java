package uz.pdp.springbootapp.DTO;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OutputDTO {

    @NotNull(message = "Date should not be empty")
    private LocalDate date;
    @NotNull(message = "InvoiceNumber should not be empty")
    private String invoiceNumber;

    @NotNull(message = "WarehouseId should not be empty")
    private Integer warehouseId;

    @NotNull(message = "ClientId should not be empty")
    private Integer clientId;

    @NotNull(message = "CurrencyId should not be empty")
    private Integer currencyId;
}
