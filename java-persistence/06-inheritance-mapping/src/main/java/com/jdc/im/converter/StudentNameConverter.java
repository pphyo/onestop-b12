package com.jdc.im.converter;

import com.jdc.im.entity.StudentName;

import jakarta.persistence.AttributeConverter;

public class StudentNameConverter implements AttributeConverter<StudentName, String> {

	// Mr.Pyae Phyo
	@Override
	public StudentName convertToEntityAttribute(String dbData) {
		if(null != dbData && dbData.isBlank()) {
			var name = new StudentName();
			name.setSur(dbData.substring(0, dbData.indexOf('.')));
			name.setFirstName(dbData.substring(dbData.indexOf('.') + 1, dbData.indexOf(' ')));
			name.setLastName(dbData.substring(dbData.indexOf(' ') + 1, dbData.length()));
			return name;
		}
		return null;
	}

	@Override
	public String convertToDatabaseColumn(StudentName attribute) {
		return String.format("%s.%s %s", 
				attribute.getSur(), 
				attribute.getFirstName(), 
				attribute.getLastName());
	}

}
