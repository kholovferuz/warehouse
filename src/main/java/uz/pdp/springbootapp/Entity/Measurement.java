package uz.pdp.springbootapp.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.pdp.springbootapp.Entity.template.AbsEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Measurement extends AbsEntity {

}
