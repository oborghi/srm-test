package com.srm.test.dao;

import com.srm.test.entity.Risk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiskDao extends JpaRepository<Risk, Integer> {
}
