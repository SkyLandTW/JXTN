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

package java.util.tuple;

/**
 * 支援九個子項目的Tuple。
 *
 * @author AqD
 * @param <V1> 第一子項目型態
 * @param <V2> 第二子項目型態
 * @param <V3> 第三子項目型態
 * @param <V4> 第四子項目型態
 * @param <V5> 第五子項目型態
 * @param <V6> 第六子項目型態
 * @param <V7> 第七子項目型態
 * @param <V8> 第八子項目型態
 * @param <V9> 第九子項目型態
 */
@SuppressWarnings("serial")
public final class TNonuple<V1, V2, V3, V4, V5, V6, V7, V8, V9>
        extends BaseTuple<TNonuple<V1, V2, V3, V4, V5, V6, V7, V8, V9>> {
    private final V1 v1;
    private final V2 v2;
    private final V3 v3;
    private final V4 v4;
    private final V5 v5;
    private final V6 v6;
    private final V7 v7;
    private final V8 v8;
    private final V9 v9;

    public TNonuple(V1 v1, V2 v2, V3 v3, V4 v4, V5 v5, V6 v6, V7 v7, V8 v8, V9 v9) {
        super(v1, v2, v3, v4, v5, v6, v7, v8, v9);
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        this.v4 = v4;
        this.v5 = v5;
        this.v6 = v6;
        this.v7 = v7;
        this.v8 = v8;
        this.v9 = v9;
    }

    public V1 getItem1() {
        return this.v1;
    }

    public V2 getItem2() {
        return this.v2;
    }

    public V3 getItem3() {
        return this.v3;
    }

    public V4 getItem4() {
        return this.v4;
    }

    public V5 getItem5() {
        return this.v5;
    }

    public V6 getItem6() {
        return this.v6;
    }

    public V7 getItem7() {
        return this.v7;
    }

    public V8 getItem8() {
        return this.v8;
    }

    public V9 getItem9() {
        return this.v9;
    }
}
