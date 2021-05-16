package uz.pdp.springbootapp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springbootapp.DTO.MeasurementDTO;
import uz.pdp.springbootapp.DTO.Result;
import uz.pdp.springbootapp.Entity.Measurement;
import uz.pdp.springbootapp.Repository.MeasurementRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MeasurementService {
    @Autowired
    MeasurementRepository measurementRepository;

    // ADD measurement
    public Result addMeasurementService(MeasurementDTO measurementDTO) {
        boolean existsByName = measurementRepository.existsByName(measurementDTO.getName());
        if (existsByName) {
            return new Result("This measurement already exists", false);
        }
        Measurement measurement = new Measurement();
        measurement.setName(measurementDTO.getName());
        measurementRepository.save(measurement);
        return new Result("Measurement added", true);
    }

    // READ all measurements
    public List<Measurement> readALlMeasurements() {
        return measurementRepository.findAll();
    }

    // READ measurements by id
    public Measurement readMeasurementById(Integer id) {
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        return optionalMeasurement.orElseGet(Measurement::new);
    }

    // UPDATE measurement by id
    public Result updateMeasurement(MeasurementDTO measurementDTO, Integer id) {
        Optional<Measurement> measurementOptional = measurementRepository.findById(id);
        if (measurementOptional.isPresent()) {
            boolean existsByName = measurementRepository.existsByName(measurementDTO.getName());
            if (existsByName) {
                return new Result("This measurement already exists", false);
            }

            Measurement editedMeasurement = measurementOptional.get();
            editedMeasurement.setName(measurementDTO.getName());

            measurementRepository.save(editedMeasurement);
            return new Result("Mesurement updated", true);
        }
        return new Result("Measurement with this id is not found", false);
    }

    // DELETE measurement by id
    public Result deleteMeasurement(Integer id) {
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (optionalMeasurement.isPresent()) {
            measurementRepository.deleteById(id);
            return new Result("Measurement deleted", true);
        }
        return new Result("Measurement with this id is not found", false);

    }
}
