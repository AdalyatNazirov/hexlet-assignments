package exercise.controller;

import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.mapper.ProductMapper;
import exercise.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    // BEGIN
    @GetMapping(path = "")
    public List<ProductDTO> index() {
        var products = productRepository.findAll();
        return products.stream()
                .map(productMapper::map)
                .toList();
    }

    @GetMapping(path = "{id}")
    public ProductDTO show(@PathVariable Long id) {
        var product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product with id " + id + " not found"));
        var productDTO = productMapper.map(product);
        return productDTO;
    }

    @PostMapping(path = "")
    @ResponseStatus(org.springframework.http.HttpStatus.CREATED)
    public ProductDTO create(@RequestBody ProductCreateDTO productData) {
        var product = productMapper.map(productData);
        productRepository.save(product);
        var productDTO = productMapper.map(product);
        return productDTO;
    }

    @PutMapping(path = "/{id}")
    public ProductDTO update(@PathVariable Long id, @RequestBody ProductUpdateDTO productData) {
        var product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product with id " + id + " not found"));
        productMapper.update(productData, product);
        productRepository.save(product);
        var productDTO = productMapper.map(product);
        return productDTO;
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        var product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product with id " + id + " not found"));
        productRepository.delete(product);
    }
    // END
}
