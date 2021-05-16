package uz.pdp.springbootapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootapp.DTO.InputProductDTO;
import uz.pdp.springbootapp.DTO.Result;
import uz.pdp.springbootapp.Entity.InputProduct;
import uz.pdp.springbootapp.Service.InputProductService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ipnutProduct")
public class InputProductController {
    @Autowired
    InputProductService inputProductService;

    // CREATE new inputProduct
    @PostMapping("/addNew")
    public HttpEntity<?> addInputProducts(@Valid @RequestBody InputProductDTO inputProductDTO) {
        Result result = inputProductService.addInputProduct(inputProductDTO);
        return ResponseEntity.status(result.isSuccess() ? 201 : 409).body(result);
    }

    // READ all inputProduct
    @GetMapping("/getAll")
    public HttpEntity<?> readAllInputProduct() {
        List<InputProduct> inputProducts = inputProductService.readAllInputProducts();
        return ResponseEntity.status(200).body(inputProducts);
    }

    // READ inputProduct by id
    @GetMapping("/getOne/{id}")
    public HttpEntity<?> readInputProductsById(@PathVariable Integer id) {
        InputProduct inputProduct = inputProductService.readInputProductById(id);
        return ResponseEntity.status(200).body(inputProduct);
    }

    // UPDATE inputProduct by id
    @PutMapping("/update/{id}")
    public HttpEntity<?> updateInputProductsById(@PathVariable Integer id, @Valid @RequestBody InputProductDTO inputProductDTO) {
        Result result = inputProductService.updateInputProduct(id, inputProductDTO);
        return ResponseEntity.status(result.isSuccess() ? 202 : 409).body(result);
    }

    // DELETE inputProduct by id
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteInputProductById(@PathVariable Integer id) {
        Result result = inputProductService.deleteInputProduct(id);
        return ResponseEntity.status(result.isSuccess() ? 202 : 409).body(result);
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
