package com.example.sprint.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer quantityBuy;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    @ManyToOne
    private Product product;
    @ManyToOne
    private Oder oder;
    @Column(columnDefinition = "bit")
    private boolean flagDeleted = false;
    @Column(columnDefinition = "bit")
    private boolean statusShipping = false;
    @Column(columnDefinition = "bit")
    private boolean statusPay = false;
    private Double discount;
    private Double price;
}
