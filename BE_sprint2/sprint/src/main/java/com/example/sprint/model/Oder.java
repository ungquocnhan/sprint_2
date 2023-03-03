package com.example.sprint.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
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
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date")
    private Date modifyDate;
    @Column(columnDefinition = "text")
    private String note;
    @OneToOne
    private Customer customer;
    @OneToMany(mappedBy = "oder")
    @JsonBackReference
    private List<OrderDetail> orderDetaiList;
    @Column(columnDefinition = "bit default false")
    private boolean flagDeleted;
}
