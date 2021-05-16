package uz.pdp.springbootapp.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class InputProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private Double price;

    private Date expiryDate;

    @ManyToOne
    private Input input;

    @ManyToOne
    private Product product;

}
