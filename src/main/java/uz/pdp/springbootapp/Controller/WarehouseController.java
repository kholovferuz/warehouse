package uz.pdp.springbootapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootapp.DTO.Result;
import uz.pdp.springbootapp.DTO.WarehouseDTO;
import uz.pdp.springbootapp.Entity.Warehouse;
import uz.pdp.springbootapp.Service.WarehouseService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
    @Autowired
    WarehouseService warehouseService;

    // CREATE new warehouse
    @PostMapping("/addNew")
    public HttpEntity<?> addNewWarehouse(@Valid @RequestBody WarehouseDTO warehouseDTO) {
        Result result = warehouseService.addWarehouse(warehouseDTO);
        return ResponseEntity.status(result.isSuccess() ? 201 : 409).body(result);
    }

    // READ all warehouses
    @GetMapping("/getAll")
    public HttpEntity<?> getAllWarehouses() {
        List<Warehouse> warehouses = warehouseService.readALlWarehouses();
        return ResponseEntity.status(200).body(warehouses);
    }

    // READ warehouse by id
    @GetMapping("/getOne/{id}")
    public HttpEntity<?> getWarehouseById(@PathVariable Integer id) {
        Warehouse warehouseById = warehouseService.readWarehouseById(id);
        return ResponseEntity.status(200).body(warehouseById);
    }

    // UPDATE warehouse by id
    @PutMapping("/update/{id}")
    public HttpEntity<?> updateWarehouseById(@PathVariable Integer id, @Valid @RequestBody WarehouseDTO warehouseDTO) {
        Result updateWarehouse = warehouseService.updateWarehouse(warehouseDTO, id);
        return ResponseEntity.status(updateWarehouse.isSuccess() ? 202 : 409).body(updateWarehouse);
    }

    // DELETE warehouse by id
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteWarehouseById(@PathVariable Integer id) {
        Result deleteWarehouse = warehouseService.deleteWarehouse(id);
        return ResponseEntity.status(deleteWarehouse.isSuccess() ? 202 : 409).body(deleteWarehouse);

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
