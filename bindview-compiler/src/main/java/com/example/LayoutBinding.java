package com.example;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.tools.Diagnostic;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by husongzhen on 17/11/10.
 * <p>
 * <p>
 * 读取xml
 * <p>
 * merge
 * veiwstub
 */

public class LayoutBinding {


    private org.dom4j.Element root;
    private List<ViewModel> viewModels = new ArrayList<>();
    private Messager messager;
    private final String layoutName;


    public LayoutBinding(Messager messager, Element element) {
        this.messager = messager;
        ExecutableElement executableElement = (ExecutableElement) element;
        LayoutId layoutId = element.getAnnotation(LayoutId.class);
        layoutName = layoutId.value();
        String path = layoutId.path();
        File file = new File(getLayoutPath(layoutName, path));
        String apath = file.getAbsolutePath();
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            org.w3c.dom.Document document = builder.parse(new File(apath));
            org.w3c.dom.Element rootElement = document.getDocumentElement();
            NodeList nodes = rootElement.getChildNodes();
            childParse(nodes);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void childParse(NodeList nodes) {
        if (nodes.getLength() == 0) {
            return;
        }
        int size = nodes.getLength();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < size; i++) {
            Node note = nodes.item(i);
            String name = note.getNodeName();
            if (name.equals("#text")) {
                continue;
            }
            stringBuffer.append("layoutName = " + name);
            NamedNodeMap map = note.getAttributes();
            if (map != null) {
                Node item = map.getNamedItem("android:id");
                if (item != null) {
                    String id = item.getNodeValue();
                    id = id.replace("@+id/", "");
                    stringBuffer.append(", id = " + id);
                    viewModels.add(new ViewModel(name, id));
                }
            }
            childParse(note.getChildNodes());
        }
        messager.printMessage(Diagnostic.Kind.WARNING, viewModels.toString());
    }

    public String getLayoutName() {
        return layoutName;
    }

    public List<ViewModel> getViewModels() {
        return viewModels;
    }

    private String getLayoutPath(String name, String path) {
        return path + name + ".xml";
    }
}
