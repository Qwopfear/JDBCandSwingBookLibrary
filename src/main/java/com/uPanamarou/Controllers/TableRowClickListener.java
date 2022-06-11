package com.uPanamarou.Controllers;

import com.uPanamarou.Service.BookModel;
import com.uPanamarou.UI.BooksTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class TableRowClickListener extends MouseAdapter {

    private JTable targetTable;

    public TableRowClickListener(JTable targetTable) {
        this.targetTable = targetTable;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int rowIndex = targetTable.rowAtPoint(new Point(e.getX(),e.getY()));
        BooksTableModel model = (BooksTableModel) targetTable.getModel();
        BookModel.setBook((String[]) model.getValueAtRow(rowIndex));
        System.out.println("x : " + e.getX() + ", y : " + e.getY());
    }
}
