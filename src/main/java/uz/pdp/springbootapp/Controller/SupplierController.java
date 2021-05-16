package uz.pdp.springbootapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import uz.pdp.springbootapp.DTO.Result;
import uz.pdp.springbootapp.DTO.SupplierDTO;
import uz.pdp.springbootapp.Entity.Supplier;
import uz.pdp.springbootapp.Service.SupplierService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    SupplierService supplierService;

    // CREATE new supplier
    @PostMapping("/addNew")
    public HttpEntity<?> addSuppliers(@Valid @RequestBody SupplierDTO supplierDTO) {
        Result result = supplierService.addSupplier(supplierDTO);
        return ResponseEntity.status(result.isSuccess() ? 201 : 409).body(result);
    }

    // READ all suppliers
    @GetMapping("/getAll")
    public HttpEntity<?> readAllSupplier() {
        List<Supplier> suppliers = supplierService.readALlSuppliers();
        return ResponseEntity.status(200).body(suppliers);
    }

    // READ supplier by id
    @GetMapping("/getOne/{id}")
    public HttpEntity<?> readSupplierById(@PathVariable Integer id) {
        Supplier supplier = supplierService.readSupplierById(id);
        return ResponseEntity.status(200).body(supplier);
    }

    // UPDATE supplier by id
    @PutMapping("/update/{id}")
    public HttpEntity<?> updateSupplierById(@PathVariable Integer id, @Valid @RequestBody SupplierDTO supplierDTO) {
        Result updateSupplier = supplierService.updateSupplier(supplierDTO, id);
        return ResponseEntity.status(updateSupplier.isSuccess() ? 202 : 409).body(updateSupplier);
    }

    // DELETE supplier by id
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteSupplierById(@PathVariable Integer id) {
        Result deleteSupplier = supplierService.deleteSupplier(id);
        return ResponseEntity.status(deleteSupplier.isSuccess() ? 202 : 409).body(deleteSupplier);

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
