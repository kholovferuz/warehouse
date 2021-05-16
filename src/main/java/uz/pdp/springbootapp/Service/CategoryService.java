package uz.pdp.springbootapp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springbootapp.DTO.CategoryDTO;
import uz.pdp.springbootapp.DTO.Result;
import uz.pdp.springbootapp.Entity.Category;
import uz.pdp.springbootapp.Repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    // CREATE new warehouse
    public Result addCategory(CategoryDTO categoryDTO) {
        boolean existsByName = categoryRepository.existsByName(categoryDTO.getName());
        if (existsByName) {
            return new Result("This category already exists", false);
        }
        Category category = new Category();
        category.setName(categoryDTO.getName());

        // parentCategory
        if (categoryDTO.getParentCategoryId() != null) {
            Optional<Category> optionalParentCategory = categoryRepository.findById(categoryDTO.getParentCategoryId());
            if (optionalParentCategory.isPresent()) {
                category.setParentCategory(optionalParentCategory.get());
            } else {
                return new Result("ParentCategory with this id is not found", false);
            }
        }

        categoryRepository.save(category);
        return new Result("New category added", true);
    }

    // READ all categories
    public List<Category> readALlCategories() {
        return categoryRepository.findAll();
    }

    // READ category by id
    public Category readCategoryById(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.orElseGet(Category::new);
    }

    // UPDATE  category by id
    public Result updateCategory(Integer id, CategoryDTO categoryDTO) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();

            // category
            category.setName(categoryDTO.getName());


            // parent category
            if (categoryDTO.getParentCategoryId() != null) {
                Optional<Category> optionalParentCategory = categoryRepository.findById(categoryDTO.getParentCategoryId());
                if (optionalParentCategory.isPresent()) {
                    category.setParentCategory(optionalParentCategory.get());
                } else {
                    return new Result("ParentCategory with this id is not found", false);
                }
            }

            categoryRepository.save(category);
            return new Result("Category updated", true);

        }
        return new Result("Category with this id is not found", false);
    }

    // DELETE category by id
    public Result deleteCategory(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            categoryRepository.deleteById(id);
            return new Result("Category deleted", true);
        }
        return new Result("Category with this id is not found", false);
    }
}
