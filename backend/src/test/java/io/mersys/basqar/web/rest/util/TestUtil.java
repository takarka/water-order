package io.mersys.basqar.web.rest.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * Utility class for testing REST controllers.
 */
public class TestUtil {

    /**
     * MediaType for JSON UTF8
     */
    // public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
    // MediaType.APPLICATION_JSON.getType(),
    // MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);

    public static final MediaType API_VERSION1_JSON_UTF8 = new MediaType("application", "json", StandardCharsets.UTF_8);


    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        final JavaTimeModule module = new JavaTimeModule();
        mapper.registerModule(module);

        return mapper.writeValueAsBytes(object);
    }


    public static byte[] createByteArray(int size, String data) {
        final byte[] byteArray = new byte[size];
        for (int i = 0; i < size; i++) {
            byteArray[i] = Byte.parseByte(data, 2);
        }
        return byteArray;
    }

    /*
     * A matcher that tests that the examined string represents the same instant as
     * the reference datetime.
     */
    public static class ZonedDateTimeMatcher extends TypeSafeDiagnosingMatcher<String> {

        private final ZonedDateTime date;

        public ZonedDateTimeMatcher(ZonedDateTime date) {
            this.date = date;
        }

        @Override
        protected boolean matchesSafely(String item, Description mismatchDescription) {
            try {
                if (!date.isEqual(ZonedDateTime.parse(item))) {
                    mismatchDescription.appendText("was ").appendValue(item);
                    return false;
                }
                return true;
            } catch (final DateTimeParseException e) {
                mismatchDescription.appendText("was ").appendValue(item)
                        .appendText(", which could not be parsed as a ZonedDateTime");
                return false;
            }

        }

        @Override
        public void describeTo(Description description) {
            description.appendText("a String representing the same Instant as ").appendValue(date);
        }
    }


    public static ZonedDateTimeMatcher sameInstant(ZonedDateTime date) {
        return new ZonedDateTimeMatcher(date);
    }


    public static FormattingConversionService createFormattingConversionService() {
        final DefaultFormattingConversionService dfcs = new DefaultFormattingConversionService();
        final DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
        registrar.setUseIsoFormat(true);
        registrar.registerFormatters(dfcs);
        return dfcs;
    }
}
