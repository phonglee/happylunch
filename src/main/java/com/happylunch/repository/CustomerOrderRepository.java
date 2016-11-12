package com.happylunch.repository;

import com.happylunch.entity.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Integer> {

    @Query("from CustomerOrder order by dateCreated desc")
    List<CustomerOrder> findAllDesc();

    @Query("from CustomerOrder where deleteFlag=false order by dateCreated desc")
    List<CustomerOrder> findAllAvailable();
}
