package com.lijian.ffmpeg;

/**
 * Created by lijian on 2017/4/21.
 */

public class Test {
    static {
        System.loadLibrary("test-lib");
    }
    public String test() {
        return testFFMPEG();
    }

    native String testFFMPEG();
}

