package uz.pdp.springbootapp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutputDTO {
    private Timestamp date;
    private String invoiceNumber;
    private String code;

    private Integer warehouseId;

    private Integer clientId;

    private Integer currencyId;
}
