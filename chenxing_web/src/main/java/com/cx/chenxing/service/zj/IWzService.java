package com.cx.chenxing.service.zj;

import com.cx.chenxing.db.zj.entity.Wz;
import java.util.List;

public interface IWzService {

	public void insert(Wz lg);

	public List<Wz> wzList();

	public List<Wz> wzListbymail(Wz lg);

}
