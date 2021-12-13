package vn.techmaster.demomapstruct.dto.simple2;

import java.util.Collection;

import lombok.Data;

@Data
public class Customer {
    private Long id;
    private String name;
    private Collection<OrderItem> orderItems;    
}
