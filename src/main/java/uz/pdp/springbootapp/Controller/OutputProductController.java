package uz.pdp.springbootapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootapp.DTO.InputProductDTO;
import uz.pdp.springbootapp.DTO.OutputProductDTO;
import uz.pdp.springbootapp.DTO.Result;
import uz.pdp.springbootapp.Entity.InputProduct;
import uz.pdp.springbootapp.Entity.OutputProduct;
import uz.pdp.springbootapp.Service.InputProductService;
import uz.pdp.springbootapp.Service.OutputProductService;
import uz.pdp.springbootapp.Service.OutputService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/outputProduct")
public class OutputProductController {
    @Autowired
    OutputProductService outputProductService;

    // CREATE new outputProduct
    @PostMapping("/addNew")
    public HttpEntity<?> addOutputProduct(@Valid @RequestBody OutputProductDTO outputProductDTO) {
        Result result = outputProductService.addOutputProduct(outputProductDTO);
        return ResponseEntity.status(result.isSuccess() ? 201 : 409).body(result);
    }

    // READ all outputProducts
    @GetMapping("/getAll")
    public HttpEntity<?> readAllOutputProduct() {
        List<OutputProduct> outputProducts = outputProductService.readAllOutputProducts();
        return ResponseEntity.status(200).body(outputProducts);
    }

    // READ outputProduct by id
    @GetMapping("/getOne/{id}")
    public HttpEntity<?> readOutputProductsById(@PathVariable Integer id) {
        OutputProduct outputProduct = outputProductService.readOutputProductById(id);
        return ResponseEntity.status(200).body(outputProduct);
    }

    // UPDATE outputProduct by id
    @PutMapping("/update/{id}")
    public HttpEntity<?> updateOutputProductsById(@PathVariable Integer id, @Valid @RequestBody OutputProductDTO outputProductDTO) {
        Result updateOutputProduct = outputProductService.updateOutputProduct(id, outputProductDTO);
        return ResponseEntity.status(updateOutputProduct.isSuccess() ? 202 : 409).body(updateOutputProduct);
    }

    // DELETE outputProduct by id
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteOutputById(@PathVariable Integer id) {
        Result deleteOutputProduct = outputProductService.deleteOutputProduct(id);
        return ResponseEntity.status(deleteOutputProduct.isSuccess() ? 202 : 409).body(deleteOutputProduct);

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
