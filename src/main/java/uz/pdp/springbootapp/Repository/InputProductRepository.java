package uz.pdp.springbootapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springbootapp.Entity.InputProduct;

public interface InputProductRepository extends JpaRepository<InputProduct,Integer> {

}
