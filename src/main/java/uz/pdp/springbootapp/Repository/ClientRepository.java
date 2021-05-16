package uz.pdp.springbootapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springbootapp.Entity.Client;
import uz.pdp.springbootapp.Entity.Supplier;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    boolean existsByName(String name);
}
