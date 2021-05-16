package uz.pdp.springbootapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootapp.DTO.CurrencyDTO;
import uz.pdp.springbootapp.DTO.Result;
import uz.pdp.springbootapp.Entity.Currency;
import uz.pdp.springbootapp.Entity.Measurement;
import uz.pdp.springbootapp.Service.CurrencyService;
import uz.pdp.springbootapp.Service.MeasurementService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/currency")
public class CurrencyController {
    @Autowired
    CurrencyService currencyService;

    // CREATE new currency
    @PostMapping("/addNew")
    public HttpEntity<?> addNewCurrency(@Valid @RequestBody CurrencyDTO currencyDTO) {
        Result result = currencyService.addCurrency(currencyDTO);
        return ResponseEntity.status(result.isSuccess() ? 201 : 409).body(result);
    }

    // READ all currency
    @GetMapping("/getAll")
    public HttpEntity<?> readAllCurrency() {
        List<Currency> currencies = currencyService.readALlCurrencies();
        return ResponseEntity.status(200).body(currencies);
    }

    // READ currencies by id
    @GetMapping("/getOne/{id}")
    public HttpEntity<?> getCurrencyById(@PathVariable Integer id) {
        Currency currency = currencyService.readCurrencyById(id);
        return ResponseEntity.status(200).body(currency);
    }

    // UPDATE currency by id
    @PutMapping("/update{id}")
    public HttpEntity<?> updateCurrencies(@PathVariable Integer id, @Valid @RequestBody CurrencyDTO currencyDTO) {
        Result result = currencyService.updateCurrency(currencyDTO, id);
        return ResponseEntity.status(result.isSuccess() ? 202 : 409).body(result);
    }

    // DELETE currency by id
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteCurrencies(@PathVariable Integer id) {
        Result deleteCurrency = currencyService.deleteCurrency(id);
        return ResponseEntity.status(deleteCurrency.isSuccess() ? 202 : 409).body(deleteCurrency);

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
