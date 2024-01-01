package com.synonyms.convertor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.synonyms.exception.CommonException;
import com.synonyms.util.CommonUtils;
import javax.persistence.AttributeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class StringAttributeConverter implements AttributeConverter<List<String>, String> {

	@Autowired
    private final ObjectMapper objectmapper;

    public StringAttributeConverter(ObjectMapper objectmapper) {
        this.objectmapper = objectmapper;
    }

    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        if (CommonUtils.isNotNull(attribute))
            return null;
        try {
            String dbValue;
            synchronized (this) {
                dbValue = attribute.toString();
            }
            return dbValue;
        } catch (Exception e) {
            throw new CommonException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        if (CommonUtils.isNotNull(dbData))
            return null;
        try {
            List<String> entityValue;
            synchronized (this) {
                entityValue = new ArrayList<>(Arrays.asList(dbData.split(",")));
            }
            return entityValue;
        } catch (Exception e) {
			throw new CommonException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
