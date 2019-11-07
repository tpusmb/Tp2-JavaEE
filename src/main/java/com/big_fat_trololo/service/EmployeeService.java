
package com.big_fat_trololo.service;

import java.util.List;

import com.big_fat_trololo.uito.EmployeeUITO;

public interface EmployeeService {
	EmployeeUITO doSaveEmp(EmployeeUITO employeeUiTO);

	List<EmployeeUITO> doFetchAllEmp();

	EmployeeUITO doGetEmp(EmployeeUITO employeeUiTO);

	void doDeleteEmp(EmployeeUITO employeeUiTO);
}