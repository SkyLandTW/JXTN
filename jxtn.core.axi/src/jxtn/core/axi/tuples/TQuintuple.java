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

package jxtn.core.axi.tuples;

/**
 * 支援五個子項目的Tuple
 *
 * @author AqD
 * @param <V1> 第一子項目型態
 * @param <V2> 第二子項目型態
 * @param <V3> 第三子項目型態
 * @param <V4> 第四子項目型態
 * @param <V5> 第五子項目型態
 */
@SuppressWarnings("serial")
public class TQuintuple<V1, V2, V3, V4, V5> extends BaseTuple<TQuintuple<V1, V2, V3, V4, V5>>
{
    private final V1 v1;
    private final V2 v2;
    private final V3 v3;
    private final V4 v4;
    private final V5 v5;

    public TQuintuple(V1 v1, V2 v2, V3 v3, V4 v4, V5 v5)
    {
        super(v1, v2, v3, v4, v5);
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        this.v4 = v4;
        this.v5 = v5;
    }

    public V1 getItem1()
    {
        return this.v1;
    }

    public V2 getItem2()
    {
        return this.v2;
    }

    public V3 getItem3()
    {
        return this.v3;
    }

    public V4 getItem4()
    {
        return this.v4;
    }

    public V5 getItem5()
    {
        return this.v5;
    }
}
