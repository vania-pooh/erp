package org.meridor.erp.io;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class StreamUtils {

    public static final String LIST_SEPARATOR = ", ";

    public static String commaSeparated(Stream<String> stream) {
        return stream.collect(Collectors.joining(LIST_SEPARATOR));
    }

    public static String commaSeparated(String[] strings) {
        return commaSeparated(Arrays.stream(strings));
    }

}
