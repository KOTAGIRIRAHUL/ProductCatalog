package com.task.catalogMicroservice.Repo;

import com.task.catalogMicroservice.Model.ProductDetails;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepo extends MongoRepository<ProductDetails, String> {

   public ProductDetails findByProductId(String productId);

   public ProductDetails findByProductName(String productName);

   public List<ProductDetails> findByProductType(String productType);


   public List<ProductDetails> findByPrice(Double price);

   //public Optional<ProductDetails> updateProducts(ProductDetails productDetails);


}