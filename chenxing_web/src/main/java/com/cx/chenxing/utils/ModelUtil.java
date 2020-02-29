package com.cx.chenxing.utils;

import com.alibaba.fastjson.TypeReference;
import com.cx.chenxing.utils.mybatisutils.Page;
import org.springframework.beans.BeanUtils;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ModelUtil {
    /**
     * List 转 泛型
     *
     * @param pojoList
     * @param typeReference
     * @return
     */
    public static <R> List<R> changeGeneric(List<?> pojoList, TypeReference<List<R>> typeReference) {
        List<R> result = new ArrayList<R>();
        String json = JsonUtil.toJson(pojoList);
        result = JsonUtil.parseJson(json, typeReference);
        return result;
    }

    /**
     * 拷贝对象
     *
     * @throws Exception
     */
    public static <S, T> T copyObject(S source, Class<T> targetClass) {
        if (source == null) return null;
        try {
            T target = targetClass.newInstance();
            BeanUtils.copyProperties(source, target);
            return target;
        } catch (Exception e) {
            new Exception("Target Class must not be nul");
        }
        return null;
    }

    /**
     * 拷贝数组
     *
     * @param source
     * @param targetEleClass
     * @return
     * @throws Exception
     */
    public static <S, T> T[] copyArray(S[] source, Class<T> targetEleClass) throws Exception {
        if (source == null) return null;
        if (targetEleClass == null) throw new Exception("Target Element Class must not be nul");

        @SuppressWarnings("unchecked") T[] target = (T[]) Array.newInstance(targetEleClass, source.length);
        for (int i = 0; i < source.length; i++) {
            T t = targetEleClass.newInstance();
            BeanUtils.copyProperties(source[i], t);
            target[i] = t;
        }

        return target;
    }

    /**
     * 拷贝集合
     *
     * @throws Exception
     */
    public static <S, T> Collection<T> copyCollection(Collection<S> source, Class<T> targetEleClass) throws Exception {
        if (source == null) return null;
        if (targetEleClass == null) throw new Exception("Target Element Class must not be nul");

        @SuppressWarnings("unchecked") Collection<T> target = source.getClass().newInstance();
        for (S s : source) {
            T t = targetEleClass.newInstance();
            BeanUtils.copyProperties(s, t);
            target.add(t);
        }

        return target;
    }
    /**
     * 拷贝集合
     *
     * @throws Exception
     */
    public static <S, T> List<T> copyList(List<S> source, Class<T> targetEleClass) throws Exception {
    	if (source == null) return null;
    	if (targetEleClass == null) throw new Exception("Target Element Class must not be nul");
    	
    	@SuppressWarnings("unchecked") List<T> target = source.getClass().newInstance();
    	for (S s : source) {
    		T t = targetEleClass.newInstance();
    		BeanUtils.copyProperties(s, t);
    		target.add(t);
    	}
    	
    	return target;
    }

    /**
     * 拷贝map
     *
     * @param source
     * @param targetEleTKClass
     * @param targetEleTVClass
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static <SK, SV, TK, TV> Map<TK, TV> copyMap(Map<SK, SV> source, Class<TK> targetEleTKClass, Class<TV> targetEleTVClass) throws Exception {
        if (source == null) return null;
        if (targetEleTKClass == null || targetEleTVClass == null)
            throw new Exception("Target Element Class must not be nul");

        Map<TK, TV> target = source.getClass().newInstance();
        for (Entry<SK, SV> s : source.entrySet()) {
            TK tk = null;
            if (BeanUtils.isSimpleValueType(targetEleTKClass)) {
                tk = (TK) s.getKey();
            } else {
                tk = targetEleTKClass.newInstance();
                BeanUtils.copyProperties(s.getKey(), targetEleTKClass.newInstance());
            }

            TV tv = null;
            if (BeanUtils.isSimpleValueType(targetEleTVClass)) {
                tv = (TV) s.getValue();
            } else {
                tv = targetEleTVClass.newInstance();
                BeanUtils.copyProperties(s.getValue(), targetEleTVClass.newInstance());
            }

            target.put(tk, tv);
        }

        return target;
    }

    /**
     * 拷贝分页对象
     *
     * @param source
     * @param targetEleClass
     * @return
     * @throws Exception
     */
    public static <S, T> Page<T> copyPage(Page<S> source, Class<T> targetEleClass) {
        if (source == null) return null;
        try {
            Page<T> page = new Page<T>(source.getPageSize(), source.getPageNumber(), source.getTotalCount());
            if (source.getResult() != null) {
                List<T> result = new ArrayList<T>();
                for (S s : source.getResult()) {
                    T t = targetEleClass.newInstance();
                    BeanUtils.copyProperties(s, t);
                    result.add(t);
                }
                page.setResult(result);
            }
            return page;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    /**
     * 拷贝对象
     *
     * @throws Exception
     */
    public static <S, T> T copyObj(S source, Class<T> targetClass) {
        if (source == null) return null;
        try {
            T target = targetClass.newInstance();
            BeanUtil.copyProperties(source, target);
            return target;
        } catch (Exception e) {
        	e.printStackTrace();
            new Exception("Target Class must not be nul");
        }
        return null;
    }
    
    /**
     * 拷贝集合
     *
     * @throws Exception
     */
    public static <S, T> List<T> copyListTwo(List<S> source, Class<T> targetEleClass) throws Exception {
    	if (source == null) return null;
    	if (targetEleClass == null) throw new Exception("Target Element Class must not be nul");
    	
    	@SuppressWarnings("unchecked") List<T> target = source.getClass().newInstance();
    	for (S s : source) {
    		T t = targetEleClass.newInstance();
    		BeanUtil.copyProperties(s, t);
    		target.add(t);
    	}
    	
    	return target;
    }
    
}
