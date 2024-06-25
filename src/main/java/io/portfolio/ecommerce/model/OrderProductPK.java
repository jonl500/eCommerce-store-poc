package io.portfolio.ecommerce.model;
import java.io.Serial;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable//the @Embeddable annotation is used to specify that the
// OrderProductPK class is an embedded class
//which means that it can be embedded in other classes
// such as the OrderProduct class letting it be used as a primary key
public class OrderProductPK implements Serializable{
    //the Serializable interface is used to make the class serializable
    // which means that it can be serialized
    @Serial //the @Serial annotation is used to make the class serializable
    private static final long serialVersionUID = 476151177562655457L;

    @JsonBackReference
    // the @JsonBackReference annotation is used to prevent infinite recursion
    // when serializing the OrderProduct class and coresponds to the @JsonManagedReference annotation
    //the @ManyToOne annotation is used to specify that the OrderProductPK class is a foreign key
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    //the @JoinColumn annotation is used to specify the foreign key
    @JoinColumn(name = "order_id")
    private Order order;

    //the @ManyToOne annotation is used to specify that the OrderProductPK class is a foreign key
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    //the @JoinColumn annotation is used to specify the foreign key
    @JoinColumn(name = "product_id")
    private Product product;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + (order == null ? 0 : order.getId().hashCode());
        result = prime * result + (product == null ? 0 : product.getId().hashCode());

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        OrderProductPK other = (OrderProductPK) obj;
        if (order == null) {
            if (other.order != null) {
                return false;
            }
        } else if (!order.equals(other.order)) {
            return false;
        }
        if (product == null) {
            if (other.product != null) {
                return false;
            }
        } else if (!product.equals(other.product)) {
            return false;
        }
        return true;
    }



}
