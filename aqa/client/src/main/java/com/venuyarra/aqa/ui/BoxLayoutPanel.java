package com.venuyarra.aqa.ui;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.Component;
import java.util.Iterator;
import java.util.List;

/**
 * Created by NIKOLAI on 20.09.2016.
 */
public class BoxLayoutPanel extends JPanel {
    /**
     * We can't use "components" as the property name,
     * because it conflicts with an existing property
     * on the Component superclass.
     */
    private List<Component> panelComponents;
    private int axis;

    public void setAxis(int axis) {
        this.axis = axis;
    }

    public void setPanelComponents(List<Component> panelComponents) {
        this.panelComponents = panelComponents;
    }

    public void init() {
        setLayout(new BoxLayout(this, axis));
        panelComponents.forEach(this::add);
    }
}