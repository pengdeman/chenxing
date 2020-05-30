package com.cx.chenxing.service.zj;

import com.cx.chenxing.db.zj.entity.FootprintReply;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Transactional
public interface IFootprintReplyService {
	
	public int insert(FootprintReply lg);

	void deleteByPrimaryKey(int parseInt);

	public List<FootprintReply> replyList(Integer articleId);

	public FootprintReply selectByPrimaryKey(int parseInt);

	public List<FootprintReply> replyLists(FootprintReply reply);

	public void updateSelective(FootprintReply footprintReply);
}
