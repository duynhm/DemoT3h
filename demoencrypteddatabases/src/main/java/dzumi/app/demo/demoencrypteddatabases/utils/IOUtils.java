package dzumi.app.demo.demoencrypteddatabases.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by Dzumi on 3/17/2016.
 */
public class IOUtils {
    public static void encryptFile(String path){

    }

    public static void decryptFile(String pathSrc, String pathDec){
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(pathSrc));
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(pathDec));



            bos.flush();
            bos.close();
            bis.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean delete(String path) {
        File file = new File(path);
        if (file.exists())
            return file.delete();
        return false;
    }
}
