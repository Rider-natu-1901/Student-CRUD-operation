package com.project.student.util;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class StudentUtil {

	private static final ModelMapper modelMapper = new ModelMapper();

	private StudentUtil() {
	}

	public static <S, D> D map(final S source, Class<D> destinationType) {
		return modelMapper.map(source, destinationType);
	}

	public static <S, D> D map(final S source, D destination) {
		modelMapper.map(source, destination);
		return destination;
	}

}
