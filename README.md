# Advanced Programming

## Module 1

### Reflection 1: Implementations
In this assignment, I used meaningful variable and function names in order to make my code easier to read. Here is an example of it:
```java
public Product update(String id, Product updatedProduct) {
    Product existingProduct = productRepository.getById(id);
    if (existingProduct != null) {
        existingProduct.setProductName(updatedProduct.getProductName());
        existingProduct.setProductQuantity(updatedProduct.getProductQuantity());
    }
    return existingProduct;
}
```

Because the function and variable names represent what the code is doing, commenting the code is no longer necessary. The code is already self explaining and the chance of me writing a misleading comment will decrease to zero.

Objects and data structures are also heavily used here. Interface is used to construct the `ProductServiceImpl` class, which stores all the CRUD operations needed in a neatly named format. `ProductServiceImpl` calls methods from `ProductRepository`, so the heavy-coding logic is written at `ProductRepository.

For now, there aren't much error handling and secure coding (eg: input handling) done since it's still the first tutorial, and I haven't learned how to handle errors in Springboot just yet.

### Reflection 2: TBD