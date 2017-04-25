/*
 * 版    权：深圳市快播科技有限公司
 * 描    述: 
 * 创建人: HuQiming
 * 创建时间: 2013-8-9
 * 
 */
package com.lijian.ffmpeg.util;

import android.content.Context;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.text.TextUtils;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author HuQiming
 * @date 2013-8-9
 */
public class SdcardUtil {
    private static final String TAG = "SdcardUtil";

    /**
     * 检查内置的sd卡是否可用
     *
     * @return
     */
    public static boolean checkSDPath() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获得手机内置存储的目录
     *
     * @return
     */
    public static String getInnerSdCard() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    /**
     * 获得手机外置存储SD卡目录
     * 这个方法比较可靠
     *
     * @return
     */
    public static String getExtendSdCard(Context mContext) {
        StorageManager mStorageManager = (StorageManager) mContext.getSystemService(Context.STORAGE_SERVICE);
        Class<?> storageVolumeClazz = null;
        try {
            storageVolumeClazz = Class.forName("android.os.storage.StorageVolume");
            Method getVolumeList = mStorageManager.getClass().getMethod("getVolumeList");
            Method getPath = storageVolumeClazz.getMethod("getPath");
            Method isRemovable = storageVolumeClazz.getMethod("isRemovable");
            Object result = getVolumeList.invoke(mStorageManager);
            final int length = Array.getLength(result);
            for (int i = 0; i < length; i++) {
                Object storageVolumeElement = Array.get(result, i);
                String path = (String) getPath.invoke(storageVolumeElement);
                boolean removable = (Boolean) isRemovable.invoke(storageVolumeElement);
                if (removable) {
                    return path;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return System.getenv("SECONDARY_STORAGE");
    }

    /**
     * 获得App在手机外置存储SD卡拥有操作权限的包名目录
     *
     * @return
     */
    public static String getAppSDCardDir(Context context) {
        if (context != null) {
            String sd = getExtendSdCard(context);
            if (!TextUtils.isEmpty(sd)) {
                String appPath = sd + "/Android/data/" + context.getPackageName();
                File file = new File(appPath);
                return file.canWrite() ? file.getAbsolutePath() : "";
            }
        }
        return "";
    }


}
