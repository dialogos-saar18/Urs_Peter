/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saar.coli.jython_dialogos_plugin;

import com.clt.dialogos.jython_plugin.JythonOutputNodeAdapter;

/**
 * A simple output node written in Jython. This output node simply
 * prints the outputs it is sent to the console.
 * 
 * @author koller
 */
public class JythonStdoutNode extends JythonOutputNodeAdapter {
    public JythonStdoutNode() {
        super("jython.output_node", "JythonStdoutOutputNode");
    }
    
    public static String getNodeTypeName(Class<?> c) {
        return "Jython Stdout Output";
    }

}
