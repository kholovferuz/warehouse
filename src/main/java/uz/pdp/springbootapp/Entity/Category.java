package uz.pdp.springbootapp.Entity;


import lombok.*;
import uz.pdp.springbootapp.Entity.template.AbsEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Category extends AbsEntity {
    @ManyToOne
    private Category parentCategory;


}
