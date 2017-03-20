package com.mcp1993.elantra.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/3/1 0001.
 */

public class FileUtils {
    //当前时间
    public static String getCharacterAndNumber() {
        String rel="";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        Date curDate = new Date(System.currentTimeMillis());
        rel = formatter.format(curDate);
        return rel;
    }

    /**
     * 把 Bitmap 保存在SD卡路径后，返回file 类型的 uri
     *
     * @param bm
     * @return
     */
    public static Uri saveBitmap(Bitmap bm) {

        File tmDir = new File(Environment.getExternalStorageDirectory()
                + "/Elantra");
        if (!tmDir.exists()) {
            tmDir.mkdir();
        }
        File img = new File(tmDir.getAbsolutePath() + FileUtils.getCharacterAndNumber()+ "picImg.png");
        try {
            FileOutputStream fos = new FileOutputStream(img);
            bm.compress(Bitmap.CompressFormat.PNG, 85, fos);
            fos.flush();
            fos.close();
            return Uri.fromFile(img);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 把 content 类型的uri 转换为 file 类型的 uri （其实，就是通过content类型的uri
     * 解释为bitmap，然后保存在sd卡中，通过保存路径来获得file类型额uri）
     *
     * @param uri
     * @return
     */
    public static Uri converUri(Context context,Uri uri) {
        InputStream is = null;
        try {
            is =context.getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            is.close();
            //将bitmap转换为Uri
            Uri uri1 = Uri.parse(MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, null, null));
//            return FileUtils.saveBitmap(bitmap);
            return uri1;
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
}
