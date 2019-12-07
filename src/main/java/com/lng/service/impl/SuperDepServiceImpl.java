package com.lng.service.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.lng.dao.SuperDepDao;
import com.lng.pojo.SuperDep;
import com.lng.service.SuperDepService;

@Service
public class SuperDepServiceImpl implements SuperDepService{

	@Autowired
	private SuperDepDao sdDao;
	@Override
	public List<SuperDep> listSpecInfoByUserId(String userId) {
		// TODO Auto-generated method stub
		Specification<SuperDep> spec = new Specification<SuperDep>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<SuperDep> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				Predicate pre = cb.conjunction();
				if(!userId.isEmpty()) {
					pre.getExpressions().add(cb.equal(root.get("superUser").get("id"), userId));
				}
				return pre;
			}};
		return sdDao.findAll(spec);
	}
	@Override
	public List<SuperDep> listInfoByDepId(String roleId) {
		// TODO Auto-generated method stub
		if(!roleId.isEmpty()) {
			Specification<SuperDep> spec = new Specification<SuperDep>() {
				private static final long serialVersionUID = 1L;
				@Override
				public Predicate toPredicate(Root<SuperDep> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					// TODO Auto-generated method stub
					Predicate pre = cb.conjunction();
					pre.getExpressions().add(cb.equal(root.get("department").get("id"), roleId));
					return pre;
			}};
			Sort sort = Sort.by(Sort.Direction.DESC, "id");//主键降序排列
			return sdDao.findAll(spec,sort);
		}
		return sdDao.findAll();
	}

}
