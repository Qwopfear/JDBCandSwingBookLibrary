package com.uPanamarou.UI;

import javax.swing.table.AbstractTableModel;
import java.io.*;
import java.util.ArrayList;

public  class BooksTableModel extends AbstractTableModel implements Serializable {


    private int columnCount = 5;
    private ArrayList<String []> dataArray;

    public BooksTableModel(){
        dataArray = new ArrayList<>();
        for (int i = 0; i < dataArray.size(); i++) {
            dataArray.add(new String[getColumnCount()]);
        }
    }
    @Override
    public int getRowCount() {
        return dataArray.size();
    }

    @Override
    public int getColumnCount() {

        return columnCount;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String[] rows = dataArray.get(rowIndex);
        return rows[columnIndex];
    }

    public Object getValueAtRow(int rowIndex){
        String[] rows = dataArray.get(rowIndex);
        return rows;
    }

    public void addDate(String [] row){
        String []rowTable = new String[getColumnCount()];
        rowTable = row;
        dataArray.add(rowTable);
        setTableMode();
    }

    @Override
    public String getColumnName(int columnIndex){
        switch (columnIndex){
            case  0 -> {return "id";}
            case  1 -> {return "Title";}
            case  2 -> {return "Author";}
            case  3 -> {return "Pages";}
            case  4 -> {return "Theme";}
        }
        return "";
    }

    void setTableMode(){
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("score.bin"))){
            outputStream.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
