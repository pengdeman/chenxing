package com.cx.chenxing.utils.mybatisutils;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<E, Q, PK extends Serializable> {

    int deleteByPrimaryKey(long id);

    int insert(E e);

    int insertSelective(E e);

    E selectByPrimaryKey(long id);

    int updateByPrimaryKeySelective(E e);

    int updateByPrimaryKey(E e);

    List<E> query(Q q);

}