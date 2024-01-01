package com.synonyms.beans;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.synonyms.util.CommonUtils;
import org.modelmapper.AbstractCondition;
import org.modelmapper.Condition;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT)
                .setPropertyCondition(isStringBlank).setFieldMatchingEnabled(true);
        return modelMapper;
    }

    private final Condition<?, ?> isStringBlank = new AbstractCondition<>() {
        @SuppressWarnings({"unchecked", "rawtypes"})
        @Override
        public boolean applies(MappingContext<Object, Object> context) {
            if (context.getSource() instanceof String) {
                return CommonUtils.isNotEmpty((String) context.getSource());
            } else if (context.getSource() instanceof List) {
                return CommonUtils.isNotEmpty((List) context.getSource());
            } else if (context.getSource() instanceof Map) {
                return CommonUtils.isNotEmpty((Map) context.getSource());
            } else {
                return context.getSource() != null;
            }
        }
    };
}
