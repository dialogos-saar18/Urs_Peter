/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saar.coli.jython_dialogos_plugin;

import com.clt.dialogos.jython_plugin.JythonInputNodeAdapter;

/**
 *
 * @author koller
 */
public class JythonStdinNode extends JythonInputNodeAdapter {
    public JythonStdinNode() {
        super("jython.input_node", "JythonStdinInputNode");
    }
    
    public static String getNodeTypeName(Class<?> c) {
        return "Jython Stdin Input";
    }
}
