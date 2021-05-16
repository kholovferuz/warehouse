package uz.pdp.springbootapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootapp.DTO.InputDTO;
import uz.pdp.springbootapp.DTO.OutputDTO;
import uz.pdp.springbootapp.DTO.Result;
import uz.pdp.springbootapp.Entity.Input;
import uz.pdp.springbootapp.Entity.Output;
import uz.pdp.springbootapp.Service.InputService;
import uz.pdp.springbootapp.Service.OutputService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/output")
public class OutputController {
    @Autowired
    OutputService outputService;

    // CREATE new output
    @PostMapping("/addNew")
    public HttpEntity<?> addOutputs(@Valid @RequestBody OutputDTO outputDTO) {
        Result result = outputService.addOutput(outputDTO);
        return ResponseEntity.status(result.isSuccess() ? 201 : 409).body(result);
    }

    // READ all outputs
    @GetMapping("/getAll")
    public HttpEntity<?> readAllOutput() {
        List<Output> outputs = outputService.readAllOutputs();
        return ResponseEntity.status(200).body(outputs);
    }

    // READ output by id
    @GetMapping("/getOne/{id}")
    public HttpEntity<?> readOutputsById(@PathVariable Integer id) {
        Output output = outputService.getOutputById(id);
        return ResponseEntity.status(200).body(output);
    }

    // UPDATE output by id
    @PutMapping("/update/{id}")
    public HttpEntity<?> updateOutputById(@PathVariable Integer id, @Valid @RequestBody OutputDTO outputDTO) {
        Result updateOutputs = outputService.updateOutput(id, outputDTO);
        return ResponseEntity.status(updateOutputs.isSuccess() ? 202 : 409).body(updateOutputs);
    }

    // DELETE output by id
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteOutputById(@PathVariable Integer id) {
        Result deleteOutputs = outputService.deleteOutput(id);
        return ResponseEntity.status(deleteOutputs.isSuccess() ? 202 : 409).body(deleteOutputs);

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
