package com.lng.service.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.lng.dao.TrucksHeadTypeDao;
import com.lng.pojo.TrucksHeadType;
import com.lng.service.TrucksHeadTypeService;

@Service
public class TrucksHeadTypeServiceImpl implements TrucksHeadTypeService {
	@Autowired
	private TrucksHeadTypeDao trucksHeadTypeDao;

	@Override
	public String saveOrUpdate(TrucksHeadType trucksHeadType) {
		return trucksHeadTypeDao.save(trucksHeadType).getId();
	}

	@Override
	public TrucksHeadType findById(String id) {
		if(!id.isEmpty()) {
			Optional<TrucksHeadType> tht =  trucksHeadTypeDao.findById(id);
			if(tht.isPresent()) {
				return tht.get();
			}
			return null;
		}else {
			return null;
		}
		
	}

	@SuppressWarnings("serial")
	@Override
	public List<TrucksHeadType> getTrucksHeadTypeByNameList(String name) {
		if (!name.isEmpty()) {
			Specification<TrucksHeadType> spec = new Specification<TrucksHeadType>() {

				@Override
				public Predicate toPredicate(Root<TrucksHeadType> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					Predicate pre = cb.conjunction();
					pre.getExpressions().add(cb.equal(root.get("name"), name));
					return pre;
				}
			};
			return trucksHeadTypeDao.findAll(spec);
		}
		Sort sort = Sort.by(Sort.Direction.DESC, "addTime");//同一天价格时间降序排列
		return trucksHeadTypeDao.findAll(sort);
	}

}
