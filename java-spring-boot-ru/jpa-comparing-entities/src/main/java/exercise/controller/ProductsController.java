package exercise.controller;

import exercise.exception.ResourceAlreadyExistsException;
import exercise.exception.ResourceNotFoundException;
import exercise.model.Product;
import exercise.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping(path = "")
    public List<Product> index() {
        return productRepository.findAll();
    }

    // BEGIN
    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product productData) {
        var products = productRepository.findAll();

        boolean exists = products.stream()
                .anyMatch(product -> product.equals(productData));

        if (exists) {
            throw new ResourceAlreadyExistsException("This product already exists.");
        }

        var product = productRepository.save(productData);
        return product;
    }
    // END

    @GetMapping(path = "/{id}")
    public Product show(@PathVariable long id) {
        var product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));

        return product;
    }

    @PutMapping(path = "/{id}")
    public Product update(@PathVariable long id, @RequestBody Product productData) {
        var product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));

        product.setTitle(productData.getTitle());
        product.setPrice(productData.getPrice());

        productRepository.save(product);

        return product;
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable long id) {
        productRepository.deleteById(id);
    }
}
