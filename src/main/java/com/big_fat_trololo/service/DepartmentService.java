
package com.big_fat_trololo.service;

import java.util.List;

import com.big_fat_trololo.uito.DepartmentUITO;

public interface DepartmentService {

	List<DepartmentUITO> getAllDepartment();

	DepartmentUITO getDepartment(DepartmentUITO departmentUITO);

}