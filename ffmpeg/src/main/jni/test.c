#include <jni.h>

JNIEXPORT jstring JNICALL
Java_com_lijian_ffmpeg_Test_testFFMPEG(JNIEnv *env, jobject instance) {

    // TODO


    return (*env)->NewStringUTF(env, returnValue);
}