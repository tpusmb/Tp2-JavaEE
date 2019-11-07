package com.big_fat_trololo.repositry;

import com.big_fat_trololo.entity.EmployeeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<EmployeeDTO, Long> {
}