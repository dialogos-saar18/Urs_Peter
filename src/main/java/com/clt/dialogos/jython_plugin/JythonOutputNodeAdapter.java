/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clt.dialogos.jython_plugin;

import com.clt.diamant.graph.Node;
import com.clt.diamant.graph.nodes.AbstractOutputNode;
import com.clt.speech.SpeechException;
import com.clt.speech.tts.VoiceName;
import java.util.List;
import java.util.Map;

/**
 * Converts a Jython implementation of an {@link AbstractOutputNode} into
 * a Java implementation. The module and class name of the Jython class are
 * given as arguments in the constructor; the Jython class is expected to
 * be derived from the base class {@link AbstractOutputNode}.<p>
 * 
 * It is usually not a good idea to use this class in a DialogOS plugin directly,
 * because the plugin wants to register classes with parameter-less constructors,
 * and you probably want to set the name and icon for your node to something
 * meaningful. Therefore, you should derive a concrete class from {@link JythonOutputNodeAdapter},
 * set the Jython module and class in its parameterless constructor,
 * and provide your own implementation of {@link Node#getNodeTypeName(java.lang.Class) }.
 * 
 * @author koller
 */
public abstract class JythonOutputNodeAdapter extends AbstractOutputNode {
    private static JythonObjectFactory factory = JythonObjectFactory.getSingleton();
    private AbstractOutputNode delegate;
    
    public JythonOutputNodeAdapter(String module, String clazz) {
        delegate = factory.create(module, clazz, AbstractOutputNode.class);
    }
    
    @Override
    public String getResourceString(String string) {
        return delegate.getResourceString(string);
    }

    @Override
    public List<VoiceName> getAvailableVoices() {
        return delegate.getAvailableVoices();
    }

    @Override
    public void speak(String string, Map<String, Object> map) throws SpeechException {
        delegate.speak(string, map);
    }

    @Override
    public void stopSynthesis() {
        delegate.stopSynthesis();
    }

}
