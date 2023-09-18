package com.task.catalogMicroservice.Controller;

import com.task.catalogMicroservice.Model.ProductDetails;
import com.task.catalogMicroservice.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/start")

public class ProductController {

    @Autowired
    ProductService productService;



    @PostMapping("/add")
    public ProductDetails addProductDetails(@RequestBody ProductDetails productDetails){
        return productService.addProductDetails(productDetails);
    }

    @GetMapping("/get")
    public List<ProductDetails> getAllProducts(){
        return productService.getAllProducts();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProductModel(@PathVariable String id){
        return productService.deleteProductDetails(id);
    }

    @GetMapping("/get/{id}")
    public ProductDetails getProductById(@PathVariable String id) throws Exception {
        ProductDetails productDetails = productService.getById(id);
        if (productDetails == null) {
            throw new Exception("Enter the valid Id");
        }
        return productService.getById(id);
    }

    @GetMapping("/getByProductName/{name}")
    public ProductDetails getByName(@PathVariable("name") String name) throws Exception {
        ProductDetails productDetails = productService.getByName(name);
        if (productDetails == null) {
            throw new Exception("Name is not available");

        }
        return productService.getByName(name);
    }

    @GetMapping("/findByProductType/{productType}")
    public List<ProductDetails> getByProductType(@PathVariable("productType") String productType){
        return productService.getByProductType(productType);
    }


   @GetMapping("/findByPrice/{lowPrice}/{highPrice}")
    public List<ProductDetails> getByPrice(@PathVariable("lowPrice") double lowPrice , @PathVariable("highPrice") double highPrice){
        return productService.getProductPrice(lowPrice, highPrice);
    }



    @PutMapping("/update")
    public ProductDetails update(@RequestBody ProductDetails productDetails){
        return productService.updateProduct(productDetails);
    }



}
