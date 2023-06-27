package com.example.cafebackend.table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "point_detail")
public class PointDetail {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "point_detail_id", length = 36 ,nullable = false, updatable = false, unique = true)
    private String pointDetailId;

    @Column(name = "status", length = 36)
    private String status;

    @Column(name = "point", length = 12)
    private Double point;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "record_date")
    private LocalDateTime recordDate;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cus_id")
    private Customer customer;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;


    public PointDetail() {
        //recordDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        recordDate =  LocalDateTime.now();
    }

    @PreRemove
    private void removeCustomerFromOrderDetailPoint() {
        Customer cus  =  customer;
        cus.getPointDetails().remove(this);
    }
}

