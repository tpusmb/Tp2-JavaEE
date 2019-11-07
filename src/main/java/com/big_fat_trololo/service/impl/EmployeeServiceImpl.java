/**
 * 
 */
/**
 * @author s727953
 *
 */
package com.big_fat_trololo.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.big_fat_trololo.entity.DepartmentDTO;
import com.big_fat_trololo.entity.EmployeeDTO;
import com.big_fat_trololo.repositry.EmployeeRepo;
import com.big_fat_trololo.service.EmployeeService;
import com.big_fat_trololo.uito.DepartmentUITO;
import com.big_fat_trololo.uito.EmployeeUITO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
    EmployeeRepo employeeRepo;

	@Override
	@Transactional(readOnly = false)
	public EmployeeUITO doSaveEmp(EmployeeUITO employeeUiTO) {
		EmployeeDTO dto = UiToToDto(employeeUiTO);
		dto = employeeRepo.save(dto);
		BeanUtils.copyProperties(dto, employeeUiTO);

		dtoToUito(employeeUiTO, dto);
		return employeeUiTO;
	}

	private void dtoToUito(EmployeeUITO employeeUiTO, EmployeeDTO dto) {
		DepartmentUITO uitTO = new DepartmentUITO();
		BeanUtils.copyProperties(dto.getDepartmentDTO(), uitTO);
		employeeUiTO.setDepartmentUITO(uitTO);
	}

	private EmployeeDTO UiToToDto(EmployeeUITO employeeUiTO) {
		EmployeeDTO dto = new EmployeeDTO();
		DepartmentDTO deptDto = new DepartmentDTO();
		BeanUtils.copyProperties(employeeUiTO, dto);
		BeanUtils.copyProperties(employeeUiTO.getDepartmentUITO(), deptDto);
		dto.setDepartmentDTO(deptDto);
		List<EmployeeDTO> lst = new ArrayList<>();
		lst.add(dto);
		deptDto.getEmployeedtolst().addAll(lst);
		return dto;
	}

	@Override
	public List<EmployeeUITO> doFetchAllEmp() {
		List<EmployeeDTO> dtoLst = employeeRepo.findAll();
		List<EmployeeUITO> uiTOLst = new ArrayList<>();
		dtoLst.forEach(dto -> {
			EmployeeUITO tmpUiTO = new EmployeeUITO();
			System.out.println(dto.getDepartmentDTO());
			BeanUtils.copyProperties(dto, tmpUiTO);

			dtoToUito(tmpUiTO, dto);
			uiTOLst.add(tmpUiTO);
		});
		return uiTOLst;
	}

	@Override
	public EmployeeUITO doGetEmp(EmployeeUITO employeeUiTO) {
		if (null != employeeUiTO.getEmailId()) {
			EmployeeDTO dto = new EmployeeDTO();

			BeanUtils.copyProperties(employeeUiTO, dto);
			dto = employeeRepo.getOne(dto.getEmpId());

			BeanUtils.copyProperties(dto, employeeUiTO);
		}
		return employeeUiTO;
	}

	@Override
	@Transactional
	public void doDeleteEmp(EmployeeUITO employeeUiTO) {

		employeeRepo.deleteById(employeeUiTO.getEmpId());
	}

}