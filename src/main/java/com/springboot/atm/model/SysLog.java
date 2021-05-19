package com.springboot.atm.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@NoArgsConstructor
@Table(name = "system_log")
public class SysLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer logId;
    private String cardId;
    private String operation;
    private String params;
    private Date optime;
}
