package com.cx.chenxing.service.zj.impl;

import com.cx.chenxing.db.zj.dao.IFootprintDao;
import com.cx.chenxing.db.zj.entity.Footprint;
import com.cx.chenxing.service.zj.IFootprintService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service 
public class FootprintServiceImpl implements IFootprintService {
    @Resource  
    private IFootprintDao footprintDao;

    @Override
	public void insert(Footprint lg) {
		this.footprintDao.insert(lg);
	}
    
    @Override  
    public Footprint getFootprintById(int footprintId) {  
        return this.footprintDao.selectByPrimaryKey(footprintId);  
    }

    @Override
	public List<Footprint> footprintList(Footprint lg) {
		return this.footprintDao.footprintList(lg);
	}

	@Override
	public List<Footprint> fList(Footprint f) {
		return this.footprintDao.fList(f);
	}

	@Override
	public void updateSelective(Footprint f) {
		this.footprintDao.updateSelective(f);
	}

	@Override
	public void deleteByPrimaryKey(int parseInt) {
		this.footprintDao.deleteByPrimaryKey(parseInt);
	}

	@Override
	public Footprint selectByPrimaryKey(int parseInt) {
		Footprint article = footprintDao.selectByPrimaryKey(parseInt);
		return article;
	}

	@Override
	public List<Footprint> publicfootprintList(Footprint fp) {
		return this.footprintDao.publicfootprintList(fp);
	}
}
