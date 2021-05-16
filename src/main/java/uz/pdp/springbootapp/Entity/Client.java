package uz.pdp.springbootapp.Entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import uz.pdp.springbootapp.Entity.template.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Entity
public class Client extends AbsEntity {
    @Column(nullable = false,unique = true)
    private String phoneNumber;
}
