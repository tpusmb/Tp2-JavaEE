package com.big_fat_trololo.repositry;

import com.big_fat_trololo.entity.DepartmentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepo extends JpaRepository<DepartmentDTO, Long> {

	DepartmentDTO findTitleByDeptName(String deptName);
}