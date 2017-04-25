package com.lijian.ffmpeg;

import android.util.Log;

import com.lijian.ffmpeg.util.SdcardUtil;

/**
 * Created by lijian on 2017/4/21.
 */

public class Test {
    private static final String TAG = "FFMPEG";

    static {
        System.loadLibrary("test-lib");
    }

    public int test(Object surface) {
        String path = SdcardUtil.getInnerSdCard() + "/test.mp4";
        Log.d(TAG, "test path=" + path);
        return testFFMPEG(surface,path);
    }

    native int testFFMPEG(Object surface, String path);
}

