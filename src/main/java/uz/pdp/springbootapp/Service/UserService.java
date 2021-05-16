package uz.pdp.springbootapp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springbootapp.DTO.Result;
import uz.pdp.springbootapp.DTO.UserDTO;
import uz.pdp.springbootapp.Entity.User;
import uz.pdp.springbootapp.Entity.Warehouse;
import uz.pdp.springbootapp.Repository.UserRepository;
import uz.pdp.springbootapp.Repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    WarehouseRepository warehouseRepository;

    // CREATE new user
    public Result addUser(UserDTO userDTO) {
        boolean exists = userRepository.existsByFirstNameAndLastNameAndPhoneNumber(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getPhoneNumber());
        if (exists) {
            return new Result("This user already exists", false);
        }

        // user
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setCode(userDTO.getCode());
        user.setPassword(userDTO.getPassword());

        // warehouses
        List<Warehouse> warehouses = warehouseRepository.findAllById(userDTO.getWarehousesId());
        for (Warehouse warehouse : warehouses) {
            if (warehouse != null) {
                user.setWarehouses(warehouses);
            }
            return new Result("Warehouse with this id is not found", false);
        }


        userRepository.save(user);
        return new Result("User added", true);
    }

    // READ all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // READ users by id
    public User getUserById(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElseGet(User::new);
    }

    // UPDATE user by id
    public Result updateUser(Integer id, UserDTO userDTO) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            boolean exists = userRepository.existsByFirstNameAndLastNameAndPhoneNumber(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getPhoneNumber());
            if (exists) {
                return new Result("This user already exists", false);
            }

            // user
            User editedUser = new User();
            editedUser.setFirstName(userDTO.getFirstName());
            editedUser.setLastName(userDTO.getLastName());
            editedUser.setPhoneNumber(userDTO.getPhoneNumber());
            editedUser.setCode(userDTO.getCode());
            editedUser.setPassword(userDTO.getPassword());

            // warehouses

            userRepository.save(editedUser);
            return new Result("User updated", true);
        }
        return new Result("User with this id is not found", false);
    }

    // DELETE user by id
    public Result deleteUser(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userRepository.deleteById(id);
            return new Result("User deleted", true);
        }
        return new Result("User with this id is not found", false);
    }
}
