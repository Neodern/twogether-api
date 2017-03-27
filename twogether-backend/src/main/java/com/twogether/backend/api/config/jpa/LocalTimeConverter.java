package com.twogether.backend.api.config.jpa;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Time;
import java.time.LocalTime;


@Converter(autoApply = true)
public class LocalTimeConverter implements AttributeConverter<LocalTime, Time> {

	@Override
	public Time convertToDatabaseColumn(LocalTime value) {
		return value == null ? null : Time.valueOf(value);
	}

	@Override
	public LocalTime convertToEntityAttribute(Time value) {
		return value == null ? null : value.toLocalTime();
	}
}