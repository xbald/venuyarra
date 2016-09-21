package com.venuyarra.aqa.ui;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Created by NIKOLAI on 20.09.2016.
 */

public class TableModel extends AbstractTableModel {
    List<String> itemList;

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    public int getColumnCount() {
        return 1;
    }

    public String getColumnName(int column) {
        return "Items";
    }

    public void setItemList(List<String> itemList) {
        this.itemList = itemList;
    }

    public int getRowCount() {
        return itemList.size();
    }

    public void setValueAt(Object value,
                           int rowIndex, int columnIndex) {
        itemList.set(rowIndex, value.toString());
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        return itemList.get(rowIndex);
    }
}