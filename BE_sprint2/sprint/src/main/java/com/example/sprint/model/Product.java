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
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Double price;
    private Integer amountExist;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String code;
    private String size;
    @ManyToOne
    private Port port;
    @ManyToOne
    private ButtonSupport buttonSupport;
    @ManyToOne
    private FrequencyBand frequencyBand;
    @ManyToOne
    private TypeDevice typeDevice;
    @ManyToOne
    private Anteing anteing;
    @ManyToOne
    private TypeAnteing typeAnteing;
    @ManyToOne
    private SpeedWifi speedWifi;
    @ManyToOne
    private StandardNetwork standardNetwork;
    @ManyToOne
    private UserConnect userConnect;
    @ManyToOne
    private CoverageDensity coverageDensity;
    @ManyToOne
    private Manufacture manufacture;
    @ManyToOne
    private MadeIn madeIn;
    @ManyToOne
    private Guarantee guarantee;
    @ManyToOne
    private Promotion promotion;
    @OneToMany(mappedBy = "product")
    @JsonBackReference
    private List<OrderDetail> orderDetailList;
    @OneToMany(mappedBy = "product")
    private List<ImageProduct> imageProducts;
    @Column(columnDefinition = "bit")
    private boolean flagDeleted = false;
    @Column(columnDefinition = "bit")
    private boolean flagPromoted = false;
}
