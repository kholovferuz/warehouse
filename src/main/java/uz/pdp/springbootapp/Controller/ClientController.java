package uz.pdp.springbootapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootapp.DTO.ClientDTO;
import uz.pdp.springbootapp.DTO.Result;
import uz.pdp.springbootapp.Entity.Client;
import uz.pdp.springbootapp.Entity.Supplier;
import uz.pdp.springbootapp.Repository.ClientRepository;
import uz.pdp.springbootapp.Service.ClientService;
import uz.pdp.springbootapp.Service.SupplierService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    ClientService clientService;

    // CREATE new client
    @PostMapping
    public Result addClients(@RequestBody ClientDTO clientDTO) {
        return clientService.addClient(clientDTO);
    }

    // READ all clients
    @GetMapping
    public List<Client> readAllClient() {
        return clientService.readALlClients();
    }

    // READ Client by id
    @GetMapping("/{id}")
    public Client readClientsById(@PathVariable Integer id) {
        return clientService.readClientById(id);
    }

    // UPDATE Client by id
    @PutMapping("/{id}")
    public Result updateClientById(@PathVariable Integer id, @RequestBody ClientDTO clientDTO) {
        return clientService.updateClient(clientDTO, id);
    }

    // DELETE Client by id
    @DeleteMapping("/{id}")
    public Result deleteClientById(@PathVariable Integer id) {
        return clientService.deleteClient(id);

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
