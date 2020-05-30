package com.cx.chenxing.service.zj;

import com.cx.chenxing.db.zj.entity.Footprint;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Transactional
public interface IFootprintService {

	public void insert(Footprint lg);

	Footprint getFootprintById(int footprintId);

	List<Footprint> footprintList(Footprint lg);

	public List<Footprint> fList(Footprint f);

	public void updateSelective(Footprint f);

	public void deleteByPrimaryKey(int parseInt);
	
	Footprint selectByPrimaryKey(int parseInt);

	public List<Footprint> publicfootprintList(Footprint fp);

}
