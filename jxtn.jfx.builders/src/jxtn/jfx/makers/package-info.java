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

/**
 * JavaFX Makers，取代官方已廢棄的同名類別
 * <ul>
 * <li>相容問題：無法直接建立Makers物件（Eclipse編譯器bug），需透過{@link jxtn.jfx.makers.JFX}建立</li>
 * <li>相容問題：除缺少建構子外，包含所有原Makers內的所有方法且功能一致</li>
 * <li>額外功能：提供{@link javafx.scene.control.LabelMaker#bindText}及{@link javafx.scene.control.LabelMaker#bindBidirectionalText}等方法做屬性的繫節</li>
 * <li>實作差異：Makers類別由<i>javafx.ftl</i>配合FMPP/FreeMarker產生，非官方透過Annotation的方式</li>
 * </ul>
 * @author AqD
 */

package jxtn.jfx.makers;