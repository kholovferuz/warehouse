package uz.pdp.springbootapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springbootapp.Entity.Output;

public interface OutputRepository extends JpaRepository<Output, Integer> {
    boolean existsByInvoiceNumber(String invoiceNumber);
}
