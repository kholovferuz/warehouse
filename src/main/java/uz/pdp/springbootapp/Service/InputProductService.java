package uz.pdp.springbootapp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springbootapp.DTO.InputProductDTO;
import uz.pdp.springbootapp.DTO.Result;
import uz.pdp.springbootapp.Entity.*;
import uz.pdp.springbootapp.Repository.InputProductRepository;
import uz.pdp.springbootapp.Repository.InputRepository;
import uz.pdp.springbootapp.Repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InputProductService {
    @Autowired
    InputProductRepository inputProductRepository;
    @Autowired
    InputRepository inputRepository;
    @Autowired
    ProductRepository productRepository;

    // CREATE new inputProduct
    public Result addInputProduct(InputProductDTO inputProductDTO) {
        // input
        Optional<Input> optionalInput = inputRepository.findById(inputProductDTO.getInputId());
        if (optionalInput.isEmpty()) {
            return new Result("Input with this id is not found", false);
        }
        
        // product
        Optional<Product> optionalProduct = productRepository.findById(inputProductDTO.getProductId());
        if (optionalProduct.isEmpty()) {
            return new Result("Product with this id is not found", false);
        }

        // save the new inputProduct
        InputProduct inputProduct = new InputProduct();
        inputProduct.setAmount(inputProductDTO.getAmount());
        inputProduct.setPrice(inputProductDTO.getPrice());
        inputProduct.setExpiryDate(inputProductDTO.getExpiryDate());
        inputProduct.setInput(optionalInput.get());
        inputProduct.setProduct(optionalProduct.get());

        int count = 0;
        for (InputProduct product : inputProductRepository.findAll()) {
            Double amount = product.getAmount();
            count += amount;
        }
        System.out.println(count);
        inputProductRepository.save(inputProduct);
        return new Result("InputProduct added", true);
    }

    // READ all inputProducts
    public List<InputProduct> readAllInputProducts() {
        return inputProductRepository.findAll();
    }

    // READ inputProducts by id
    public InputProduct readInputProductById(Integer id) {
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        return optionalInputProduct.orElseGet(InputProduct::new);
    }

    // UPDATE inputProduct by id
    public Result updateInputProduct(Integer id, InputProductDTO inputProductDTO) {
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (optionalInputProduct.isPresent()) {
            // input
            Optional<Input> optionalInput = inputRepository.findById(inputProductDTO.getInputId());
            if (optionalInput.isEmpty()) {
                return new Result("Input with this id is not found", false);
            }

            // product
            Optional<Product> optionalProduct = productRepository.findById(inputProductDTO.getProductId());
            if (optionalProduct.isEmpty()) {
                return new Result("Product with this id is not found", false);
            }

            // save the updated inputProduct
            InputProduct updatedInputProduct = new InputProduct();
            updatedInputProduct.setAmount(inputProductDTO.getAmount());
            updatedInputProduct.setPrice(inputProductDTO.getPrice());
            updatedInputProduct.setExpiryDate(inputProductDTO.getExpiryDate());
            updatedInputProduct.setInput(optionalInput.get());
            updatedInputProduct.setProduct(optionalProduct.get());


            inputProductRepository.save(updatedInputProduct);
            return new Result("InputProduct updated", true);
        }
        return new Result("InputProduct with this id is not found", false);
    }

    // DELETE inputProduct by id
    public Result deleteInputProduct(Integer id) {
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (optionalInputProduct.isPresent()) {
            inputProductRepository.deleteById(id);

            return new Result("InputProduct deleted", true);
        }
        return new Result("InputProduct with this id is not found", false);
    }
}
