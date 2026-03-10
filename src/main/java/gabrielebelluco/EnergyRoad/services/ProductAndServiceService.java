package gabrielebelluco.EnergyRoad.services;

import gabrielebelluco.EnergyRoad.entities.ProductAndService;
import gabrielebelluco.EnergyRoad.entities.ProductCategory;
import gabrielebelluco.EnergyRoad.exceptions.NotFoundException;
import gabrielebelluco.EnergyRoad.payloads.CreateProductAndServiceDTO;
import gabrielebelluco.EnergyRoad.payloads.ResponseProductAndServiceDTO;
import gabrielebelluco.EnergyRoad.repositories.ProductAndServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductAndServiceService {
    private final ProductAndServiceRepository productRepository;
    private final ProductCategoryService categoryService;

    public ProductAndServiceService(ProductAndServiceRepository productRepository, ProductCategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    public ResponseProductAndServiceDTO createProduct(CreateProductAndServiceDTO dto) {
        ProductCategory cat = categoryService.getById(dto.categoryId());
        ProductAndService p = new ProductAndService();
        p.setName(dto.name());
        p.setDescription(dto.description());
        p.setPrice(dto.price());
        p.setCategory(cat);
        ProductAndService saved = productRepository.save(p);
        return new ResponseProductAndServiceDTO(saved.getProductAndServiceId(), saved.getName(), saved.getDescription(), saved.getPrice(), saved.getCategory().getProductCategoryId());
    }

    public List<ProductAndService> getAllProducts() {
        return productRepository.findAll();
    }

    public ProductAndService getById(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Prodotto non trovato con id: " + id));
    }

    public ResponseProductAndServiceDTO updateProduct(UUID id, CreateProductAndServiceDTO dto) {
        ProductAndService p = getById(id);
        ProductCategory cat = categoryService.getById(dto.categoryId());
        p.setName(dto.name());
        p.setDescription(dto.description());
        p.setPrice(dto.price());
        p.setCategory(cat);
        ProductAndService saved = productRepository.save(p);
        return new ResponseProductAndServiceDTO(saved.getProductAndServiceId(), saved.getName(), saved.getDescription(), saved.getPrice(), saved.getCategory().getProductCategoryId());
    }

    public void deleteProduct(UUID id) {
        ProductAndService p = getById(id);
        productRepository.delete(p);
    }
}
