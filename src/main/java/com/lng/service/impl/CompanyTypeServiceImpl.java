package com.lng.service.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.lng.dao.CompanyTypeDao;
import com.lng.pojo.CompanyType;
import com.lng.service.CompanyTypeService;

@Service
public class CompanyTypeServiceImpl implements CompanyTypeService {
	@Autowired
	private CompanyTypeDao cTypeDao;

	@Override
	public String saveOrUpdate(CompanyType ctype) {
		return cTypeDao.save(ctype).getId();
	}

	@Override
	public CompanyType findById(String id) {
		if(!id.isEmpty()) {
			Optional<CompanyType> ct = cTypeDao.findById(id);
			if(ct.isPresent()) {
				return ct.get();
			}
			return null;
		}else {
			return null;
		}
		
	}

	@SuppressWarnings("serial")
	@Override
	public List<CompanyType> getCompanyTypeList(Integer status) {
		if(status.equals(0)) {
			return cTypeDao.findAll();
		}else {
			Specification<CompanyType> spec = new Specification<CompanyType>() {

				@Override
				public Predicate toPredicate(Root<CompanyType> root, CriteriaQuery<?> query,
						CriteriaBuilder cb) {
					Predicate pre = cb.conjunction();
					pre.getExpressions().add(cb.equal(root.get("status"), status));
					return pre;
				}};
				return cTypeDao.findAll(spec); 
		}
	}

	@SuppressWarnings("serial")
	@Override
	public List<CompanyType> getCompanyTypeByNameList(String name) {
		if(!name.isEmpty()) {
			Specification<CompanyType> spec = new Specification<CompanyType>() {

				@Override
				public Predicate toPredicate(Root<CompanyType> root, CriteriaQuery<?> query,
						CriteriaBuilder cb) {
					Predicate pre = cb.conjunction();
					pre.getExpressions().add(cb.equal(root.get("name"), name));
					return pre;
				}};
				return cTypeDao.findAll(spec); 
		}
		return null;
	}

}
