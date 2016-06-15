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

package jxtn.core.fmpp;

import java.io.StringWriter;
import java.io.Writer;
import java.util.AbstractList;
import java.util.List;
import java.util.Objects;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

final class XML {

    public static List<Node> asList(NodeList nodeList) {
        return new NodeListList(nodeList);
    }

    public static String toText(Node node) throws TransformerException {
        StringWriter writer = new StringWriter();
        toText(node, writer);
        return writer.toString();
    }

    public static void toText(Node node, Writer writer) throws TransformerException {
        Objects.requireNonNull(writer);
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        StreamResult result = new StreamResult(writer);
        DOMSource source = new DOMSource(node);
        transformer.transform(source, result);
    }

    private static final class NodeListList extends AbstractList<Node> {
        private final NodeList nodeList;

        public NodeListList(NodeList nodeList) {
            this.nodeList = Objects.requireNonNull(nodeList);
        }

        @Override
        public Node get(int index) {
            return this.nodeList.item(index);
        }

        @Override
        public int size() {
            return this.nodeList.getLength();
        }
    }

    private XML() {
    }
}
