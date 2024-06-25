package io.portfolio.ecommerce.model;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/*The Jackson imports are for JSON serialization/deserialization */
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

//The imports belong to JPA
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.Valid;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateCreated;

    private String status;

    //the @JsonManagedReference annotation is used to prevent infinite recursion
    // when serializing the Order object
    //@OneToMany annotation is used to create a one-to-many relationship between Order and Product
    //the mappedBy attribute is used to specify the name of the field in the OrderProduct class
    //that refers to the Order object
    //the @Valid annotation is used to validate the Order object
    @JsonManagedReference
    @OneToMany(mappedBy = "pk.order")
    @Valid
    private List<OrderProduct> orderProducts = new ArrayList<>();

        @Transient
    public double getTotalPrice(){
            double sum = 0D; //the D is a double literal and is used to specify the double type
            List<OrderProduct> products = getOrderProducts();
            for (OrderProduct op : orderProducts) {
                sum += op.getTotalPrice();
            }
            return sum;
        }

        @Transient
        public int getNumberOfProducts(){
            return this.orderProducts.size();
        }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public void addOrderProduct(OrderProduct orderProduct) {
        this.orderProducts.add(orderProduct);
    }



}
