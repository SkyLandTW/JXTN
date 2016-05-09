/*
 * This is free and unencumbered software released into the public domain.
 *
 * Anyone is free to copy, modify, publish, use, compile, sell, or
 * distribute this software, either in source code form or as a compiled
 * binary, for any purpose, commercial or non-commercial, and by any
 * means.
 *
 * In jurisdictions that recognize copyright laws, the author or authors
 * of this software dedicate any and all copyright interest in the
 * software to the public domain. We make this dedication for the benefit
 * of the public at large and to the detriment of our heirs and
 * successors. We intend this dedication to be an overt act of
 * relinquishment in perpetuity of all present and future rights to this
 * software under copyright law.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 *
 * For more information, please refer to <http://unlicense.org/>
 */

#include <jni.h>

extern __thread int jxtn_core_unix_errno;

/**
 * Resolve the data address from the given Java object and offset
 * <p>
 * Parameters follow the same convention as {@link sun.misc.Unsafe#copyMemory}. For regular Java objects, the caller
 * should pass the object itself and an offset calculated from {@link sun.misc.Unsafe#arrayBaseOffset} and index. For
 * direct buffers, pass NULL to {@code base} and the direct address to {@code offset}.
 * </p>
 *
 * @param base Java object, could be NULL of {@code offset} is the absolute address
 * @param offset offset in {@code base}, could be zero
 * @return data address
 * @see hotspot/src/share/vm/prims/unsafe.cpp, Unsafe_CopyMemory2
 */
static inline void* resolve(jobject base, jlong offset) {
    // hotspot/src/share/vm/runtime/jniHandles.hpp, JNIHandles::resolve (from "jobject" to "oop")
    if (base == NULL) {
        return (void*) offset;
    }
    void* base_ptr = *((void**) base);
    // hotspot/src/share/vm/prims/unsafe.cpp, index_oop_from_field_offset_long (from "oop" to data address)
    return base_ptr + offset;
}

/**
 * Resolve the data address from the given Java byte array
 *
 * @param base Java byte array, could be NULL
 * @return data address
 * @see hotspot/src/share/vm/prims/unsafe.cpp, Unsafe_CopyMemory2
 */
static inline void* resolveBA(jbyteArray base) {
    // hotspot/src/share/vm/runtime/jniHandles.hpp, JNIHandles::resolve (from "jobject" to "oop")
    if (base == NULL) {
        return NULL;
    }
    void* base_ptr = *((void**) base);
    // hotspot/src/share/vm/prims/unsafe.cpp, index_oop_from_field_offset_long (from "oop" to data address)
    return base_ptr + 16;
}
