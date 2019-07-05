package com.example.TraineeHackathon.BaseClass;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
interface VendorRepository extends JpaRepository<VendorBase, Long> {
    @Query(value = "SELECT COUNT (DISTINCT (UPPER(VENDOR_NAME))) FROM Vendor", nativeQuery = true)
   Long countDistinctVendorNameBy();
}
