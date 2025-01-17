package com.courtcanva.ccfranchise.repositories;

import com.courtcanva.ccfranchise.models.Franchisee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FranchiseeRepository extends JpaRepository<Franchisee, Long> {

    boolean existsFranchiseeByAbn(String abn);

    Optional<Franchisee> findFranchiseeById(Long franchiseeId);


}
