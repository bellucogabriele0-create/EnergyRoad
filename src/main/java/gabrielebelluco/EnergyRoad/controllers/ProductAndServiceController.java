package gabrielebelluco.EnergyRoad.controllers;

import gabrielebelluco.EnergyRoad.entities.ProductAndService;
import gabrielebelluco.EnergyRoad.payloads.request.CreateProductAndServiceDTO;
import gabrielebelluco.EnergyRoad.payloads.response.ResponseProductAndServiceDTO;
import gabrielebelluco.EnergyRoad.services.ProductAndServiceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductAndServiceController {
    private final ProductAndServiceService productService;

    public ProductAndServiceController(ProductAndServiceService productService) {
        this.productService = productService;
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','FOUNDER')")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseProductAndServiceDTO createProduct(@RequestBody @Valid CreateProductAndServiceDTO dto) {
        return productService.createProduct(dto);
    }

    @GetMapping
    public List<ResponseProductAndServiceDTO> getAllProducts() {
        return productService.getAllProducts().stream()
                .map(p -> new ResponseProductAndServiceDTO(
                        p.getProductAndServiceId(),
                        p.getName(),
                        p.getDescription(),
                        p.getPrice(),
                        p.getCategory().getProductCategoryId()
                ))
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseProductAndServiceDTO getProduct(@PathVariable UUID id) {
        ProductAndService p = productService.getById(id);
        return new ResponseProductAndServiceDTO(
                p.getProductAndServiceId(),
                p.getName(),
                p.getDescription(),
                p.getPrice(),
                p.getCategory().getProductCategoryId()
        );
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','FOUNDER')")
    public ResponseProductAndServiceDTO updateProduct(
            @PathVariable UUID id,
            @RequestBody @Valid CreateProductAndServiceDTO dto
    ) {
        return productService.updateProduct(id, dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','FOUNDER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
    }
}