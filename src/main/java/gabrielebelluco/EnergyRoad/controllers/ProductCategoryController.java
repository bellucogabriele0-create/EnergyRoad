package gabrielebelluco.EnergyRoad.controllers;

import gabrielebelluco.EnergyRoad.entities.ProductCategory;
import gabrielebelluco.EnergyRoad.payloads.request.CreateProductCategoryDTO;
import gabrielebelluco.EnergyRoad.payloads.response.ResponseProductCategoryDTO;
import gabrielebelluco.EnergyRoad.services.ProductCategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/categories")
public class ProductCategoryController {
    private final ProductCategoryService categoryService;

    public ProductCategoryController(ProductCategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','FOUNDER')")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseProductCategoryDTO createCategory(@RequestBody @Valid CreateProductCategoryDTO dto) {
        ProductCategory cat = categoryService.createCategory(dto);
        return new ResponseProductCategoryDTO(cat.getProductCategoryId(), cat.getName());
    }

    @GetMapping
    public List<ResponseProductCategoryDTO> getAllCategories() {
        return categoryService.getAllCategories().stream()
                .map(cat -> new ResponseProductCategoryDTO(cat.getProductCategoryId(), cat.getName()))
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseProductCategoryDTO getCategory(@PathVariable UUID id) {
        ProductCategory cat = categoryService.getById(id);
        return new ResponseProductCategoryDTO(cat.getProductCategoryId(), cat.getName());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','FOUNDER')")
    public ResponseProductCategoryDTO updateCategory(
            @PathVariable UUID id,
            @RequestBody @Valid CreateProductCategoryDTO dto
    ) {
        ProductCategory cat = categoryService.updateCategory(id, dto);
        return new ResponseProductCategoryDTO(cat.getProductCategoryId(), cat.getName());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','FOUNDER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable UUID id) {
        categoryService.deleteCategory(id);
    }
}