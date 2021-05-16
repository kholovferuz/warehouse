package uz.pdp.springbootapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springbootapp.Entity.Input;

public interface InputRepository extends JpaRepository<Input, Integer> {
    boolean existsByInvoiceNumber(String invoiceNumber);
}
