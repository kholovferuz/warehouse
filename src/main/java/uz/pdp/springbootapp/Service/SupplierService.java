package uz.pdp.springbootapp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springbootapp.DTO.Result;
import uz.pdp.springbootapp.DTO.SupplierDTO;
import uz.pdp.springbootapp.Entity.Supplier;
import uz.pdp.springbootapp.Repository.SupplierRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    @Autowired
    SupplierRepository supplierRepository;

    // ADD supplier
    public Result addSupplier(SupplierDTO supplierDTO) {
        boolean existsByName = supplierRepository.existsByName(supplierDTO.getName());
        if (existsByName) {
            return new Result("This supplier already exists", false);
        }

        boolean exists = supplierRepository.existsByPhoneNumber(supplierDTO.getPhoneNumber());
        if (exists) {
            return new Result("This phone number exists", false);
        }
        Supplier supplier = new Supplier();
        supplier.setName(supplierDTO.getName());
        supplier.setPhoneNumber(supplierDTO.getPhoneNumber());
        supplierRepository.save(supplier);
        return new Result("Supplier added", true);
    }

    // READ all suppliers
    public List<Supplier> readALlSuppliers() {
        return supplierRepository.findAll();
    }

    // READ suppliers by id
    public Supplier readSupplierById(Integer id) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        return optionalSupplier.orElse(null);
    }

    // UPDATE supplier by id
    public Result updateSupplier(SupplierDTO supplierDTO, Integer id) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (optionalSupplier.isPresent()) {
            boolean existsByName = supplierRepository.existsByName(supplierDTO.getName());
            if (existsByName) {
                return new Result("This supplier already exists", false);
            }

            Supplier editedSupplier = optionalSupplier.get();
            editedSupplier.setName(supplierDTO.getName());
            editedSupplier.setPhoneNumber(supplierDTO.getPhoneNumber());

            supplierRepository.save(editedSupplier);
            return new Result("Supplier updated", true);
        }
        return new Result("Supplier with this id is not found", false);
    }

    // DELETE supplier by id
    public Result deleteSupplier(Integer id) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (optionalSupplier.isPresent()) {
            supplierRepository.deleteById(id);
            return new Result("Supplier deleted", true);
        }
        return new Result("Supplier with this id is not found", false);

    }
}
