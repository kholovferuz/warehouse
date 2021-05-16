package uz.pdp.springbootapp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springbootapp.DTO.CurrencyDTO;
import uz.pdp.springbootapp.DTO.Result;
import uz.pdp.springbootapp.Entity.Currency;
import uz.pdp.springbootapp.Entity.Measurement;
import uz.pdp.springbootapp.Repository.CurrencyRepository;
import uz.pdp.springbootapp.Repository.MeasurementRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {
    @Autowired
    CurrencyRepository currencyRepository;

    // ADD currency
    public Result addCurrency(CurrencyDTO currencyDTO) {
        boolean existsByName = currencyRepository.existsByName(currencyDTO.getName());
        if (existsByName) {
            return new Result("This currency already exists", false);
        }
        Currency currency = new Currency();
        currency.setName(currencyDTO.getName());

        currencyRepository.save(currency);
        return new Result("Currency added", true);
    }

    // READ all currencies
    public List<Currency> readALlCurrencies() {
        return currencyRepository.findAll();
    }

    // READ currency by id
    public Currency readCurrencyById(Integer id) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        return optionalCurrency.orElseGet(Currency::new);
    }

    // UPDATE currency by id
    public Result updateCurrency(CurrencyDTO currencyDTO, Integer id) {
        Optional<Currency> currencyOptional = currencyRepository.findById(id);
        if (currencyOptional.isPresent()) {
            boolean existsByName = currencyRepository.existsByName(currencyDTO.getName());
            if (existsByName) {
                return new Result("This currency already exists", false);
            }

            Currency editedCurrency = currencyOptional.get();
            editedCurrency.setName(currencyDTO.getName());

            currencyRepository.save(editedCurrency);
            return new Result("Currency updated", true);
        }
        return new Result("Currency with this id is not found", false);
    }

    // DELETE currency by id
    public Result deleteCurrency(Integer id) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (optionalCurrency.isPresent()) {
            currencyRepository.deleteById(id);
            return new Result("Currency deleted", true);
        }
        return new Result("Currency with this id is not found", false);

    }
}
