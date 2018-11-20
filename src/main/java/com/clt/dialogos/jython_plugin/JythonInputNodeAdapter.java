/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clt.dialogos.jython_plugin;

import com.clt.diamant.Device;
import com.clt.diamant.InputCenter;
import com.clt.diamant.graph.nodes.AbstractInputNode;
import com.clt.script.exp.Pattern;
import com.clt.script.exp.patterns.VarPattern;
import com.clt.speech.recognition.LanguageName;
import com.clt.speech.recognition.MatchResult;
import com.clt.speech.recognition.RecognitionExecutor;
import com.clt.srgf.Grammar;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.TimeoutException;
import javax.sound.sampled.AudioFormat;
import javax.swing.JLayeredPane;

/**
 * Converts a Jython implementation of an {@link AbstractInputNode} into
 * a Java implementation. The module and class name of the Jython class are
 * given as arguments in the constructor; the Jython class is expected to
 * be derived from the base class {@link AbstractInputNode}.<p>
 * 
 * The Jython class must implement all abstract methods of {@link AbstractInputNode}.
 * In addition, it may implement {@link AbstractInputNode#recognizeInBackground(com.clt.srgf.Grammar, com.clt.diamant.InputCenter, com.clt.script.exp.patterns.VarPattern, float) }
 * or {@link AbstractInputNode#graphicallyRecognize(javax.swing.JLayeredPane, com.clt.srgf.Grammar, com.clt.script.exp.Pattern[], long, float, boolean) }
 * (or both). These method implementations are used if they are present;
 * otherwise the default implementations from {@link AbstractInputNode}
 * are used.<p>
 * 
 * It is usually not a good idea to use this class in a DialogOS plugin directly,
 * because the plugin wants to register classes with parameter-less constructors,
 * and you probably want to set the name and icon for your node to something
 * meaningful. Therefore, you should derive a concrete class from {@link JythonInputNodeAdapter},
 * set the Jython module and class in its parameterless constructor,
 * and provide your own implementation of {@link Node#getNodeTypeName(java.lang.Class) }.
 * 
 * @author koller
 */
public class JythonInputNodeAdapter extends AbstractInputNode {

    private static JythonObjectFactory factory = JythonObjectFactory.getSingleton();
    private AbstractInputNode delegate;

    public JythonInputNodeAdapter(String module, String clazz) {
        delegate = factory.create(module, clazz, AbstractInputNode.class);
    }

    /* If Jython class implements a Recognize method, delegate it to the Jython class;
       otherwise, use the default implementation.
     */
    @Override
    public void recognizeInBackground(Grammar recGrammar, InputCenter input, VarPattern backgroundPattern, float confidenceThreshold) {
        Class delegateClass = delegate.getClass();
        try {
            Method methodInDelegate = delegateClass.getDeclaredMethod("recognizeInBackground", new Class[]{Grammar.class, InputCenter.class, VarPattern.class, float.class});
            methodInDelegate.invoke(delegate, recGrammar, input, backgroundPattern, confidenceThreshold);
        } catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException  ex) {
            // Delegate class does not implement this method => use default implementation
            super.recognizeInBackground(recGrammar, input, backgroundPattern, confidenceThreshold);
        } catch(InvocationTargetException ex) {
            throw (RuntimeException) ex.getCause();
        }
    }

    @Override
    public MatchResult graphicallyRecognize(JLayeredPane layer, Grammar recGrammar, Pattern[] patterns, long timeout, float confidenceThreshold, boolean interactiveTest) throws TimeoutException {
        Class delegateClass = delegate.getClass();
        try {
            Method methodInDelegate = delegateClass.getDeclaredMethod("graphicallyRecognize", new Class[]{JLayeredPane.class, Grammar.class, Pattern[].class, long.class, float.class, boolean.class});
            return (MatchResult) methodInDelegate.invoke(delegate, layer, recGrammar, patterns, timeout, confidenceThreshold, interactiveTest);
        } catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException  ex) {
            // Delegate class does not implement this method => use default implementation
            return super.graphicallyRecognize(layer, recGrammar, patterns, timeout, confidenceThreshold, interactiveTest); //To change body of generated methods, choose Tools | Templates.
        } catch(InvocationTargetException ex) {
            throw (RuntimeException) ex.getCause();
        }
    }

    /* delegate all abstract methods to Jython class */
    @Override
    public AudioFormat getAudioFormat() {
        return delegate.getAudioFormat();
    }

    @Override
    public RecognitionExecutor createRecognitionExecutor(Grammar grmr) {
        return delegate.createRecognitionExecutor(grmr);
    }

    @Override
    public Device getDevice() {
        return delegate.getDevice();
    }

    @Override
    public List<LanguageName> getAvailableLanguages() {
        return delegate.getAvailableLanguages();
    }

    @Override
    public LanguageName getDefaultLanguage() {
        return delegate.getDefaultLanguage();
    }
}
