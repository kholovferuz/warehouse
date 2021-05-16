package uz.pdp.springbootapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootapp.DTO.ProductDTO;
import uz.pdp.springbootapp.DTO.Result;
import uz.pdp.springbootapp.Entity.Product;
import uz.pdp.springbootapp.Entity.Warehouse;
import uz.pdp.springbootapp.Service.ProductService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    // CREATE new product
    @PostMapping("/addNew")
    public HttpEntity<?> addProduct(@Valid @RequestBody ProductDTO productDTO) {
        Result result = productService.addProduct(productDTO);
        return ResponseEntity.status(result.isSuccess() ? 201 : 409).body(result);
    }

    // READ all products
    @GetMapping("/getOne")
    public HttpEntity<?> readAllProduct() {
        List<Product> products = productService.readAllProducts();
        return ResponseEntity.status(200).body(products);
    }

    // READ product by id
    @GetMapping("/getOne/{id}")
    public HttpEntity<?> readProductsById(@PathVariable Integer id) {
        Product product = productService.readProductById(id);
        return ResponseEntity.status(200).body(product);
    }

    // UPDATE product by id
    @PutMapping("/update/{id}")
    public HttpEntity<?> updateProductsById(@PathVariable Integer id, @Valid @RequestBody ProductDTO productDTO) {
        Result updateProduct = productService.updateProductById(id, productDTO);
        return ResponseEntity.status(updateProduct.isSuccess() ? 202 : 409).body(updateProduct);
    }

    // DELETE product by id
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteProductById(@PathVariable Integer id) {
        Result deleteProduct = productService.deleteProduct(id);
        return ResponseEntity.status(deleteProduct.isSuccess() ? 202 : 409).body(deleteProduct);

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
