/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kursovic;

import javax.swing.table.*;
import javax.swing.event.*;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import com.groupon.novie.internal.engine.schema.SqlTable;
import java.util.Random;

class ResultSetTableModel extends AbstractTableModel
{  
/**
   Constructs the table model.
   @param aResultSet the result set to display.
*/
    ResultSet rs;
    String[] columnNames;
public ResultSetTableModel(ResultSet aResultSet)
{  
   rs = aResultSet;
   
   try
   {  
      rsmd = rs.getMetaData();
      columnNames = new String[rsmd.getColumnCount()+1];
   }
   catch (SQLException e)
   {  
      e.printStackTrace();
   }
}

public ResultSetTableModel(String sql)
{  
    rs = DBConnection.executeQuery(sql);
       try
   {  
      rsmd = rs.getMetaData();
      columnNames = new String[rsmd.getColumnCount()+1];
   }
   catch (SQLException e)
   {  
      e.printStackTrace();
   }
}

public void setColumnNameLabel(String columnName,int c_i)
{
    columnNames[c_i]= columnName;
}
public void setColumnNames(String[] names)
{
    for(int i=0; i<columnNames.length && i< names.length;i++)
        columnNames[i]=names[i];
}

public void addRow()
{
    try {
        rs.moveToInsertRow();
        try
        {
            rs.updateObject("name", "Новая запись"+new Random().nextInt());
        }catch(Exception e){}
                try
        {
            rs.updateObject("id", new Random().nextInt());
        }catch(Exception e){}
        rs.insertRow();
        rs.beforeFirst();
        fireTableRowsInserted(getRowCount()-1,getRowCount()-1);
    } catch (SQLException ex) {
        Logger.getLogger(ResultSetTableModel.class.getName()).log(Level.SEVERE, null, ex);
    }
}


public String getTableName()
{
        try {
            rsmd.getTableName(0);
        } catch (SQLException ex) {
            Logger.getLogger(ResultSetTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Error";
}

public String getColumnName(int c)
{  
    if(columnNames[c]!=null)
        return columnNames[c];
   try
   {  
      return rsmd.getColumnName(c + 1);
   }
   catch (SQLException e)
   {  
      e.printStackTrace();
      return "";
   }
}

public boolean isCellEditable(int row, int col){
      return true;
}

public int getColumnCount()
{  
   try
   {  
      return rsmd.getColumnCount();
   }
   catch (SQLException e)
   {  
      e.printStackTrace();
      return 0;
   }
}

public Object getValueAt(int r, int c)
{  
   try
   {  
      rs.absolute(r + 1);
      return rs.getObject(c + 1);
   }
   catch(SQLException e)
   {  
      e.printStackTrace();
      return null;
   }
}

public void setValueAt(Object aValue, int rowIndex, int columnIndex){
      try
         {  
            rs.absolute(rowIndex + 1);
            System.out.println("I got here... row: "+ rowIndex + ", " + columnIndex);
            rs.updateString(columnIndex+1, (String)aValue);
            rs.updateRow();
         }
         catch(SQLException e)
         {  
            e.printStackTrace();
         }
}

    public int getRowCount()
    {  
       try
       {  
          rs.last();
          return rs.getRow();
       }
       catch(SQLException e)
       {  
          e.printStackTrace();
          return 0;
       }
    }

    private ResultSetMetaData rsmd;

    void deleteRow() 
    {
        try {
            rs.deleteRow();
        } catch (SQLException ex) {
            Logger.getLogger(ResultSetTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}