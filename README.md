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

### Reflection 2: Unit Tests
After writing unit tests, I feel horrible. Speedrunning this assignment was something I don't want to do again since it puts me in a lot of stress. The number of unit tests needed depends of how much feature you implement. To be precise, I would do as much as the number of functions if I have the time.

I think code coverage is a good enough metric to measure if the tests that had been made are enough or not. It's good enough, but not perfect since 100% code coverage don't guarantee a bug-free program.

I don't think the code will be clean if you create another functional test for verifying the quantity of a product (the existing one is just to check the name of product). It would be better to test both at one test rather than writing them separately since they simply do the same thing but different data to be checked.

My code is imperfect and I don't have time to check the tests since somehow my gradle can't read the tests. I've already searched everywhere but this is the sin of a deadliner, so I can't verify them. I tried my best to write them all tho.