package com.twogether.backend.api.config.jpa;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.Year;


@Converter(autoApply = true)
public class YearConverter implements AttributeConverter<Year, Integer> {

	@Override
	public Integer convertToDatabaseColumn(Year value) {
		return value == null ? null : Integer.valueOf(value.getValue());
	}

	@Override
	public Year convertToEntityAttribute(Integer value) {
		return value == null ? null : Year.of(value.intValue());
	}
}