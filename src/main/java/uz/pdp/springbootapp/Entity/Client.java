package uz.pdp.springbootapp.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.pdp.springbootapp.Entity.template.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Client extends AbsEntity {
    @Column(nullable = false,unique = true)
    private String phoneNumber;
}
