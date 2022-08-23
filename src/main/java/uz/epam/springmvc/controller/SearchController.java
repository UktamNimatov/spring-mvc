package uz.epam.springmvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.epam.springmvc.bean.Product;
import uz.epam.springmvc.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    private ProductRepository productRepository;

    @Autowired
    public SearchController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/search")
    public String search(@RequestParam("search") String search, Model model) {
        logger.info("in search controller");
        logger.info("in search criteria");

        List<Product> products;
        products = productRepository.searchByName(search);

        model.addAttribute("products", products);
        return "search";
    }
}
