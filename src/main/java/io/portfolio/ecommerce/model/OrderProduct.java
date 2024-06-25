package io.portfolio.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

@Entity
public class OrderProduct {

    @EmbeddedId //the @EmbeddedId annotation is used to embed the OrderProductPK within the OrderProduct class
    @JsonIgnore //the @JsonIgnore annotation is used to ignore the OrderProductPK in the JSON response
    private OrderProductPK pk;

    @Column //The @Column annotation is used to specify the column name in the database
    private int quantity;

    //standard constructor
    public OrderProduct(){
        super();
    }

    //This constructor is used to initialize the OrderProductPK and quantity fields for dependency injection
    public OrderProduct(Order order, Product product, Integer quantity){
        pk = new OrderProductPK();
        pk.setOrder(order);
        pk.setProduct(product);
        this.quantity = quantity;
    }

    @Transient //the @Transient annotation is used to make the quantity field not included in the JSON response
    public Product getProduct(){
        return this.pk.getProduct();
    }

    @Transient
    public double getTotalPrice(){
        return this.quantity * this.pk.getProduct().getPrice();
    }

    public OrderProductPK getPk() {
        return pk;
    }

    public void setPk(OrderProductPK pk) {
        this.pk = pk;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;
        result = prime * result + ((pk == null) ? 0 : pk.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj)
            return true;

        if(obj == null)
            return false;

        if(getClass() != obj.getClass())
            return false;
        OrderProduct other = (OrderProduct) obj;
        if(pk == null){
            return other.pk == null;
        }else return pk.equals(other.pk);
    }
}
