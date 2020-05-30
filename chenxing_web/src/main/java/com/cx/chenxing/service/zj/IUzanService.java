package com.cx.chenxing.service.zj;

import com.cx.chenxing.db.zj.entity.Uzan;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Transactional
public interface IUzanService {

	public void insert(Uzan lg);

	public List<Uzan> queryzanlist(Uzan lg);  

}
