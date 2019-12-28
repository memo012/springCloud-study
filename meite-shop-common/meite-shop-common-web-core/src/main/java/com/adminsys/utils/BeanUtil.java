package com.adminsys.utils;

import org.springframework.beans.BeanUtils;

/**
 * @author memo012
 *  Vo 转 Dto
 * @param <Vo>
 * @param <Dto>
 */
public class BeanUtil<Vo, Dto> {

	/**
	 * vo 转换为Dto 工具类
	 * 
	 * @param voEntity
	 * @param dtoClass
	 * @return
	 */
	public static <Dto> Dto voToDto(Object voEntity, Class<Dto> dtoClass) {
		// 判断VoSF是否为空!
		if (voEntity == null) {
			return null;
		}
		// 判断DtoClass 是否为空
		if (dtoClass == null) {
			return null;
		}
		try {
			Dto newInstance = dtoClass.newInstance();
			BeanUtils.copyProperties(voEntity, newInstance);
			// vo转换Dto
			return newInstance;
		} catch (Exception e) {
			return null;
		}
	}

	// 后面集合类型带封装
}
