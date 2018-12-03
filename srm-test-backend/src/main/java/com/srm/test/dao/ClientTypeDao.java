package com.srm.test.dao;

import com.srm.test.entity.ClientType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientTypeDao extends JpaRepository<ClientType, Integer> {
}
