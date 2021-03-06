package com.lng.service.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.lng.dao.DriverQzDao;
import com.lng.pojo.DriverQz;
import com.lng.service.DriverQzService;

@Service
public class DriverQzServiceImpl implements DriverQzService {
	@Autowired
	private DriverQzDao driverQzDao;

	@Override
	public String saveOrUpdate(DriverQz driverQz) {
		return driverQzDao.save(driverQz).getId();
	}

	@Override
	public DriverQz getEntityById(String id) {
		if (!id.isEmpty()) {
			Optional<DriverQz> qz = driverQzDao.findById(id);
			if (qz.isPresent()) {
				return qz.get();
			}
			return null;
		} else {
			return null;
		}

	}

	@SuppressWarnings("serial")
	@Override
	public Page<DriverQz> getDriverQzByOption(String userId, String jzYear, String jzType, String wage,
			Integer checkSta, Integer showSta, Integer pageNo, Integer pageSize) {
		Sort sort = Sort.by(Sort.Direction.DESC, "addTime");// 降序排列
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

		Specification<DriverQz> spec = new Specification<DriverQz>() {

			@Override
			public Predicate toPredicate(Root<DriverQz> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate pre = cb.conjunction();
				if (!userId.isEmpty()) {
					pre.getExpressions().add(cb.equal(root.get("userId"), userId));
				}
				if (!jzYear.isEmpty()) {
					String[] jzYearArr = jzYear.split("-");
					Integer jzYear1 = Integer.parseInt(jzYearArr[0]);
					Integer jzYear2 = null;
					if (jzYearArr.length == 1) {
						jzYear2 = jzYear1;
					} else {
						jzYear2 = Integer.parseInt(jzYearArr[1]);
					}
					pre.getExpressions().add(cb.between(root.get("jzYear"), jzYear1, jzYear2));
				}
				if (!jzType.isEmpty()) {
					pre.getExpressions().add(cb.equal(root.get("jzType"), jzType));
				}
				if (!wage.isEmpty()) {
					String[] wages = wage.split("-");
					Integer wage1 = Integer.parseInt(wages[0]);
					Integer wage2 = null;
					if (wages.length == 1) {
						wage2 = wage1;
					} else {
						wage2 = Integer.parseInt(wages[1]);
					}
					pre.getExpressions().add(cb.between(root.get("wage"), wage1, wage2));
				}
				if (checkSta != -1) {
					pre.getExpressions().add(cb.equal(root.get("checkStatus"), checkSta));
				}
				if (showSta != -1) {
					pre.getExpressions().add(cb.equal(root.get("showStatus"), showSta));
				}
				return pre;
			}
		};
		return driverQzDao.findAll(spec, pageable);
	}

	@SuppressWarnings("serial")
	@Override
	public List<DriverQz> getDriverQzByUserId(String userId) {
		if (!userId.isEmpty()) {
			Specification<DriverQz> spec = new Specification<DriverQz>() {
				@Override
				public Predicate toPredicate(Root<DriverQz> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					Predicate pre = cb.conjunction();
					if (!userId.isEmpty()) {
						pre.getExpressions().add(cb.equal(root.get("userId"), userId));
					}
					return pre;
				}
			};
			return driverQzDao.findAll(spec);
		}
		return driverQzDao.findAll();
	}

	@SuppressWarnings("serial")
	@Override
	public List<DriverQz> listQzInfoByOpt(String sDate, String eDate, Integer checkSta, Integer showSta) {
		// TODO Auto-generated method stub
		Sort sort = Sort.by(Sort.Direction.DESC, "addTime");// 降序排列

		Specification<DriverQz> spec = new Specification<DriverQz>() {

			@Override
			public Predicate toPredicate(Root<DriverQz> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate pre = cb.conjunction();
				if (!sDate.isEmpty() && !eDate.isEmpty()) {
					pre.getExpressions().add(cb.greaterThanOrEqualTo(root.get("addTime"), sDate + " 00:00:01"));
					pre.getExpressions().add(cb.lessThanOrEqualTo(root.get("addTime"), eDate + " 23:59:59"));
				}
				if (checkSta != -1) {
					pre.getExpressions().add(cb.equal(root.get("checkStatus"), checkSta));
				}
				if (showSta != -1) {
					pre.getExpressions().add(cb.equal(root.get("showStatus"), showSta));
				}
				return pre;
			}
		};
		return driverQzDao.findAll(spec, sort);
	}

}
