package uz.pdp.springbootapp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springbootapp.DTO.InputDTO;
import uz.pdp.springbootapp.DTO.Result;
import uz.pdp.springbootapp.Entity.*;
import uz.pdp.springbootapp.Repository.CurrencyRepository;
import uz.pdp.springbootapp.Repository.InputRepository;
import uz.pdp.springbootapp.Repository.SupplierRepository;
import uz.pdp.springbootapp.Repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InputService {
    @Autowired
    InputRepository inputRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    CurrencyRepository currencyRepository;

    // CREATE input
    public Result addInput(InputDTO inputDTO) {
        boolean existsByInvoiceNumber = inputRepository.existsByInvoiceNumber(inputDTO.getInvoiceNumber());
        if (existsByInvoiceNumber) {
            return new Result("This input already exists", false);
        }

        // warehouse
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDTO.getWarehouseId());
        if (optionalWarehouse.isEmpty()) {
            return new Result("Warehouse with this id is not found", false);
        }

        // supplier
        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDTO.getSupplierId());
        if (optionalSupplier.isEmpty()) {
            return new Result("Supplier with this id is not found", false);
        }

        // currency
        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDTO.getCurrencyId());
        if (optionalCurrency.isEmpty()) {
            return new Result("Currency with this id is not found", false);
        }

        Input input = new Input();
        input.setDate(inputDTO.getDate());
        input.setInvoiceNumber(inputDTO.getInvoiceNumber());
        input.setCode(inputDTO.getCode());
        input.setWarehouse(optionalWarehouse.get());
        input.setSupplier(optionalSupplier.get());
        input.setCurrency(optionalCurrency.get());

        inputRepository.save(input);
        return new Result("Input added", true);
    }

    // READ all inputs
    public List<Input> readAllInputs() {
        return inputRepository.findAll();
    }

    // READ input by id
    public Input getInputById(Integer id) {
        Optional<Input> optionalInput = inputRepository.findById(id);
        return optionalInput.orElseGet(Input::new);
    }

    // UPDATE input by id
    public Result updateInput(Integer id, InputDTO inputDTO) {
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (optionalInput.isPresent()) {
            boolean existsByInvoiceNumber = inputRepository.existsByInvoiceNumber(inputDTO.getInvoiceNumber());
            if (existsByInvoiceNumber) {
                return new Result("This input already exists", false);
            }

            // warehouse
            Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDTO.getWarehouseId());
            if (optionalWarehouse.isEmpty()) {
                return new Result("Warehouse with this id is not found", false);
            }

            // supplier
            Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDTO.getSupplierId());
            if (optionalSupplier.isEmpty()) {
                return new Result("Supplier with this id is not found", false);
            }

            // currency
            Optional<Currency> optionalCurrency = currencyRepository.findById(inputDTO.getCurrencyId());
            if (optionalCurrency.isEmpty()) {
                return new Result("Currency with this id is not found", false);
            }

            Input editedInput = optionalInput.get();
            editedInput.setDate(inputDTO.getDate());
            editedInput.setInvoiceNumber(inputDTO.getInvoiceNumber());
            editedInput.setCode(inputDTO.getCode());
            editedInput.setWarehouse(optionalWarehouse.get());
            editedInput.setSupplier(optionalSupplier.get());
            editedInput.setCurrency(optionalCurrency.get());

            inputRepository.save(editedInput);
            return new Result("Input updated", true);
        }
        return new Result("Input with this id is not found", false);
    }

    // DELETE input by id
    public Result deleteInput(Integer id) {
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (optionalInput.isPresent()) {
            inputRepository.deleteById(id);
            return new Result("Input deleted", true);
        }
        return new Result("Input with this id is not found", false);
    }
}
