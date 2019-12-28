//
// Created by Rani Lasma Uli on 27/12/2019.
//

#include <jni.h>
extern "C" JNIEXPORT jint JNICALL Java_id_ac_ui_cs_mobileprogramming_ranilasmauli_clouthing_NativeConnection_calculate
        (JNIEnv * env, jobject obj, jint price, jint discount) {
            return price - (price*discount/100);
}
