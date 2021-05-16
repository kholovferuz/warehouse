package uz.pdp.springbootapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootapp.DTO.InputDTO;
import uz.pdp.springbootapp.DTO.Result;
import uz.pdp.springbootapp.Entity.Input;
import uz.pdp.springbootapp.Service.InputService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/input")
public class InputController {
    @Autowired
    InputService inputService;

    // CREATE new input
    @PostMapping("/addNew")
    public HttpEntity<?> addNewInput(@Valid @RequestBody InputDTO inputDTO) {
        Result result = inputService.addInput(inputDTO);
        return ResponseEntity.status(result.isSuccess() ? 201 : 409).body(result);
    }

    // READ all inputs
    @GetMapping("/getAll")
    public HttpEntity<?> readAllInput() {
        List<Input> inputs = inputService.readAllInputs();
        return ResponseEntity.status(200).body(inputs);
    }

    // READ ipnut by id
    @GetMapping("/getOne/{id}")
    public HttpEntity<?> readInputsById(@PathVariable Integer id) {
        Input inputById = inputService.getInputById(id);
        return ResponseEntity.status(200).body(inputById);
    }

    // UPDATE input by id
    @PutMapping("/update/{id}")
    public HttpEntity<?> updateInputById(@PathVariable Integer id, @Valid @RequestBody InputDTO inputDTO) {
        Result updateInput = inputService.updateInput(id, inputDTO);
        return ResponseEntity.status(updateInput.isSuccess() ? 202 : 409).body(updateInput);
    }

    // DELETE input by id
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteInputById(@PathVariable Integer id) {
        Result deleteInput = inputService.deleteInput(id);
        return ResponseEntity.status(deleteInput.isSuccess() ? 202 : 409).body(deleteInput);

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
