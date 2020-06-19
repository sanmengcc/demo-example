package com.sanmengcc.example.util;

import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

/**
 * description: FileUtil <br>
 * date: 2020/6/17 10:47 <br>
 *
 * @author: huhd <br>
 * desc:
 */
public class FileUtil {

    private final static String BOUNDARY_STR = "------------7da2e536604c8";

    public static MultipartEntityBuilder getMultipartEntity(File file) throws IOException {
        MultipartEntityBuilder meb = MultipartEntityBuilder.create();
        meb.setBoundary(BOUNDARY_STR).setCharset(Charset.forName("utf-8")).setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        meb.addBinaryBody("media", file, ContentType.APPLICATION_OCTET_STREAM, file.getName());
        return meb;
    }

    public static MultipartEntityBuilder getMultipartEntity(byte[] data,String fileName) throws IOException {
        MultipartEntityBuilder meb = MultipartEntityBuilder.create();
        meb.setBoundary(BOUNDARY_STR).setCharset(Charset.forName("utf-8")).setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        meb.addBinaryBody("media", data, ContentType.APPLICATION_OCTET_STREAM, fileName);
        return meb;
    }

    public static MultipartEntityBuilder getMultipartEntity(InputStream data,String fileName) throws IOException {
        MultipartEntityBuilder meb = MultipartEntityBuilder.create();
        meb.setBoundary(BOUNDARY_STR).setCharset(Charset.forName("utf-8")).setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        meb.addBinaryBody("media", data, ContentType.APPLICATION_OCTET_STREAM, fileName);
        return meb;
    }

    public static byte[] getImageStreamByte(String urlAddr) {
        InputStream in = null;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            URL url = new URL(urlAddr);
            URLConnection connection = url.openConnection();
            in = connection.getInputStream();

            int len = -1;
            byte[] data = new byte[1024];
            while ((len = in.read(data)) != -1) {
                out.write(data, 0, len);
            }
        } catch (MalformedURLException me) {

        } catch (IOException ie) {

        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {

                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                }
            }
        }
        return out.toByteArray();
    }

}

