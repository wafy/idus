package com.homework.idus.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class CustomLocalDateTimeSerializer extends StdSerializer<LocalDateTime> {

  protected CustomLocalDateTimeSerializer(final Class<LocalDateTime> t) {
    super(t);
  }

  protected CustomLocalDateTimeSerializer() {
    this(null);
  }

  @Override
  public void serialize(final LocalDateTime value,
                        final JsonGenerator gen,
                        final SerializerProvider provider) throws IOException {
    gen.writeString(
        DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(
            ZonedDateTime.of(value, ZoneId.systemDefault())
        )
    );
  }
}
