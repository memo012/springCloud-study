package com.adminsys.mapper;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import java.util.List;

/**
 * @author memo012
 * 集合do转变成dto
 */
public class MapperUtils {
	static MapperFactory mapperFactory;
	static {
		mapperFactory = new DefaultMapperFactory.Builder().build();
	}

	public static <S, D> List<D> mapAsList(Iterable<S> source, Class<D> destinationClass) {
		return mapperFactory.getMapperFacade().mapAsList(source, destinationClass);
	}
}
