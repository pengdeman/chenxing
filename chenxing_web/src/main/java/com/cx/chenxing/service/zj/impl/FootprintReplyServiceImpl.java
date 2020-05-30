package com.cx.chenxing.service.zj.impl;

import com.cx.chenxing.db.zj.dao.IFootprintReplyDao;
import com.cx.chenxing.db.zj.entity.FootprintReply;
import com.cx.chenxing.service.zj.IFootprintReplyService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class FootprintReplyServiceImpl implements IFootprintReplyService {
	@Resource  
    private IFootprintReplyDao footprintDao;
	
	@Override
	public int insert(FootprintReply reply) {
		return footprintDao.insert(reply);
	}

	@Override
	public void deleteByPrimaryKey(int id) {
		this.footprintDao.deleteByPrimaryKey(id);
	}

	@Override
	public List<FootprintReply> replyList(Integer articleId) {
		return this.footprintDao.replyList(articleId);
	}

	@Override
	public FootprintReply selectByPrimaryKey(int id) {
		return this.footprintDao.selectByPrimaryKey(id);
	}

	@Override
	public List<FootprintReply> replyLists(FootprintReply reply) {
		return this.footprintDao.replyLists(reply);
	}

	@Override
	public void updateSelective(FootprintReply footprintReply) {
		this.footprintDao.updateSelective(footprintReply);
	}

}
