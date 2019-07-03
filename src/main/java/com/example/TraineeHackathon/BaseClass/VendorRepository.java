package com.example.TraineeHackathon.BaseClass;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface VendorRepository extends JpaRepository<VendorBase, Long> {
    Long countDistinctVendorBy();
}
