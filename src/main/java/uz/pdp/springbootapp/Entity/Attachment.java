package uz.pdp.springbootapp.Entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column
    private long size;
    @Column
    private String content_type;
}
