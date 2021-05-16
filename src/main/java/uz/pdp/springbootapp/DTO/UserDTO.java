package uz.pdp.springbootapp.DTO;

import lombok.Data;

import java.util.List;
import java.util.Set;


@Data
public class UserDTO {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String code;
    private String password;

    // warehouse
    private List<Integer> warehousesId;
}
