package com.ritvik.fileupload.utilities;


import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class UploadFile {
    public static final String UPLOAD_DIR;

    static {
        try {
            UPLOAD_DIR = new ClassPathResource("static/files/").getFile().getAbsolutePath();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public UploadFile() throws IOException {}

    public static Boolean uploadFile(MultipartFile multipartFile) throws IOException {
        boolean uploaded= false;
        try{
            InputStream stream = multipartFile.getInputStream();
            FileOutputStream fileOutputStream = new FileOutputStream(UPLOAD_DIR+ File.separator+multipartFile.getOriginalFilename());
            byte data[] = new byte[stream.available()];
            stream.read(data);
            fileOutputStream.write(data);
            fileOutputStream.flush();
            fileOutputStream.close();
            return true;
        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }
        return uploaded;
    }
}
