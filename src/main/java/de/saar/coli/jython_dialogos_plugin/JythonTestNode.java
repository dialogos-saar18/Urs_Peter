/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saar.coli.jython_dialogos_plugin;

import com.clt.dialogos.jython_plugin.JythonNodeAdapter;


/**
 *
 * @author koller
 */
public class JythonTestNode extends JythonNodeAdapter {

    public JythonTestNode() {
        super("jython.test_node", "JythonTestNode");
    }

    public static String getNodeTypeName(Class<?> c) {
        return "Jython Test";
    }
}
