//
// Created by Windows10 on 2021-12-18.
//
#include <com_ekcapaper_ndk_practice_HelloNdkActivity.h>

extern "C" JNIEXPORT jstring JNICALL Java_com_ekcapaper_ndk_practice_HelloNdkActivity_print_1ndk
  (JNIEnv *env, jobject thiz, jstring text) {
    return text;
}

