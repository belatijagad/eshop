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

## Module 2

### Code Quality Issues
I only have two code quality issues, and thankfully they are both low level. One is reliability bug, which is caused by the `<table>` within my `ProductList.html` don't have a caption, and the other is maintainability issues which is caused by the repeated use of `redirect:/product/list` on the return string within `ProductController.java`. They are both minor and I think quite negligible.

My strategy on fixing them is simply doing what SonarCloud want me to do. I added `<caption>` element within `<table>` tag in `ProductList.html`, and made a `productListRedirect` string variable in `ProductController.java` to replace the repeated use of strings.

### Does My Implementation Met the Definition of CI/CD?
Yes, I do think so. Let's go back to the definition.

> Continuous Integration (CI) is “a software development practice where continuous changes & updates in codebase are integrated and verified by an automated build script using various tools.” (Swaraj, 2017) It is a practice where we want to automate the process of integrating changes by utilizing tools.

In this repository, there are functional tests that checks automatically for every change of code inside the already existing function that I have made. For every push and pull request, the automated scripts will review my code using the unit tests that I have made and SonarCloud code analyzer to see whether I have implemented clean code correctly.

> We refer the further improvement to CI as Continuous Deployment (CD). In CD, we are not only automating the build and testing of a software, but also the delivery process. The delivery process here can be still performed manually or fully automated. If we only produce a runnable product at the end of a CD process, then the “D” in the “CD" stands for “Delivery”. However, if we implement the delivery process until automating the delivery (or, “deployment”), then the “D” in the “CD” stands for “Deployment”.

I have integrated this GitHub repository with Koyeb PaaS, so every time a change occur in the main branch, it will automatically rebuild and redeploy the app so the deployment will be updated.

# Module 3
## Principles that are applied to the project
On this occasion, I am implementing the SOLID principles into the project I am currently working on. SOLID principles are actually an acronym for 5 aspects needed when designing OOP to make it more maintainable during subsequent processes. These 5 aspects are: Single Responsibility Principle (SRP), Open-Closed Principle (OCP), Liskov Substitution Principle (LSP), Interface Segregation Principle (ISP), and Dependency Inversion Principle (DIP). Here's what I have implemented based on these five aspects:

### Single Responsibility Principle (SRP)
Every class should adhere to its own functionality. In the before-solid branch, the CarController class is nested within the ProductController class, so it needs to be separated so that CarController has its own class file. Also, the constructor of the Product model should not assign values inside the constructor, so I removed the constructor that assigned the UUID value to the Product's id and moved it to the create method in the ProductRepository. Additionally, CarController does not need to extend ProductController because CarController only needs mapping for car-related functionalities.

### Open-Closed Principle (OCP)
In the CarController class, the carService attribute uses the CarService interface instead of the CarServiceImpl class. This is to ensure it is open-closed.

### Liskov Substitution Principle (LSP)
CarController does not need to extend ProductController, and the carService attribute uses the CarService interface.

### Interface Segregation Principle (ISP)
By changing the carService attribute, the CarServiceImpl class does not need to implement methods from the interface they use.

### Dependency Inversion Principle (DIP)
Removed the public modifier on the interface class because it is redundant. Also, changing the carService attribute to use the CarService interface falls under DIP.


## Advantages of applying SOLID to the project with examples
By applying SOLID principles, the quality of code, maintainability, comprehensibility, and extensibility will be improved. With Single Responsibility principle, each class will only do their purpose only, clarifying it's function so it can be easily understood. The Open/Closed Principle enables feature extension without disrupting existing code and reduces the risk of introducing bugs. The Liskov Substitution Principle ensures that a subclass can be used as a substitute for its superclass. The Interface Segregation Principle separates interfaces so that clients only depend on relevant ones. Lastly, the Dependency Inversion Principle allows for flexibility. With SOLID principles, we can create code as described earlier. For example, if we encounter an error in the CarController class, we will know where to debug because the features are contained within that class itself, adhering to the Single Responsibility Principle (SRP).

## Disadvantages of not applying SOLID with examples
Failure to apply SOLID principles in our project can lead to significant drawbacks. For example, as mentioned earlier, not adhering to the Single Responsibility Principle (SRP) can result in classes becoming overly complex with excessive workloads, making them difficult to understand. If CarController remains within the ProductController file, we may become confused when debugging errors in that file, as we would need to debug either the CarController or the ProductController. Additionally, suppose there's an interface named Service containing all methods that should be in CarService and ProductService. In that case, the implementation in CarServiceImpl would have to implement methods such as Product create(Product product); that would never be used.