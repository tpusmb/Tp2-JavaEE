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
import com.big_fat_trololo.repositry.DepartmentRepo;
import com.big_fat_trololo.uito.DepartmentUITO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.big_fat_trololo.service.DepartmentService;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
	@Autowired
	private DepartmentRepo departmentRepo;

	@Override
	public List<DepartmentUITO> getAllDepartment() {
		List<DepartmentUITO> departmentUITOLst = new ArrayList<>();
		List<DepartmentDTO> departmentDTOLst = departmentRepo.findAll();

		departmentDTOLst.forEach(dto -> {
			DepartmentUITO tmpUiTO = new DepartmentUITO();

			BeanUtils.copyProperties(dto, tmpUiTO);
			departmentUITOLst.add(tmpUiTO);
		});

		return departmentUITOLst;
	}

	@Override
	public DepartmentUITO getDepartment(DepartmentUITO departmentUITO) {
		System.out.println(">>>>> "+departmentUITO.getDeptName());
		DepartmentDTO dto = departmentRepo.findTitleByDeptName(departmentUITO.getDeptName());
		DepartmentUITO uito = new DepartmentUITO();

		BeanUtils.copyProperties(dto, uito);
		return uito;
	}
}