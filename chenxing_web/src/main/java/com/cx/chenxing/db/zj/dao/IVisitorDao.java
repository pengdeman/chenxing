package com.cx.chenxing.db.zj.dao;

import com.cx.chenxing.db.zj.entity.Visitor;
import java.util.List;

public interface IVisitorDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Visitor record);

    Visitor selectByPrimaryKey(Integer id);

	List<Visitor> queryListNumByBvisitorId(Visitor record);

	List<Visitor> queryListByBvisitorId(Visitor record);
}