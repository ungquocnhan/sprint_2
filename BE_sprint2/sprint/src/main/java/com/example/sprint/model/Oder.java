package com.example.sprint.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Oder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String code;
    @Column(columnDefinition = "date")
    private String dateOrder;
    private boolean status = false;
    private boolean statusPay = false;
    private String address;
    private String phoneNumber;
    @ManyToOne
    private Customer customer;
    @OneToMany(mappedBy = "oder")
    @JsonBackReference
    private List<OrderDetail> orderDetaiList;
    @Column(columnDefinition = "bit default false")
    private boolean flagDeleted;
}
