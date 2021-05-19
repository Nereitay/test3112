package com.springboot.atm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "card")
public class CardModel {
    @Id
    @GeneratedValue(generator = "atmGenerator")
    @GenericGenerator(name = "atmGenerator", strategy = "uuid")
    @Column(name = "cardId", nullable = false, length = 36)
    private String cardId;
    // false debito, true credito
    private Boolean carType;
    @JsonIgnore
    private String password;
    private String customerId;
    //false inactivo, true activo
    private Boolean active;
    private int credit;
    @Column(name = "changed", nullable = false, columnDefinition = "int default 0")
    private Boolean changed;


}
