package com.example.cafebackend.table;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @Column(name = "cus_id", length = 36, nullable = false, updatable = false, unique = true)
    private String cusId;

    @Column(name = "phone_number", length = 36, nullable = false, updatable = false, unique = true)
    private String phoneNumber;

    @Column(name = "pointCount", length = 36, nullable = false)
    private Double pointCount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_date")
    private LocalDateTime startDate;

//    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
//    private List<OrderDetailPoint> orderDetailPoints = new ArrayList<>();

    public Customer() {
        //startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        startDate = LocalDateTime.now(ZoneId.of("Asia/Bangkok"));
    }
}
