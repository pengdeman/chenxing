package com.cx.chenxing.db.zj.dao;

import com.cx.chenxing.db.zj.entity.Uzan;
import java.util.List;

public interface IUzanDao {
	
    int deleteByPrimaryKey(Integer id);

    int insert(Uzan record);

    Uzan selectByPrimaryKey(Integer id);

	List<Uzan> queryzanlist(Uzan lg);

}