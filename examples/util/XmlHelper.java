package com.homevip.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.ParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 从xps1.0里拷过来的解析xml的工具类
 * 修改了日志输出方式
 * @author mojs
 *
 */
public class XmlHelper {
	Log _Log = LogFactory.getLog(this.getClass());
	
	private static DocumentBuilderFactory _BuilderFactory = DocumentBuilderFactory.newInstance();
	Document m_XmlDoc;

	public static DocumentBuilder getDocumentBuilder()
			throws ParserConfigurationException {
		return _BuilderFactory.newDocumentBuilder();
	}

	public Document getDocument() {
		return this.m_XmlDoc;
	}

	/**
	 * 加载xml文件,并进行UTF-8编码
	 * @param xml
	 * @return
	 */
	public Document load(String xml) {
		try {
			this.m_XmlDoc = null;
			ByteArrayInputStream stream = new ByteArrayInputStream(xml.getBytes("UTF-8"));
			this.m_XmlDoc = getDocumentBuilder().parse(stream);
			return this.m_XmlDoc;
		} catch (Exception e) {
			_Log.error("Load this XML string failed! -->\n" + xml, e);
			return null;
		}
	}

	public Document load(InputStream in) {
		try {
			this.m_XmlDoc = getDocumentBuilder().parse(in);
			return this.m_XmlDoc;
		} catch (Exception e) {
			_Log.error("Load XML in stream failed!", e);
			return null;
		}
	}

	public Node selectChildElement(String path) {
		return selectChildElement(this.m_XmlDoc, path);
	}

	/**
	 * 得到子节点元素
	 * @param node
	 * @param subTagName
	 * @return
	 */
	public static Node getChildElement(Node node, String subTagName) {
		if (node == null)
			return null;
		NodeList children = node.getChildNodes();
		for (int i = 0; i < children.getLength(); ++i) {
			Node child = children.item(i);
			if ((child != null) && (child.getNodeName() != null)) {
				if (!(child.getNodeName().equals(subTagName)))
					continue;
				return child;
			}
		}
		return null;
	}

	/**
	 * 得到节点属性值
	 * @param node
	 * @param subTagName
	 * @return
	 */
	public static String getAttributValue(Node node, String subTagName) {
		node = getChildElement(node, subTagName);
		if (node == null)
			return "";
		node = node.getAttributes().getNamedItem("value");
		if (node == null)
			return "";
		return getElementText(node);
	}

	public static String getAttributText(Node node, String name) {
		NamedNodeMap atts = node.getAttributes();
		if (atts == null)
			return null;
		return getElementText(atts.getNamedItem(name));
	}

	/**
	 * 查找子节点
	 * @param node
	 * @param path
	 * @return
	 */
	public static Node selectChildElement(Node node, String path) {
		String[] ss = path.split("\\/");
		for (int i = 0; (node != null) && (i < ss.length); ++i) {
			if ("..".equals(ss[i])) {
				node = node.getParentNode();
			}
			node = getChildElement(node, ss[i]);
		}
		return node;
	}

	/**
	 * 得到子节点的元素值
	 * @param node
	 * @param subTagName
	 * @return
	 */
	public static String getChildElementText(Node node, String subTagName) {
		node = getChildElement(node, subTagName);
		if (node == null)
			return "";
		String ret = node.getFirstChild().getNodeValue();
		if (ret == null)
			return "";
		return ret;
	}

	/**
	 * 得到元素值
	 * @param node
	 * @return
	 */
	public static String getElementText(Node node) {
		Node fc = node.getFirstChild();
		String val = (fc == null) ? node.getNodeValue() : fc.getNodeValue();
		return ((val == null) ? "" : val);
	}

	public static void escape(String text, StringBuilder buffer) {
		XmlHelper.escapeXML(text, buffer);
	}

	/**
	 * 替换特殊符号
	 * @param text
	 * @param buffer
	 */
	public static void escapeXML(String text, StringBuilder buffer) {
		if ((text == null) || (text.length() == 0))
			return;
		for (int i = 0; i < text.length(); ++i) {
			int ch = text.charAt(i);
			switch (ch) {
			case 62:
				buffer.append("&gt;");
				break;
			case 60:
				buffer.append("&lt;");
				break;
			case 34:
				buffer.append("&quot;");
				break;
			case 38:
				buffer.append("&amp;");
				break;
			default:
				buffer.append(text.charAt(i));
			}
		}
	}

	/**
	 * 将字符串转换成字符流
	 * @param hexString
	 * @return
	 * @throws ParseException
	 */
	public static ByteArrayInputStream decodeHex(String hexString)
			throws ParseException {
		byte[] buf = new byte[hexString.length() / 2];
		if (hexString.length() == 0)
			return new ByteArrayInputStream(buf);
		if (1 == (0x1 & hexString.length()))
			throw new ParseException("无效的编码串", hexString.length());
		int i = 0;
		int j = 0;
		while (i < hexString.length()) {
			if ((hexString.charAt(i) > '/') && (hexString.charAt(i) < ':')) {
				buf[j] = (byte) (0xF0 & hexString.charAt(i) - '0' << 4);
			} else if ((hexString.charAt(i) > '`')
					&& (hexString.charAt(i) < 'g')) {
				buf[j] = (byte) (0xF0 & hexString.charAt(i) - 'W' << 4);
			} else if ((hexString.charAt(i) > '@')
					&& (hexString.charAt(i) < 'G')) {
				buf[j] = (byte) (0xF0 & hexString.charAt(i) - '7' << 4);
			} else {
				throw new ParseException("无效字符", i);
			}

			if ((hexString.charAt(++i) > '/') && (hexString.charAt(i) < ':')) {
				int tmp215_214 = j;
				byte[] tmp215_213 = buf;
				tmp215_213[tmp215_214] = (byte) (tmp215_213[tmp215_214] | 0xF & hexString
						.charAt(i) - '0');
			} else if ((hexString.charAt(i) > '`')
					&& (hexString.charAt(i) < 'g')) {
				int tmp256_255 = j;
				byte[] tmp256_254 = buf;
				tmp256_254[tmp256_255] = (byte) (tmp256_254[tmp256_255] | 0xF & hexString
						.charAt(i) - 'W');
			} else if ((hexString.charAt(i) > '@')
					&& (hexString.charAt(i) < 'G')) {
				int tmp297_296 = j;
				byte[] tmp297_295 = buf;
				tmp297_295[tmp297_296] = (byte) (tmp297_295[tmp297_296] | 0xF & hexString
						.charAt(i) - '7');
			} else {
				throw new ParseException("无效字符", i);
			}
			++i;
			++j;
		}
		return new ByteArrayInputStream(buf);
	}
}