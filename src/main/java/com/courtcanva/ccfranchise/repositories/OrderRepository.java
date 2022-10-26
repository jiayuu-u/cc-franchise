package com.courtcanva.ccfranchise.repositories;

import com.courtcanva.ccfranchise.constants.OrderStatus;
import com.courtcanva.ccfranchise.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findFirst10ByFranchiseeIdAndStatus(Long franchiseeId, OrderStatus statusCode);

}
