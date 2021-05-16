package uz.pdp.springbootapp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springbootapp.DTO.ProductDTO;
import uz.pdp.springbootapp.DTO.Result;
import uz.pdp.springbootapp.Entity.Attachment;
import uz.pdp.springbootapp.Entity.Category;
import uz.pdp.springbootapp.Entity.Measurement;
import uz.pdp.springbootapp.Entity.Product;
import uz.pdp.springbootapp.Repository.AttachmentRepository;
import uz.pdp.springbootapp.Repository.CategoryRepository;
import uz.pdp.springbootapp.Repository.MeasurementRepository;
import uz.pdp.springbootapp.Repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    MeasurementRepository measurementRepository;


    public Result addProduct(ProductDTO productDTO) {
        boolean existsByName = productRepository.existsByNameAndCategoryId(productDTO.getName(), productDTO.getCategoryId());
        if (existsByName) {
            return new Result("This product already exists", false);
        }

        // category
        Optional<Category> optionalCategory = categoryRepository.findById(productDTO.getCategoryId());
        if (optionalCategory.isEmpty()) {
            return new Result("Category with this id is not found", false);
        }

        // photo
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDTO.getPhotoId());
        if (optionalAttachment.isEmpty()) {
            return new Result("Photo with this id is not found", false);
        }

        // measurement
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDTO.getMeasurementId());
        if (optionalMeasurement.isEmpty()) {
            return new Result("Measurement with this id is not found", false);
        }

        // save the new product
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setCategory(optionalCategory.get());
        product.setAttachment(optionalAttachment.get());
        product.setMeasurement(optionalMeasurement.get());
        product.setCode(productDTO.getCode());

        productRepository.save(product);
        return new Result("Product added", true);
    }

    // READ all products
    public List<Product> readAllProducts() {
        return productRepository.findAll();
    }

    // READ products by id
    public Product readProductById(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElseGet(Product::new);
    }

    // UPDATE products by id
    public Result updateProductById(Integer id, ProductDTO productDTO) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product editedProduct = optionalProduct.get();
            boolean existsByName = productRepository.existsByNameAndCategoryId(productDTO.getName(), productDTO.getCategoryId());
            if (existsByName) {
                return new Result("This product already exists", false);
            }

            // category
            Optional<Category> optionalCategory = categoryRepository.findById(productDTO.getCategoryId());
            if (optionalCategory.isEmpty()) {
                return new Result("Category with this id is not found", false);
            }

            // photo
            Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDTO.getPhotoId());
            if (optionalAttachment.isEmpty()) {
                return new Result("Photo with this id is not found", false);
            }

            // measurement
            Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDTO.getMeasurementId());
            if (optionalMeasurement.isEmpty()) {
                return new Result("Measurement with this id is not found", false);
            }
            editedProduct.setName(productDTO.getName());
            editedProduct.setCategory(optionalCategory.get());
            editedProduct.setAttachment(optionalAttachment.get());
            editedProduct.setMeasurement(optionalMeasurement.get());
            editedProduct.setCode(productDTO.getCode());

            return new Result("Product updated", true);
        }
        return new Result("Product with this id is not found", false);

    }

    // DELETE products by id
    public Result deleteProduct(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            productRepository.deleteById(id);

            return new Result("Product deleted", true);
        }
        return new Result("Product with this id is not found", false);
    }
}
