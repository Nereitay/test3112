package com.springboot.atm.repository;

import com.springboot.atm.model.SysLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysLogRepository extends JpaRepository<SysLog, Integer> {
}
