package uz.pdp.springbootapp.Entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import uz.pdp.springbootapp.Entity.template.AbsEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Entity
public class Currency extends AbsEntity {

}
