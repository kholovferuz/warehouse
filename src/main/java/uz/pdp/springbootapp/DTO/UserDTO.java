package uz.pdp.springbootapp.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;


@Getter
@Setter
public class UserDTO {
    @NotNull(message = "FirstName should not be empty")
    private String firstName;
    @NotNull(message = "LastName should not be empty")
    private String lastName;
    @NotNull(message = "PhoneNumber should not be empty")
    private String phoneNumber;
    @NotNull(message = "Password should not be empty")
    private String password;

    // warehouse
    @NotNull(message = "WarehousesId should not be empty")
    private List<Integer> warehousesId;
}
