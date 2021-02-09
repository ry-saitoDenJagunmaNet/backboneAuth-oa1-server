package net.jagunma.backbone.auth.authmanager.infra.web.common;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import net.jagunma.common.values.jackson.filter.ValueObjectPropertyFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();

        FilterProvider filters = new SimpleFilterProvider()
            .addFilter("valueObject", new ValueObjectPropertyFilter());

        builder.filters(filters);

        return builder;
    }
}
