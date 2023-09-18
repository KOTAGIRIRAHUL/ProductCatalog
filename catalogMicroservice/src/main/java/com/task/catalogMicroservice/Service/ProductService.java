package com.task.catalogMicroservice.Service;


import com.task.catalogMicroservice.Model.ProductDetails;
import com.task.catalogMicroservice.Repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class ProductService {

    @Autowired
    ProductRepo productRepo;



    public List<ProductDetails> getAllProducts(){
        return productRepo.findAll();
    }

    public ProductDetails addProductDetails(ProductDetails productDetails){
        return productRepo.save(productDetails);

    }

    public String deleteProductDetails(String id){
        ProductDetails productDetails =productRepo.findByProductId(id);
        productRepo.delete(productDetails);
        return "Deleted Successfully by Id";
    }

    public ProductDetails getById(String id){
        return productRepo.findByProductId(id);

    }

    public ProductDetails getByName(String productName){
        return productRepo.findByProductName(productName);

    }

    public List<ProductDetails> getByProductType(String productType){
        return productRepo.findByProductType(productType);
    }

    /*public List<ProductDetails> getByPrice(double price){
        return productRepo.findByPrice(price);
    }*/


    /*public List<ProductDetails> getProductPrice(double low,double high){
        List<ProductDetails> productDetails = productRepo.findAll();
        List<ProductDetails> new1 =new ArrayList<>();
        productDetails.stream().filter(n->n.price<=high&&n.price>=low).forEach(n->new1.add(n));
        return new1;
    }*/

    public List<ProductDetails> getProductPrice(double low,double high) {
        List<ProductDetails> productDetails = productRepo.findAll();
        List<ProductDetails> new1 = new ArrayList<>();
        for (ProductDetails new2:productDetails) {
            if(new2.price<=high && new2.price>=low){
                new1.add(new2);
            }

        }
        return new1;
    }

    public ProductDetails updateProduct(ProductDetails productDetails){
        ProductDetails productDetails1 = productRepo.findByProductId(productDetails.getProductId());
        productDetails1.setProductName(productDetails.getProductName());
        productDetails1.setProductType(productDetails.getProductType());
        productDetails1.setQuantity(productDetails.getQuantity());
        productDetails1.setPrice(productDetails.getPrice());
        productDetails1.setDescription(productDetails.getDescription());
        productDetails1.setAvailable(productDetails.getAvailable());

        return productRepo.save(productDetails1);
    }


}
