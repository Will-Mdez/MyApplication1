package com.example.myapplication1;

import android.content.Context;
import android.view.Gravity;
import android.widget.ListAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TablaDinamicaArticulos {
    private TableLayout tableLayout;
    private Context context;
    private ArrayList<String[]> data;
    private String[] header;
    private TableRow tableRow;
    private TextView txtCell;
    private int indexC;
    private int indexR;

    public TablaDinamicaArticulos(TableLayout tableLayout, Context context) {
        this.tableLayout = tableLayout;
        this.context =context;
    }

    public void addHeader(String[] header){
        this.header=header;
        createHeader();
    }
    public void addData(ArrayList<String[]> data){
        this.data= data;
        createDataTable();
    }
    private void newRow(){
        tableRow= new TableRow(context);
    }

    private void newCell(){
        txtCell=new TextView(context);
        txtCell.setGravity(Gravity.CENTER);
        txtCell.setTextSize(25);

    }
    private void createHeader(){
        indexC=0;
        newRow();
        while(indexC< header.length){
            newCell();
            txtCell.setText(header[indexC++]);
            tableRow.addView(txtCell,newTablerowParams());
        }
        tableLayout.addView(tableRow);
    }

    private void createDataTable(){
        String info;
        for(indexR=0;indexR<= header.length;indexR++){
            newRow();
            for(indexC=0;indexC<= header.length;indexC++){
                newCell();
                String[] row= data.get(indexR-1);
                info=(indexC<row.length)?row[indexC]:"";
                txtCell.setText(info);
                tableRow.addView(txtCell,newTablerowParams());

            }
            tableLayout.addView(tableRow);
        }
    }

    private TableRow.LayoutParams newTablerowParams(){
        TableRow.LayoutParams params = new TableRow.LayoutParams();
        params.setMargins(1,1,1,1);
        params.weight=1;
        return params;
    }

     public void addItems(String[] item){
         String info;
         data.add(item);
         indexC=0;
         newRow();
         while(indexC< header.length){
             newCell();
             info=(indexC< item.length)?item[indexC++]:"";
             txtCell.setText(info);
             tableRow.addView(txtCell,newTablerowParams());
         }
         tableLayout.addView(tableRow,data.size()-1);
     }
}
