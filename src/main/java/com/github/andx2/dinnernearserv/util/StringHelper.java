package com.github.andx2.dinnernearserv.util;

import java.util.Random;

/**
 * Created by savos on 04.09.2016.
 */
public class StringHelper {

    private static Random rnd = new Random();

    public static String randStr() {
        int length = 15;
        CharSequence characters = "A Java String persisted as an array of bytes (byte[]) with the SQL type VARBINARY." +
                " Many databases are Unicode compliant (MySQL/Postgres) but some are not (SQLite). To store strings with" +
                " accents or other special characters, you may have to encode them as an array of bytes using this type." +
                " By default the Unicode Charset is used to convert the string to bytes and back again. You can use the " +
                "format field in DatabaseField to specify a custom character-set to use instead for the field. Comparison " +
                "and ordering of this type may not be possible depending on the database type.";
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(rnd.nextInt(characters.length()));
        }
        return new String(text);

    }
}
