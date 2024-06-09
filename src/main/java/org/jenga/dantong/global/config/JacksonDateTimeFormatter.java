package org.jenga.dantong.global.config;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import java.time.format.DateTimeFormatter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Component;

@Component
public class JacksonDateTimeFormatter implements JacksonFormatConfigurer {

    public static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd";
    public static final String TIME_FORMAT_PATTERN = "HH:mm:ss";

    public static final DateTimeFormatter DATE_FORMAT =
        DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN);
    public static final DateTimeFormatter TIME_FORMAT =
        DateTimeFormatter.ofPattern(TIME_FORMAT_PATTERN);

    @Override
    public void configure(Jackson2ObjectMapperBuilder builder) {

        builder.serializers(new LocalDateSerializer(DATE_FORMAT));
        builder.serializers(new LocalTimeSerializer(TIME_FORMAT));

        builder.deserializers(new LocalDateDeserializer(DATE_FORMAT));
        builder.deserializers(new LocalTimeDeserializer(TIME_FORMAT));
    }
}
