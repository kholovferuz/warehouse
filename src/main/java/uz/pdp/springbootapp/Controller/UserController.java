package uz.pdp.springbootapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootapp.DTO.Result;
import uz.pdp.springbootapp.DTO.UserDTO;
import uz.pdp.springbootapp.Entity.User;
import uz.pdp.springbootapp.Service.UserService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    // CREATE new user
    @PostMapping("/addNew")
    public HttpEntity<?> addUsers(@Valid @RequestBody UserDTO userDTO) {
        Result result = userService.addUser(userDTO);
        return ResponseEntity.status(result.isSuccess() ? 201 : 409).body(result);
    }

    // READ all users
    @GetMapping("/getAll")
    public HttpEntity<?> readAllUsers() {
        List<User> userList = userService.getAllUsers();
        return ResponseEntity.status(200).body(userList);
    }

    // READ user by id
    @GetMapping("/getOne/{id}")
    public HttpEntity<?> readUsersById(@PathVariable Integer id) {
        User user = userService.getUserById(id);
        return ResponseEntity.status(200).body(user);
    }

    // UPDATE user by id
    @PutMapping("/update/{id}")
    public HttpEntity<?> updateUserById(@PathVariable Integer id, @Valid @RequestBody UserDTO userDTO) {
        Result updateUser = userService.updateUser(id, userDTO);
        return ResponseEntity.status(updateUser.isSuccess() ? 202 : 409).body(updateUser);
    }

    // DELETE user by id
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteUserById(@PathVariable Integer id) {
        Result deleteUsers = userService.deleteUser(id);
        return ResponseEntity.status(deleteUsers.isSuccess() ? 202 : 409).body(deleteUsers);

    }

    // EXCEPTION HANDLER
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidExecption(MethodArgumentNotValidException ex) {
        Map<String, String> mistakes = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            mistakes.put(fieldName, errorMessage);
        });
        return mistakes;
    }
}
