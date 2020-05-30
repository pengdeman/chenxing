package com.cx.chenxing.service.zj.impl;

import com.cx.chenxing.db.zj.dao.IWzDao;
import com.cx.chenxing.db.zj.entity.Wz;
import com.cx.chenxing.service.zj.IWzService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service 
public class WzServiceImpl implements IWzService {
    @Resource  
    private IWzDao wzDao;

	@Override
	public void insert(Wz lg) {
		this.wzDao.insert(lg);
	}

	@Override
	public List<Wz> wzList() {
		return this.wzDao.wzList();
	}

	@Override
	public List<Wz> wzListbymail(Wz lg) {
		return this.wzDao.wzListbymail(lg);
	}

}
