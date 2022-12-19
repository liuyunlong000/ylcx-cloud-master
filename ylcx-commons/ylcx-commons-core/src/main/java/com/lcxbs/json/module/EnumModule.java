package com.lcxbs.json.module;

import com.lcxbs.json.EnumUtil;
import com.lcxbs.json.IEnum;
import com.lcxbs.json.StringUtils;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.json.PackageVersion;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;

/**
 * @author huss
 */
public class EnumModule extends SimpleModule {

    private static final Logger log= LoggerFactory.getLogger(EnumModule.class);

    public EnumModule() {
        super(PackageVersion.VERSION);
        super.addSerializer(IEnum.class, new EnumJsonSerializer());
        // 此处添加Enum序列化才有效
        super.addDeserializer(Enum.class, new EnumJsonDeserializer());

    }

    static class EnumJsonSerializer extends JsonSerializer<IEnum> {

        @Override
        public void serialize(IEnum value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeStartObject();
            log.debug(">>>>>EnumJsonSerializer serialize value:{}", value);
            Map<String, Object> params = value.getParams();

            for (Map.Entry<String, Object> entry : params.entrySet()) {
                String key = entry.getKey();
                Object val = entry.getValue();

                gen.writeFieldName(key);
                if (val instanceof Number) {
                    gen.writeNumber(val.toString());
                } else {
                    gen.writeString(val.toString());
                }

            }

            gen.writeEndObject();
        }
    }

    static class EnumJsonDeserializer extends JsonDeserializer<Enum> {
        @Override
        public Enum deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {

            JsonNode node = p.getCodec().readTree(p);
            Object currentValue = p.getCurrentValue();

            String currentName = p.currentName();
            Class findPropertyType = null;
            try {
                if (currentValue instanceof Collection) {
                    JsonStreamContext parsingContext = p.getParsingContext();

                    JsonStreamContext parent = parsingContext.getParent();
                    Object currentValue3 = parent.getCurrentValue();
                    String currentName3 = parent.getCurrentName();

                    Field listField = FieldUtils.getField(currentValue3.getClass(), currentName3, true);
                    ParameterizedType listGenericType = (ParameterizedType) listField.getGenericType();
                    Type listActualTypeArguments = listGenericType.getActualTypeArguments()[0];
                    findPropertyType = (Class) listActualTypeArguments;

                } else {
                    PropertyDescriptor propDesc = new PropertyDescriptor(currentName, currentValue.getClass());
                    findPropertyType = propDesc.getPropertyType();
                }
            } catch (Exception e) {
                log.error("e:", e);
            }

            String asText = null;
            if (node.getNodeType().equals(JsonNodeType.OBJECT)) {
                JsonNode codeNode = node.get(IEnum.CODE_KEY);
                if (codeNode != null) {
                    asText = codeNode.asText();
                }
            } else {
                asText = node.asText();
            }

            if (StringUtils.isEmpty(asText)) {
                return null;
            }
            log.debug(">>>>>EnumJsonDeserializer deserialize asText:{}, findPropertyType:{}", asText, findPropertyType);
            if (findPropertyType != null && findPropertyType.isEnum()
                    && IEnum.class.isAssignableFrom(findPropertyType)) {
                return (Enum) EnumUtil.codeOf(findPropertyType, asText);
            }
            throw new IllegalArgumentException("value is not enum");

        }
    }
}
