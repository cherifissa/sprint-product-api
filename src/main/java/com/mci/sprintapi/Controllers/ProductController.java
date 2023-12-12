package com.mci.sprintapi.Controllers;

import com.mci.sprintapi.Exceptions.ResourceNotFoundException;
import com.mci.sprintapi.Models.Product;
import com.mci.sprintapi.Repository.ProductRepo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.lang.module.ResolutionException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class ProductController {
    private final ProductRepo productRepo;


    public ProductController(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }
    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productRepo.findAll();
    }
    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable(value = "id") Long prodID) throws ResourceNotFoundException{

        return productRepo.findById(prodID)
                .orElseThrow(()->new ResolutionException("produits avec "+ prodID + " est introuvable !"));
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable(value = "id") Long prodID) throws ResourceNotFoundException{
        Product product = productRepo.findById(prodID)
                .orElseThrow(()->new ResolutionException("produits avec "+ prodID + " est introuvable !"));
        productRepo.delete(product);
    }
    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable(value = "id") Long prodID, @Validated @RequestBody Product prodDetails
    ) throws ResourceNotFoundException{

        Product product = productRepo.findById(prodID)
                .orElseThrow(()->new ResolutionException("produits avec "+ prodID + " est introuvable !"));

        product.setName(prodDetails.getName());
        product.setPrice(prodDetails.getPrice());

        return productRepo.save(product);
    }
    @PostMapping("products")
    public Product createProduct(@Validated @RequestBody Product product){
        return  productRepo.save(product);
    }
}
