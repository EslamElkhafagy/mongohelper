package com.example.mongodb.annotation;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;

public class DoubleContextualSerializer extends JsonSerializer<Double> implements ContextualSerializer {

    private int precision;

    public DoubleContextualSerializer() {
    }

    public DoubleContextualSerializer(int precision) {
        this.precision = precision;
    }

    @Override
    public void serialize(Double value, JsonGenerator gen, SerializerProvider serializers) throws IOException {

        gen.writeNumber(MathHelper.roundDecimalNumber(value, this.precision));
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) {

        Precision precisionAnnotation = property.getAnnotation(Precision.class);

        if (precisionAnnotation != null) {
            return new DoubleContextualSerializer(precisionAnnotation.precision());
        }

        return this;
    }
}