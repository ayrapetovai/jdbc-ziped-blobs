package org.example.util;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

public class FormatUtils {
    public static String formatByteSize(long sizeInBytes) {
        if (-1000 < sizeInBytes && sizeInBytes < 1000) {
            return sizeInBytes + "B";
        }
        CharacterIterator ci = new StringCharacterIterator("kMGTPE");
        while (sizeInBytes <= -999_950 || sizeInBytes >= 999_950) {
            sizeInBytes /= 1000;
            ci.next();
        }
        return String.format("%.1f%cB", sizeInBytes / 1000.0, ci.current());
    }
}
