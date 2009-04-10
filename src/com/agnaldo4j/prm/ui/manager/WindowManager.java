package com.agnaldo4j.prm.ui.manager;

import javax.swing.JDialog;

public interface WindowManager {
    public void addDialogInstance(DialogInstance dialogInstance, JDialog dialog);
    public JDialog getDialogInstance(DialogInstance dialogInstance);
}
