package uz.epam.springmvc.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.epam.springmvc.bean.Product;
import uz.epam.springmvc.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductsRestController {

    private ProductRepository productRepository;

    @Autowired
    public ProductsRestController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /*@GetMapping("/hplus/rest/products")
    @ResponseBody
    public List<Product> getProducts() {
        Iterable<Product> iterableProducts = productRepository.findAll();
        List<Product> productList = new ArrayList<>();

        iterableProducts.forEach(productList::add);
        return productList;
    }*/

    @GetMapping("/hplus/rest/products")
    public ResponseEntity getProductsByRequestParam(@RequestParam("name") String name) {
        List<Product> productList = productRepository.searchByName(name);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/hplus/rest/products/{name}")
    public ResponseEntity getProductsByPathVariable(@PathVariable("name") String name) {
        List<Product> productList = productRepository.searchByName(name);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }
}
