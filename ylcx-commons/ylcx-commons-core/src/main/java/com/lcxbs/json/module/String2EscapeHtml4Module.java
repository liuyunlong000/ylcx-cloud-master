package com.lcxbs.json.module;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.PackageVersion;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * string xss过滤
 * 
 * @author huss
 */
public class String2EscapeHtml4Module extends SimpleModule {

    private static final Logger log= LoggerFactory.getLogger(String2EscapeHtml4Module.class);

    public String2EscapeHtml4Module() {
        super(PackageVersion.VERSION);
        super.addDeserializer(String.class, new String2EscapeHtml4Deserializer());
    }

    static class String2EscapeHtml4Deserializer extends JsonDeserializer<String> {

        @Override
        public String deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {

            String value = p.getValueAsString();
            log.debug(">>>>> String2EscapeHtml4Deserializer. name：{}, value：{}", p.currentName(), value);
            if (value != null) {
                return StringEscapeUtils.escapeHtml4(value);
            }
            return "";

        }
    }
}
