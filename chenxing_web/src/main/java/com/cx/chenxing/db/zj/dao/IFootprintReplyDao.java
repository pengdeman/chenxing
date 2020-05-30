package com.cx.chenxing.db.zj.dao;

import com.cx.chenxing.db.zj.entity.FootprintReply;
import java.util.List;

public interface IFootprintReplyDao {
	
	List<FootprintReply> replyList(Integer articleId);
	
	int insert(FootprintReply record);
	
	int deleteByPrimaryKey(Integer id);
	
	FootprintReply selectByPrimaryKey(Integer id);

	List<FootprintReply> replyLists(FootprintReply reply);

	void updateSelective(FootprintReply footprintReply);
}
