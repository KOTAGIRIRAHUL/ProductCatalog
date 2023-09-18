package com.task.catalogMicroservice.ControllerTest;

import com.task.catalogMicroservice.Controller.ProductController;
import com.task.catalogMicroservice.Model.ProductDetails;
import com.task.catalogMicroservice.Service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;

@WebMvcTest(ProductController.class)
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    public void testAddProductWithDuplicateId() throws Exception {
        // Create a sample ProductDetails with a duplicate ID
        ProductDetails duplicateProduct = new ProductDetails("123", "Duplicate Product", "Duplicate", 50, 10000, "good", "yes");

        // Mock the behavior of the productService to return the duplicateProduct
        when(productService.addProductDetails(any(ProductDetails.class))).thenReturn(duplicateProduct);

        // Perform a POST request to the /add endpoint with the duplicateProduct
        mockMvc.perform(MockMvcRequestBuilders.post("/start/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":\"123\",\"name\":\"Duplicate Product\",\"type\":\"Duplicate\",\"quantity\":50,\"price\":10000,\"condition\":\"good\",\"available\":\"yes\"}")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId").value("123"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productName").value("Duplicate Product"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productType").value("Duplicate"));
    }

    @Test
    public void testGetAllProductsWithDuplicateValues() throws Exception {
        // Create a list with duplicate ProductDetails
        List<ProductDetails> duplicateProducts = new ArrayList<>();
        duplicateProducts.add(new ProductDetails("123", "Duplicate Product 1", "Type 1", 50, 10000, "good", "yes"));
        duplicateProducts.add(new ProductDetails("123", "Duplicate Product 2", "Type 2", 60, 11000, "excellent", "no"));

        // Mock the behavior of the productService to return the list with duplicates
        when(productService.getAllProducts()).thenReturn(duplicateProducts);

        // Perform a GET request to the /get endpoint
        mockMvc.perform(MockMvcRequestBuilders.get("/start/get"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2)); // Assuming you expect two items in the response
    }
    @Test
    public void testGetProductsByPriceRangeWithDuplicates() throws Exception {
        // Create a list with duplicate ProductDetails within the price range
        List<ProductDetails> duplicateProducts = new ArrayList<>();
        duplicateProducts.add(new ProductDetails("1", "Product 1", "Type 1", 50, 10000, "good", "yes"));
        duplicateProducts.add(new ProductDetails("2", "Product 2", "Type 2", 60, 11000, "excellent", "no"));
        duplicateProducts.add(new ProductDetails("3", "Product 3", "Type 3", 70, 10000, "good", "yes")); // Duplicate
        duplicateProducts.add(new ProductDetails("4", "Product 4", "Type 4", 80, 9000, "fair", "no"));    // Not in range
        duplicateProducts.add(new ProductDetails("5", "Product 5", "Type 5", 90, 9500, "good", "yes"));  // Duplicate

        // Mock the behavior of the productService to return the list with duplicates within the price range
        when(productService.getProductPrice(anyDouble(), anyDouble())).thenReturn(duplicateProducts);

        // Perform a GET request to the /findByPrice endpoint with a price range
        mockMvc.perform(MockMvcRequestBuilders.get("/start/findByPrice/1000/12000"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(5)); // Updated expectation
    }
}








