package uz.pdp.springbootapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootapp.DTO.MeasurementDTO;
import uz.pdp.springbootapp.DTO.Result;
import uz.pdp.springbootapp.Entity.Measurement;
import uz.pdp.springbootapp.Service.MeasurementService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {
    @Autowired
    MeasurementService measurementService;

    // CREATE new measurement
    @PostMapping("/addNew")
    public HttpEntity<?> addMeasurementController(@Valid @RequestBody MeasurementDTO measurementDTO) {
        Result result = measurementService.addMeasurementService(measurementDTO);
        return ResponseEntity.status(result.isSuccess() ? 201 : 409).body(result);

    }

    // READ all measurements
    @GetMapping("/getAll")
    public HttpEntity<?> readAllMeasurement() {
        List<Measurement> measurements = measurementService.readALlMeasurements();
        return ResponseEntity.status(200).body(measurements);
    }

    // READ measurements by id
    @GetMapping("/getOne/{id}")
    public HttpEntity<?> getMeasurementById(@PathVariable Integer id) {
        Measurement measurement = measurementService.readMeasurementById(id);
        return ResponseEntity.status(200).body(measurement);
    }

    // UPDATE measurement by id
    @PutMapping("/update/{id}")
    public HttpEntity<?> updateMeasurement(@PathVariable Integer id, @Valid @RequestBody MeasurementDTO measurementDTO) {
        Result result = measurementService.updateMeasurement(measurementDTO, id);
        return ResponseEntity.status(result.isSuccess() ? 202 : 409).body(result);
    }

    // DELETE measurement by id
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteMeasurementById(@PathVariable Integer id) {
        Result result = measurementService.deleteMeasurement(id);
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
