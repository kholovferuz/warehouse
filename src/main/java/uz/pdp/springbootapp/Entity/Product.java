package uz.pdp.springbootapp.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.pdp.springbootapp.Entity.template.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Product extends AbsEntity {
    @ManyToOne(optional = false)
    private Category category;

    @ManyToOne(optional = false)
    private Measurement measurement;

    @OneToOne
    private Attachment attachment;

    @Column(unique = true, nullable = false)
    private String code;
}
