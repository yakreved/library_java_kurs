/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kursovic;

import com.ezware.oxbow.swingbits.table.filter.TableRowFilterSupport;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.enterprise.inject.Default;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableModel;

/**
 *
 * @author 01
 */
public class NewJFrame extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    ResultSetTableModel model;

    public NewJFrame() {
        initComponents();
        //jTable3.setCellEditor(new TableCellEditor());
        DBConnection.connect();
        ResultSet rs = DBConnection.executeQuery("select * from books");
        model = new ResultSetTableModel(rs);
        model.setColumnNames(new String[]{"Название книги","id","Авторы","Год","Издатель","Шифр"});
        jTable3.setModel(model);
        TableRowFilterSupport.forTable(jTable3).searchable(true).apply();

        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Execute when button is pressed
                model.addRow();
            }
        });
        
        DeleteBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Execute when button is pressed
                model.deleteRow();
            }
        });

        BooksBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setTitle("Книги");
                ResultSet rs = DBConnection.executeQuery("select * from books");
                model = new ResultSetTableModel(rs);
                model.setColumnNames(new String[]{"Название книги","id","Авторы","Год","Издатель","Шифр"});
                jTable3.setModel(model);
            }
        });

        ReadersBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Execute when button is pressed
                setTitle("Читатели");
                ResultSet rs = DBConnection.executeQuery("select * from readers");
                model = new ResultSetTableModel(rs);
                model.setColumnNameLabel("Фамилия Имя Отчество", 1);
                model.setColumnNameLabel("Паспорт", 2);
                model.setColumnNameLabel("Дата рождения", 3);
                model.setColumnNameLabel("Телефон", 4);
                model.setColumnNameLabel("Образование", 5);
                model.setColumnNameLabel("Учёная степень", 6);
                model.setColumnNameLabel("Зал", 7);
                jTable3.setModel(model);
                

                ResultSet rs2 = DBConnection.executeQuery("select id,name from rooms");
                jTable3.getColumnModel().getColumn(7).setCellEditor(MyJComboCell.generate(rs2,"id","name"));
                jTable3.getColumnModel().getColumn(7).setCellRenderer(new MyCellRenderer(rs2,"id","name"));
            }
        });

        RoomsBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setTitle("Залы");
                ResultSet rs = DBConnection.executeQuery("select * from rooms");
                model = new ResultSetTableModel(rs);
                model.setColumnNames(new String[]{"id", "Номер", "Название", "Вместимость"});
                jTable3.setModel(model);
            }
        });
        
        RoomBooksBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setTitle("Книги в залах");
                ResultSet rs = DBConnection.executeQuery("select * from test.room_book");
                model = new ResultSetTableModel(rs);
                model.setColumnNames(new String[]{"Название книги", "Название зала","Колличество книг","id"});
                jTable3.setModel(model);
                
                ResultSet rs3 = DBConnection.executeQuery("select id,name from test.books");
                jTable3.getColumnModel().getColumn(0).setCellEditor(MyJComboCell.generate(rs3,"id","name"));
                jTable3.getColumnModel().getColumn(0).setCellRenderer(new MyCellRenderer(rs3,"id","name"));
                
                ResultSet rs2 = DBConnection.executeQuery("select id,name from rooms");
                jTable3.getColumnModel().getColumn(1).setCellEditor(MyJComboCell.generate(rs2,"id","name"));
                jTable3.getColumnModel().getColumn(1).setCellRenderer(new MyCellRenderer(rs2,"id","name"));
                
            }
        });
                
        ReaderBooksBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setTitle("Книги у читателей");
                ResultSet rs = DBConnection.executeQuery("select * from test.reader_book");
                model = new ResultSetTableModel(rs);
                model.setColumnNames(new String[]{"Имя читателя", "Название книги","Дата выдачи","Дата возврата"});
                jTable3.setModel(model);
                
                ResultSet rs3 = DBConnection.executeQuery("select id,name from test.books");
                jTable3.getColumnModel().getColumn(0).setCellEditor(MyJComboCell.generate(rs3,"id","name"));
                jTable3.getColumnModel().getColumn(0).setCellRenderer(new MyCellRenderer(rs3,"id","name"));
                
                ResultSet rs2 = DBConnection.executeQuery("select id,name from readers");
                jTable3.getColumnModel().getColumn(1).setCellEditor(MyJComboCell.generate(rs2,"id","name"));
                jTable3.getColumnModel().getColumn(1).setCellRenderer(new MyCellRenderer(rs2,"id","name"));
                
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        DeleteBtn = new javax.swing.JButton();
        ReadersBtn = new javax.swing.JButton();
        RoomsBtn = new javax.swing.JButton();
        BooksBtn = new javax.swing.JButton();
        RoomBooksBtn = new javax.swing.JButton();
        ReaderBooksBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Книги");

        jTable3.setAutoCreateRowSorter(true);
        jTable3.setColumnSelectionAllowed(true);
        jScrollPane3.setViewportView(jTable3);

        jButton1.setText("Добавить");

        DeleteBtn.setText("Удалить");

        ReadersBtn.setText("Читатели");

        RoomsBtn.setText("Залы");

        BooksBtn.setText("Книги");

        RoomBooksBtn.setText("Книги в залах");

        ReaderBooksBtn.setText("Книги у читателей");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1006, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ReadersBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RoomsBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BooksBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RoomBooksBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ReaderBooksBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(DeleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(DeleteBtn)
                    .addComponent(ReadersBtn)
                    .addComponent(RoomsBtn)
                    .addComponent(BooksBtn)
                    .addComponent(RoomBooksBtn)
                    .addComponent(ReaderBooksBtn))
                .addContainerGap())
        );

        DeleteBtn.getAccessibleContext().setAccessibleName("DeleteBtn");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BooksBtn;
    private javax.swing.JButton DeleteBtn;
    private javax.swing.JButton ReaderBooksBtn;
    private javax.swing.JButton ReadersBtn;
    private javax.swing.JButton RoomBooksBtn;
    private javax.swing.JButton RoomsBtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable3;
    // End of variables declaration//GEN-END:variables
}
