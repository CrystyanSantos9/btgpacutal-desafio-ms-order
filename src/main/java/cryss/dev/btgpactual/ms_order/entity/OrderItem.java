package cryss.dev.btgpactual.ms_order.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderItem {
    private String product;
    private Integer quantity;

    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal price;
}
