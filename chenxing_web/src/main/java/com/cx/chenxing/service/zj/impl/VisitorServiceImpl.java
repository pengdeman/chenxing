package com.cx.chenxing.service.zj.impl;

import com.cx.chenxing.db.zj.dao.IVisitorDao;
import com.cx.chenxing.db.zj.entity.Visitor;
import com.cx.chenxing.service.zj.IVisitorService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service  
public class VisitorServiceImpl implements IVisitorService {
    @Resource  
    private IVisitorDao visitorDao;
    
	@Override
	public void insert(Visitor lg) {
		this.visitorDao.insert(lg);
	}

	@Override
	public List<Visitor> queryListNumByBvisitorId(Visitor v1) {
		List<Visitor> vs = visitorDao.queryListNumByBvisitorId(v1);
		return vs;
	}

	@Override
	public List<Visitor> queryListByBvisitorId(Visitor v1) {
		List<Visitor> vs = visitorDao.queryListByBvisitorId(v1);
		return vs;
	}
} 