package com.cx.chenxing.db.zj.dao;

import com.cx.chenxing.db.zj.entity.Footprint;
import java.util.List;

public interface IFootprintDao {

	void insert(Footprint lg);
	
	Footprint selectByPrimaryKey(Integer id);

	List<Footprint> footprintList(Footprint lg);

	List<Footprint> fList(Footprint f);

	void updateSelective(Footprint f);

	void deleteByPrimaryKey(int parseInt);

	List<Footprint> publicfootprintList(Footprint fp);

}
