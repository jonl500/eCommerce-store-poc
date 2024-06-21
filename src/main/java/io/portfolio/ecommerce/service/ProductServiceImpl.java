package io.portfolio.ecommerce.service;
import io.portfolio.ecommerce.model.Product;
import io.portfolio.ecommerce.exception.ResourceNotFoundException;
import io.portfolio.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    private ProductRepository productRepository;



    /**
     * This method is used to get all products via CrudRepository.
     * it uses findAll() method from CrudRepository to get all products.
     * */
    @Override
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Retrieves a Product object from the repository by its ID.
     *
     * @param id the ID of the product to retrieve
     * @return the Product object if found
     * @throws ResourceNotFoundException if the product with the given ID is not found
     */
    @Override
    public Product getProduct(long id) {
        return productRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
    }

    /**
     * Creates or updates a Product in the database.
     *
     * This method implements the 'Create' and 'Update' operations in CRUD.
     * If the Product object has an existing ID, it updates the corresponding record in the database.
     * If the Product object does not have an ID, it creates a new record in the database.
     *
     * @param product The Product object to be saved or updated
     * @return The saved or updated Product object
     */
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

}
