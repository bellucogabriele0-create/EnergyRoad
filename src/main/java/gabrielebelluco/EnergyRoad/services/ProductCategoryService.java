package gabrielebelluco.EnergyRoad.services;

import gabrielebelluco.EnergyRoad.entities.ProductCategory;
import gabrielebelluco.EnergyRoad.exceptions.NotFoundException;
import gabrielebelluco.EnergyRoad.payloads.request.CreateProductCategoryDTO;
import gabrielebelluco.EnergyRoad.repositories.ProductCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductCategoryService {
    private final ProductCategoryRepository categoryRepository;

    public ProductCategoryService(ProductCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public ProductCategory createCategory(CreateProductCategoryDTO dto) {
        ProductCategory category = new ProductCategory();
        category.setName(dto.name());
        return categoryRepository.save(category);
    }

    public List<ProductCategory> getAllCategories() {
        return categoryRepository.findAll();
    }

    public ProductCategory getById(UUID id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Categoria non trovata con id: " + id));
    }

    public ProductCategory updateCategory(UUID id, CreateProductCategoryDTO dto) {
        ProductCategory category = getById(id);
        category.setName(dto.name());
        return categoryRepository.save(category);
    }

    public void deleteCategory(UUID id) {
        ProductCategory category = getById(id);
        categoryRepository.delete(category);
    }
}
