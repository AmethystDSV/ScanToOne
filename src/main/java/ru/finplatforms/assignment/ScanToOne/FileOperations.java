package ru.finplatforms.assignment.ScanToOne;

import java.io.*;

public class FileOperations {

    public static String readFile(File f) throws Exception {
        String str;
        try (FileInputStream fis = new FileInputStream(f);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()
        ) {
            byte[] byteBuffer = new byte[512 * 8];
            int cnt;
            while ((cnt = fis.read(byteBuffer)) >= 0) {
                baos.write(byteBuffer, 0, cnt);
            }
            byte[] bytes = baos.toByteArray();
            str = new String(bytes);
        }
        return str;
    }

    public static void writeToFile(File f, String str) throws Exception {

        try( FileOutputStream fos = new FileOutputStream(f,true)) {
            fos.write(str.getBytes());
        }

    }

}
