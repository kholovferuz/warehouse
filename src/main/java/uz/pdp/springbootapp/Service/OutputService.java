package uz.pdp.springbootapp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springbootapp.DTO.InputDTO;
import uz.pdp.springbootapp.DTO.OutputDTO;
import uz.pdp.springbootapp.DTO.Result;
import uz.pdp.springbootapp.Entity.*;
import uz.pdp.springbootapp.Repository.*;

import java.util.List;
import java.util.Optional;

@Service
public class OutputService {
    @Autowired
    OutputRepository outputRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    CurrencyRepository currencyRepository;

    // CREATE output
    public Result addOutput(OutputDTO outputDTO) {
        boolean existsByInvoiceNumber = outputRepository.existsByInvoiceNumber(outputDTO.getInvoiceNumber());
        if (existsByInvoiceNumber) {
            return new Result("This output already exists", false);
        }

        // warehouse
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDTO.getWarehouseId());
        if (optionalWarehouse.isEmpty()) {
            return new Result("Warehouse with this id is not found", false);
        }

        // client
        Optional<Client> optionalClient = clientRepository.findById(outputDTO.getClientId());
        if (optionalClient.isEmpty()) {
            return new Result("Client with this id is not found", false);
        }

        // currency
        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDTO.getCurrencyId());
        if (optionalCurrency.isEmpty()) {
            return new Result("Currency with this id is not found", false);
        }

        Output output = new Output();
        output.setDate(outputDTO.getDate());
        output.setInvoiceNumber(outputDTO.getInvoiceNumber());
        output.setCode(outputDTO.getCode());
        output.setWarehouse(optionalWarehouse.get());
        output.setClient(optionalClient.get());
        output.setCurrency(optionalCurrency.get());

        outputRepository.save(output);
        return new Result("Output added", true);
    }

    // READ all Output
    public List<Output> readAllOutputs() {
        return outputRepository.findAll();
    }

    // READ Output by id
    public Output getOutputById(Integer id) {
        Optional<Output> optionalOutput = outputRepository.findById(id);
        return optionalOutput.orElseGet(Output::new);
    }

    // UPDATE output by id
    public Result updateOutput(Integer id, OutputDTO outputDTO) {
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (optionalOutput.isPresent()) {
            boolean existsByInvoiceNumber = outputRepository.existsByInvoiceNumber(outputDTO.getInvoiceNumber());
            if (existsByInvoiceNumber) {
                return new Result("This output already exists", false);
            }

            // warehouse
            Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDTO.getWarehouseId());
            if (optionalWarehouse.isEmpty()) {
                return new Result("Warehouse with this id is not found", false);
            }

            // client
            Optional<Client> optionalClient = clientRepository.findById(outputDTO.getClientId());
            if (optionalClient.isEmpty()) {
                return new Result("Supplier with this id is not found", false);
            }

            // currency
            Optional<Currency> optionalCurrency = currencyRepository.findById(outputDTO.getCurrencyId());
            if (optionalCurrency.isEmpty()) {
                return new Result("Currency with this id is not found", false);
            }

            Output editedOutput = optionalOutput.get();
            editedOutput.setDate(outputDTO.getDate());
            editedOutput.setInvoiceNumber(outputDTO.getInvoiceNumber());
            editedOutput.setCode(outputDTO.getCode());
            editedOutput.setWarehouse(optionalWarehouse.get());
            editedOutput.setClient(optionalClient.get());
            editedOutput.setCurrency(optionalCurrency.get());

            outputRepository.save(editedOutput);
            return new Result("Output updated", true);
        }
        return new Result("Output with this id is not found", false);
    }

    // DELETE Output by id
    public Result deleteOutput(Integer id) {
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (optionalOutput.isPresent()) {
            outputRepository.deleteById(id);
            return new Result("Output deleted", true);
        }
        return new Result("Output with this id is not found", false);
    }
}
