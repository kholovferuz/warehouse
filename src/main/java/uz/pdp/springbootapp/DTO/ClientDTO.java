package uz.pdp.springbootapp.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ClientDTO {
    @NotNull(message = "Name should not be empty")
    private String name;
    @NotNull(message = "Phone number should not be empty")
    private String phoneNumber;
}
