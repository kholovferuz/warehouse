package uz.pdp.springbootapp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springbootapp.DTO.OutputProductDTO;
import uz.pdp.springbootapp.DTO.Result;
import uz.pdp.springbootapp.Entity.*;
import uz.pdp.springbootapp.Repository.*;

import java.util.List;
import java.util.Optional;

@Service
public class OutputProductService {
    @Autowired
    OutputProductRepository outputProductRepository;
    @Autowired
    OutputRepository outputRepository;
    @Autowired
    ProductRepository productRepository;

    // CREATE new outputProduct
    public Result addOutputProduct(OutputProductDTO outputProductDTO) {
        // output
        Optional<Output> optionalOutput = outputRepository.findById(outputProductDTO.getOutputId());
        if (optionalOutput.isEmpty()) {
            return new Result("Output with this id is not found", false);
        }

        // product
        Optional<Product> optionalProduct = productRepository.findById(outputProductDTO.getProductId());
        if (optionalProduct.isEmpty()) {
            return new Result("Product with this id is not found", false);
        }

        // save the new inputProduct
        OutputProduct outputProduct = new OutputProduct();
        outputProduct.setAmount(outputProductDTO.getAmount());
        outputProduct.setPrice(outputProductDTO.getPrice());
        outputProduct.setOutput(optionalOutput.get());
        outputProduct.setProduct(optionalProduct.get());


        outputProductRepository.save(outputProduct);
        return new Result("OutputProduct added", true);
    }

    // READ all outputProducts
    public List<OutputProduct> readAllOutputProducts() {
        return outputProductRepository.findAll();
    }

    // READ outputProducts by id
    public OutputProduct readOutputProductById(Integer id) {
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        return optionalOutputProduct.orElseGet(OutputProduct::new);
    }

    // UPDATE outputProducts by id
    public Result updateOutputProduct(Integer id, OutputProductDTO outputProductDTO){
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if (optionalOutputProduct.isPresent()){
            // output
            Optional<Output> optionalOutput = outputRepository.findById(outputProductDTO.getOutputId());
            if (optionalOutput.isEmpty()) {
                return new Result("Output with this id is not found", false);
            }

            // product
            Optional<Product> optionalProduct = productRepository.findById(outputProductDTO.getProductId());
            if (optionalProduct.isEmpty()) {
                return new Result("Product with this id is not found", false);
            }

            // save the new inputProduct
            OutputProduct outputProduct = new OutputProduct();
            outputProduct.setAmount(outputProductDTO.getAmount());
            outputProduct.setPrice(outputProductDTO.getPrice());
            outputProduct.setOutput(optionalOutput.get());
            outputProduct.setProduct(optionalProduct.get());


            outputProductRepository.save(outputProduct);
            return new Result("OutputProduct updated", true);
        }
        return new Result("OutputProduct with this id is not found",false);
    }

    // DELETE outputProduct by id
    public Result deleteOutputProduct(Integer id) {
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if (optionalOutputProduct.isPresent()) {
            outputProductRepository.deleteById(id);

            return new Result("OutputProduct deleted", true);
        }
        return new Result("OutputProduct with this id is not found", false);
    }
}
