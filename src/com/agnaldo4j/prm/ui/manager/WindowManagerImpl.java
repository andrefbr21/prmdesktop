package com.agnaldo4j.prm.ui.manager;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JDialog;

public class WindowManagerImpl implements WindowManager {
    
    private Map<DialogInstance, JDialog> windows;
    
    public WindowManagerImpl() {
        this.windows = new HashMap<DialogInstance, JDialog>();
    }
    
    public void addDialogInstance(DialogInstance dialogInstance, JDialog dialog) {
        this.windows.put(dialogInstance, dialog);
    }
    
    public JDialog getDialogInstance(DialogInstance dialogInstance) {
        return this.windows.get(dialogInstance);
    }
    
}
