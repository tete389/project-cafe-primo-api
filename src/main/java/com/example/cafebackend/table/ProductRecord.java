package com.example.cafebackend.table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "product_record")
public class ProductRecord {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "prod_rec_id", length = 36 ,nullable = false, updatable = false, unique = true)
    private String prodRecId;

//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @Column(name = "date")
//    private String date;



    @Column(name = "prod_name", length = 36)
    private String prodName;

    @Column(name = "prod_price", length = 12)
    private Double prodPrice;

    @Column(name = "prod_amount", length = 36)
    private Integer prodAmount;

    @Column(name = "price", length = 12)
    private Double price;

    @Column(name = "total_price", length = 12)
    private Double totalPrice;

    @Column(name = "bonus_point", length = 12)
    private Double bonusPoint;

    @Column(name = "prod_id", length = 36)
    private String prodId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @OneToMany(mappedBy = "productRecord", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<OptionRecord> optionRecords = new ArrayList<>();

//    public ProductRecord() {
//        date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
//    }
}

