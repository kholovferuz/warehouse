package uz.pdp.springbootapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springbootapp.Entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByFirstNameAndLastNameAndPhoneNumber(String firstName, String lastName, String phoneNumber);
}
