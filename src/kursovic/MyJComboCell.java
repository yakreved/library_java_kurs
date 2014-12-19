/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kursovic;

import java.awt.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author 01
 */
public class MyJComboCell 
{
    public static DefaultCellEditor generate(ResultSet rs, String key, String value)
    {
            JComboBox combo = generateCombo(rs,key,value);
            combo.setRenderer(new MyListRenderer());
            return new DefaultCellEditor(combo);
    }
    
       /* public static DefaultCellEditor generateRenderer(ResultSet rs, String key, String value)
    {
            JComboBox combo = generateCombo(rs,key,value);
            combo.setRenderer(new MyCellRenderer());
            return new DefaultCellEditor(combo);
    }*/
    
    public static JComboBox generateCombo(ResultSet rs, String key, String value)
    {
        try {
            DefaultComboBoxModel model = new DefaultComboBoxModel()
            {
                @Override
                public Object getSelectedItem()
                {
                    if(super.getSelectedItem() instanceof Pair)
                        return ((Pair<Object,Object>)super.getSelectedItem()).getKey().toString();
                    else return super.getSelectedItem();
                }
            };
            while (rs.next())
            {
                int id=rs.getInt(key);
                String category=rs.getString(value);
                Pair<Object,Object> itemData = new Pair<Object,Object>(id, category);
                model.addElement(itemData);
            }
            return new JComboBox(model);
        
        } catch (SQLException ex) {
            Logger.getLogger(MyJComboCell.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

class MyListRenderer extends JLabel implements ListCellRenderer 
{
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) 
    {
        if(value instanceof Pair)
        {
            Pair<Object,Object> itemData = (Pair<Object,Object>)value;
            setText((String)itemData.getValue());
        }
        else setText((String)value);

        return this;
    }

 }

class MyCellRenderer extends DefaultTableCellRenderer {

    ArrayList<Pair<Object,Object>> array = new ArrayList<Pair<Object,Object>>();
    public MyCellRenderer(ResultSet rs, String key, String value)
    {
        super();
        try {
            rs.beforeFirst();
            while (rs.next())
            {
                int id=rs.getInt(key);
                String category=rs.getString(value);
                Pair<Object,Object> itemData = new Pair<Object,Object>(id, category);
                array.add(itemData);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MyCellRenderer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    @Override
    public Component getTableCellRendererComponent(JTable table,Object value,
                                                   boolean isSelected,boolean hasFocus,
                                                   int row,int column){

        for(Pair<Object,Object> t : array)
        {
            if(value !=null &&(int)value==(int)t.getKey())
            {
               System.out.println("Render key found");
               setText((String)t.getValue());
               return this;
            }
        }
        System.out.println("Render key not found. value: "+value);
        setText((String)array.get(1).getValue());
        return this;
    }
}