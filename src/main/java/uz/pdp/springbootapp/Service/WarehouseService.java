package uz.pdp.springbootapp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springbootapp.DTO.Result;
import uz.pdp.springbootapp.DTO.WarehouseDTO;
import uz.pdp.springbootapp.Entity.Warehouse;
import uz.pdp.springbootapp.Repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {
    @Autowired
    WarehouseRepository warehouseRepository;

    // ADD warehouse
    public Result addWarehouse(WarehouseDTO warehouseDTO) {
        boolean existsByName = warehouseRepository.existsByName(warehouseDTO.getName());
        if (existsByName) {
            return new Result("This warehouse already exists", false);
        }
        Warehouse warehouse=new Warehouse();
        warehouse.setName(warehouseDTO.getName());
        warehouseRepository.save(warehouse);
        return new Result("Warehouse added", true);
    }

    // READ all warehouse
    public List<Warehouse> readALlWarehouses() {
        return warehouseRepository.findAll();
    }

    // READ warehouse by id
    public Warehouse readWarehouseById(Integer id) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        return optionalWarehouse.orElseGet(Warehouse::new);
    }

    // UPDATE warehouse by id
    public Result updateWarehouse(WarehouseDTO warehouseDTO, Integer id) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (optionalWarehouse.isPresent()) {
            boolean existsByName = warehouseRepository.existsByName(warehouseDTO.getName());
            if (existsByName) {
                return new Result("This warehouse already exists", false);
            }

            Warehouse editedWarehouse = optionalWarehouse.get();
            editedWarehouse.setName(warehouseDTO.getName());

            warehouseRepository.save(editedWarehouse);
            return new Result("Warehouse updated", true);
        }
        return new Result("Warehouse with this id is not found", false);
    }

    // DELETE warehouse by id
    public Result deleteWarehouse(Integer id){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (optionalWarehouse.isPresent()){
            warehouseRepository.deleteById(id);
            return new Result("Warehouse deleted",true);
        }
        return new Result("Warehouse with this id is not found",false);

    }


}
