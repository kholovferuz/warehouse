package uz.pdp.springbootapp.DTO;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    @NotNull(message = "Name should not be empty")
    private String name;
    @NotNull(message = "ParentCategoryId should not be empty")
    private Integer parentCategoryId;
}
