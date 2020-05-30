package com.cx.chenxing.db.zj.dao;

import com.cx.chenxing.db.zj.entity.Wz;
import java.util.List;

public interface IWzDao {

	int insert(Wz record);

	List<Wz> wzList();

	List<Wz> wzListbymail(Wz lg);
}
