package com.springboot.atm.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "transfer_records")
public class TransferModel {
    @Id
    @GeneratedValue(generator = "atmGenerator")
    @GenericGenerator(name = "atmGenerator", strategy = "uuid")
    @Column(name = "transferId", nullable = false, length = 36)
    private String transferId;
    private String cardId;
    // 1 sacar 2 ingresar 3 transferir
    private int transType;
    private int transMoney;
    private Date transDate;

}
