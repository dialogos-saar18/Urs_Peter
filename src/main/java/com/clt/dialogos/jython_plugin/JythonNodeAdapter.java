/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clt.dialogos.jython_plugin;

import com.clt.diamant.ExecutionLogger;
import com.clt.diamant.IdMap;
import com.clt.diamant.InputCenter;
import com.clt.diamant.WozInterface;
import com.clt.diamant.graph.Node;
import com.clt.xml.XMLWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import javax.swing.JComponent;

/**
 *
 * @author koller
 */
public abstract class JythonNodeAdapter extends Node {
    protected static JythonObjectFactory factory = JythonObjectFactory.getSingleton();
    protected Node delegate;
    
    public JythonNodeAdapter(String module, String clazz) {
        delegate = factory.create(module, clazz, Node.class, this);
    }

    @Override
    public Node execute(WozInterface wi, InputCenter ic, ExecutionLogger el) {
        return delegate.execute(wi, ic, el);
    }

    @Override
    public JComponent createEditorComponent(Map<String, Object> map) {
        return delegate.createEditorComponent(map);
    }

    @Override
    public void writeVoiceXML(XMLWriter writer, IdMap idmap) throws IOException {
        Class delegateClass = delegate.getClass();
        try {
            Method methodInDelegate = delegateClass.getDeclaredMethod("writeVoiceXML", new Class[]{XMLWriter.class, IdMap.class});
            methodInDelegate.invoke(delegate, writer, idmap);
        } catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException ex) {
            // Delegate class does not implement this method => use default implementation
            // (which is to do nothing)
            
        } catch (InvocationTargetException ex) {
            throw (RuntimeException) ex.getCause();
        }
    }

    @Override
    public String toString() {
        return super.toString() + "\n(wrapper for " + (delegate == null ? "<uninit>" : delegate.toString()) + ")";
    }    
}
