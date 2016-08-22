package xyz.enhorse.commons.parameters;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         22.08.2016
 */
public class Tess {

    static String inToString(final InputStream input) throws Exception {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = input.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        return result.toString("UTF-8");
    }


    public static void main(String[] args) throws Exception {
        String eng = "abcdefghijklmnopqrstuvwxyz";
        String rus = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
        StringBuilder mega = new StringBuilder();

        for (int i = 0; i < 80; i++) {
            mega.append(i % 2 == 0 ? eng : rus);
        }

        InputStream stream = new ByteArrayInputStream(mega.toString().getBytes(StandardCharsets.UTF_8));
        System.out.println(inToString(stream));
    }

}
