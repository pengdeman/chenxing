package com.cx.chenxing.service.zj;

import com.cx.chenxing.db.zj.entity.Visitor;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Transactional
public interface IVisitorService {

	public void insert(Visitor lg);

	public List<Visitor> queryListNumByBvisitorId(Visitor v1);

	public List<Visitor> queryListByBvisitorId(Visitor v1);  
	
}
