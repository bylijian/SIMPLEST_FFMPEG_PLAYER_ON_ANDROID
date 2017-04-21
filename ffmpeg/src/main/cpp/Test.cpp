//
// Created by lijian on 2017/4/21.
//
#include <jni.h>
#include <string>

extern "C" {
#include "libavformat/avformat.h"
};
using namespace std;

extern "C"
jstring
Java_com_lijian_ffmpeg_Test_testFFMPEG(
        JNIEnv *env,
        jobject /* this */) {
    string hello = "Hello from C++ , ";
    av_register_all();
    string s=hello+" end ";
    return env->NewStringUTF(s.c_str());
}

