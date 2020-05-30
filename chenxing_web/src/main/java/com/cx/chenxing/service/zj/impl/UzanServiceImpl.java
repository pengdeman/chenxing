package com.cx.chenxing.service.zj.impl;

import com.cx.chenxing.db.zj.dao.IUzanDao;
import com.cx.chenxing.db.zj.entity.Uzan;
import com.cx.chenxing.service.zj.IUzanService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service  
public class UzanServiceImpl implements IUzanService {
    @Resource  
    private IUzanDao uzanDao;

	@Override
	public void insert(Uzan lg) {
		this.uzanDao.insert(lg);
	}

	@Override
	public List<Uzan> queryzanlist(Uzan lg) {
		return this.uzanDao.queryzanlist(lg);
	}

} 