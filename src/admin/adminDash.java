/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import Rooms.checkOut;
import Rooms.manageRooms;
import canillasapp.loginForm;
import configgg.Session;
import configgg.dbconnect;
import java.awt.Color;
import java.awt.Image;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.ProcessBuilder.Redirect.to;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.scene.paint.Color.color;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import static jdk.nashorn.internal.objects.NativeJava.to;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author SCC-COLLEGE
 */
public class adminDash extends javax.swing.JFrame {

    /**
     * Creates new form adminDash
     */
    public adminDash() {
        initComponents();
        displayData();
        time();
        date();
        
        
        srchf.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
            filterTable(srchf.getText(), tableAd); 
            
        }
        
        });
        
        
      
    }
    
    private void date(){
        
        Date d = new Date();
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
         
        String dt = sdf.format(d);
        txtdate.setText(dt);
             
    }
    
        Timer t;
        SimpleDateFormat st;
    
    private void time(){

        t = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                Date dt = new Date();
                st = new SimpleDateFormat("hh:mm:ss a");
                
                String tm = st.format(dt);
                txttime.setText(tm);
                
            }
        });
        
        t.start();
        
    }
    
     public void displayData(){
        try{
            dbconnect dbc = new dbconnect();
            ResultSet rs = dbc.getData("SELECT t.transID, t.roomID, t.u_id, t.name, t.Contact, t.Check_In, t.Check_Out,t.totalPayment,  t.status "
                    + "\n FROM tbl_transaction t\n JOIN tbl_room r ON t.roomID = r.roomID;");
            tableAd.setModel(DbUtils.resultSetToTableModel(rs));
             rs.close();
        }catch(SQLException ex){
            System.out.println("Errors: "+ex.getMessage());
        
        }
        
    
    }
    
   public static int getHeightFromWidth(String imagePath, int desiredWidth) {
        try {
            // Read the image file
            File imageFile = new File(imagePath);
            BufferedImage image = ImageIO.read(imageFile);
            
            // Get the original width and height of the image
            int originalWidth = image.getWidth();
            int originalHeight = image.getHeight();
            
            // Calculate the new height based on the desired width and the aspect ratio
            int newHeight = (int) ((double) desiredWidth / originalWidth * originalHeight);
            
            return newHeight;
        } catch (IOException ex) {
            System.out.println("No image found!");
        }
        
        return -1;
    }

    
    public  ImageIcon ResizeImage(String ImagePath, byte[] pic, JLabel label) {
        ImageIcon MyImage = null;
            if(ImagePath !=null){
                MyImage = new ImageIcon(ImagePath);
            }else{
                MyImage = new ImageIcon(pic);
            }

        int newHeight = getHeightFromWidth(ImagePath, label.getWidth());

        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(label.getWidth(), newHeight, Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }
    
    
            private void filterTable(String searchText, JTable table) 
            {
                TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
                table.setRowSorter(sorter);

                if (searchText.trim().length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
                }

                applyHighlightRenderer(searchText, table);
            }   

           private void applyHighlightRenderer(String searchText, JTable table) 
           {
                for (int i = 0; i < table.getColumnCount(); i++) 
                {
                    table.getColumnModel().getColumn(i).setCellRenderer(new HighlightRenderer(searchText));
                }
            }

            class HighlightRenderer extends DefaultTableCellRenderer 
            {
                private String searchText;

                public HighlightRenderer(String searchText) 
                {
                    this.searchText = searchText;
                }

                @Override
                protected void setValue(Object value) {
                    if (value != null && searchText != null && !searchText.isEmpty()) 
                    {
                        String stringValue = value.toString();
                        String lcSearchText = searchText.toLowerCase();
                        String lcStringValue = stringValue.toLowerCase();

                        int startIdx = lcStringValue.indexOf(lcSearchText);
                        if (startIdx >= 0) 
                        {
                            String highlightedText = "<html>" + stringValue.substring(0, startIdx) +
                                    "<span style='background: yellow;'>" +
                                    stringValue.substring(startIdx, startIdx + searchText.length()) +
                                    "</span>" + stringValue.substring(startIdx + searchText.length()) + "</html>";
                            super.setValue(highlightedText);
                            return;
                        }
                    }
                    super.setValue(value);
                }
            }
            
      
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        acc_name1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        acc_name = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        img = new javax.swing.JLabel();
        p6 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        p3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        p4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        p5 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txttime = new javax.swing.JLabel();
        txtdate = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        srch = new javax.swing.JLabel();
        srchf = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        dateChoose = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableAd = new javax.swing.JTable();

        acc_name1.setFont(new java.awt.Font("Lucida Console", 1, 24)); // NOI18N
        acc_name1.setForeground(new java.awt.Color(255, 255, 255));
        acc_name1.setText("ADMIN");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(80, 103, 120));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("SimSun-ExtB", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ADMINISTRATION");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setText("BACK");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1067, 11, 73, 30));

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, 50));

        jPanel3.setBackground(new java.awt.Color(34, 96, 137));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        acc_name.setFont(new java.awt.Font("Lucida Console", 1, 24)); // NOI18N
        acc_name.setForeground(new java.awt.Color(255, 255, 255));
        acc_name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        acc_name.setText("ADMIN");
        jPanel3.add(acc_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 220, 40));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        img.setBackground(new java.awt.Color(255, 255, 255));
        img.setForeground(new java.awt.Color(255, 255, 255));
        img.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(img, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(img, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 240));

        p6.setBackground(new java.awt.Color(0, 204, 0));
        p6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        p6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                p6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                p6MouseExited(evt);
            }
        });

        jLabel16.setBackground(new java.awt.Color(0, 102, 153));
        jLabel16.setFont(new java.awt.Font("Verdana", 1, 20)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("CHECK-IN");

        javax.swing.GroupLayout p6Layout = new javax.swing.GroupLayout(p6);
        p6.setLayout(p6Layout);
        p6Layout.setHorizontalGroup(
            p6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                .addContainerGap())
        );
        p6Layout.setVerticalGroup(
            p6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.add(p6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 200, 50));

        p3.setBackground(new java.awt.Color(102, 0, 0));
        p3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        p3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                p3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                p3MouseExited(evt);
            }
        });

        jLabel11.setBackground(new java.awt.Color(0, 102, 153));
        jLabel11.setFont(new java.awt.Font("Verdana", 1, 20)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("CHECK-OUT");

        javax.swing.GroupLayout p3Layout = new javax.swing.GroupLayout(p3);
        p3.setLayout(p3Layout);
        p3Layout.setHorizontalGroup(
            p3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                .addContainerGap())
        );
        p3Layout.setVerticalGroup(
            p3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.add(p3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 200, 50));

        p4.setBackground(new java.awt.Color(204, 204, 0));
        p4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        p4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                p4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                p4MouseExited(evt);
            }
        });

        jLabel13.setBackground(new java.awt.Color(0, 102, 153));
        jLabel13.setFont(new java.awt.Font("Verdana", 1, 20)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("RESERVATIONS");

        javax.swing.GroupLayout p4Layout = new javax.swing.GroupLayout(p4);
        p4.setLayout(p4Layout);
        p4Layout.setHorizontalGroup(
            p4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                .addContainerGap())
        );
        p4Layout.setVerticalGroup(
            p4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.add(p4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 470, 200, 50));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 240, 580));

        jDesktopPane1.setBackground(new java.awt.Color(255, 255, 255));
        jDesktopPane1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        p5.setBackground(new java.awt.Color(102, 102, 102));
        p5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        p5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                p5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                p5MouseExited(evt);
            }
        });

        jLabel15.setBackground(new java.awt.Color(0, 102, 153));
        jLabel15.setFont(new java.awt.Font("Verdana", 1, 20)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("REFRESH");

        javax.swing.GroupLayout p5Layout = new javax.swing.GroupLayout(p5);
        p5.setLayout(p5Layout);
        p5Layout.setHorizontalGroup(
            p5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 850, Short.MAX_VALUE)
                .addContainerGap())
        );
        p5Layout.setVerticalGroup(
            p5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addContainerGap())
        );

        jDesktopPane1.add(p5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 442, -1, -1));

        txttime.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        txttime.setForeground(new java.awt.Color(255, 255, 255));
        txttime.setText("0");
        jDesktopPane1.add(txttime, new org.netbeans.lib.awtextra.AbsoluteConstraints(248, 11, 183, 28));

        txtdate.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        txtdate.setForeground(new java.awt.Color(255, 255, 255));
        txtdate.setText("0");
        jDesktopPane1.add(txtdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 11, 129, 28));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/gif/icons8-date (1).gif"))); // NOI18N
        jDesktopPane1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 15, -1, -1));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        srch.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        srch.setForeground(new java.awt.Color(204, 204, 204));
        srch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/gif/icons8-search.gif"))); // NOI18N
        srch.setText("Search");
        jPanel9.add(srch, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 101, 40));

        srchf.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        srchf.setBorder(null);
        srchf.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        srchf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                srchfMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                srchfMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                srchfMouseReleased(evt);
            }
        });
        srchf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                srchfActionPerformed(evt);
            }
        });
        srchf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                srchfKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                srchfKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                srchfKeyTyped(evt);
            }
        });
        jPanel9.add(srchf, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 130, -1));

        jDesktopPane1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 30, 210, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/gif/icons8-time (1).gif"))); // NOI18N
        jDesktopPane1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(214, 11, -1, -1));

        dateChoose.setDateFormatString("yyyy-MM-dd");
        jDesktopPane1.add(dateChoose, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 30, 200, 40));

        tableAd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tableAd);

        jDesktopPane1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 85, 870, 351));

        jPanel2.add(jDesktopPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 70, 890, 550));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        Session sess = Session.getInstance();
        dbconnect db = new dbconnect();
        
        if(sess.getUid() == 0)
        {
            
        }
        else
        {
           acc_name.setText(""+sess.getFname());
           
           try{
              ResultSet rs = db.getData("SELECT * FROM tbl_user WHERE u_id = '"+sess.getUid()+"'");
              
              if(rs.next()){
                    img.setIcon(ResizeImage(rs.getString("u_image"), null, img));
               }
              
           }catch(SQLException ex){
               System.out.println(""+ex);
           }
        }
        
    }//GEN-LAST:event_formWindowActivated

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        adminDB adb = new adminDB();
        adb.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1MouseClicked

    private void p4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p4MouseClicked
        reserve rsv = new reserve();
        rsv.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_p4MouseClicked

    private void p4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p4MouseEntered
        p4.setBackground(new Color(255,102,0));
    }//GEN-LAST:event_p4MouseEntered

    private void p4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p4MouseExited
       p4.setBackground(new Color(204,204,0));
    }//GEN-LAST:event_p4MouseExited

    private void p6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p6MouseClicked
       manageRooms mrm = new manageRooms();
       mrm.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_p6MouseClicked

    private void p6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p6MouseEntered
        p6.setBackground(new Color(0,102,0));
    }//GEN-LAST:event_p6MouseEntered

    private void p6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p6MouseExited
        p6.setBackground(new Color(0,204,0));
    }//GEN-LAST:event_p6MouseExited

    private void p5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p5MouseExited
        p5.setBackground(new Color(102,102,102));// TODO add your handling code here:
    }//GEN-LAST:event_p5MouseExited

    private void p5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p5MouseEntered
       p5.setBackground(new Color(204,204,204)); // TODO add your handling code here:
    }//GEN-LAST:event_p5MouseEntered

    private void p5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p5MouseClicked
      DefaultTableModel model = (DefaultTableModel)tableAd.getModel();
        model.setRowCount(0);
        displayData();      
    }//GEN-LAST:event_p5MouseClicked

    private void p3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p3MouseExited
        p3.setBackground(new Color(102,0,0));
    }//GEN-LAST:event_p3MouseExited

    private void p3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p3MouseEntered
        p3.setBackground(new Color(153,0,0));
    }//GEN-LAST:event_p3MouseEntered

    private void p3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p3MouseClicked
    dbconnect db = new dbconnect();
    int row = tableAd.getSelectedRow();

    if (row < 0) {
        JOptionPane.showMessageDialog(null, "Please Select");
    } else {
        TableModel mod = tableAd.getModel();
        try {
            String u_id = mod.getValueAt(row, 2).toString(); // Assuming column index 2 contains u_id

            // Construct the SQL query with proper syntax and concatenation
            String sql = "SELECT t.transID, t.roomID, t.u_id, t.name, t.Contact, r.roomType, r.roomNo, r.r_price, t.Check_In, t.Check_Out, t.totalPayment, t.status, u.u_fname, u.u_image " +
                         "FROM tbl_transaction t " +
                         "JOIN tbl_room r ON t.roomID = r.roomID " +
                         "JOIN tbl_user u ON t.u_id = u.u_id " +
                         "WHERE t.u_id = '" + u_id + "'";

            ResultSet rs = db.getData(sql);

            if (rs.next()) {
                // Extract data from ResultSet and set to corresponding components in checkOut frame
                checkOut cout = new checkOut();
                cout.tid.setText(rs.getString("transID"));
                cout.cnm.setText(rs.getString("name"));
                cout.cno.setText(rs.getString("Contact"));
                cout.cki.setText(rs.getString("Check_In"));
                cout.cko.setText(rs.getString("Check_Out"));
                cout.rid.setText(rs.getString("roomID"));
                cout.rtp.setText(rs.getString("roomType"));
                cout.rno.setText(rs.getString("roomNo"));
                cout.rp.setText(rs.getString("r_price"));
                cout.tprc.setText(rs.getString("totalPayment"));
                cout.tnm.setText(rs.getString("u_fname"));
                cout.tuid.setText(rs.getString("u_id"));
                cout.transid.setText(rs.getString("transID"));

                            // Handle image


                // Check if Check_Out is null before calculating nod (number of days)
                String checkOutDate = rs.getString("Check_Out");
                String checkInDate = rs.getString("Check_In").split(" ")[0]; // Extract date part
                LocalDate checkIn = LocalDate.parse(checkInDate);

                if (checkOutDate != null && !checkOutDate.isEmpty()) {
                    try {
                        LocalDate checkOut;
                        if (checkOutDate.length() == 10) {
                            // If Check_Out is in the format "yyyy-MM-dd"
                            checkOut = LocalDate.parse(checkOutDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        } else {
                            // If Check_Out is in the format "yyyy-MM-dd HH:mm:ss.S"
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
                            LocalDateTime checkOutDateTime = LocalDateTime.parse(checkOutDate, formatter);
                            checkOut = checkOutDateTime.toLocalDate();
                        }
                        long nod = ChronoUnit.DAYS.between(checkIn, checkOut);
                        cout.nod.setText(String.valueOf(nod)); // Set the total number of days (nod)
                    } catch (DateTimeParseException e) {
                        System.err.println("Error parsing Check_Out date: " + checkOutDate);
                        e.printStackTrace();
                        cout.nod.setText("Parsing Error");
                    }
                } else {
                    LocalDate currentDate = LocalDate.now(); // Get current date
                    cout.cko.setText(currentDate.toString()); // Set Check_Out as current date
                    long nod = ChronoUnit.DAYS.between(checkIn, currentDate);
                    cout.nod.setText(String.valueOf(nod)); // Set the total number of days (nod)

                    // Calculate total payment
                    double roomPrice = Double.parseDouble(rs.getString("r_price"));
                    double totalPayment = nod * roomPrice;
                    cout.tprc.setText(String.valueOf(totalPayment));
                }

                cout.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Record not found");
            }

            rs.close(); // Close the ResultSet
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }


   
       
    }//GEN-LAST:event_p3MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void srchfMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_srchfMouseEntered

    }//GEN-LAST:event_srchfMouseEntered

    private void srchfMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_srchfMouseExited

    }//GEN-LAST:event_srchfMouseExited

    private void srchfMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_srchfMouseReleased
        srch.setText("Search");
    }//GEN-LAST:event_srchfMouseReleased

    private void srchfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_srchfActionPerformed
      
    }//GEN-LAST:event_srchfActionPerformed

    private void srchfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_srchfKeyPressed

    }//GEN-LAST:event_srchfKeyPressed

    private void srchfKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_srchfKeyReleased

    }//GEN-LAST:event_srchfKeyReleased

    private void srchfKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_srchfKeyTyped
        srch.setText("");
    }//GEN-LAST:event_srchfKeyTyped

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
            java.util.logging.Logger.getLogger(adminDash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(adminDash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(adminDash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(adminDash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new adminDash().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel acc_name;
    private javax.swing.JLabel acc_name1;
    private com.toedter.calendar.JDateChooser dateChoose;
    private javax.swing.JLabel img;
    private javax.swing.JButton jButton1;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel p3;
    private javax.swing.JPanel p4;
    private javax.swing.JPanel p5;
    private javax.swing.JPanel p6;
    private javax.swing.JLabel srch;
    private javax.swing.JTextField srchf;
    private javax.swing.JTable tableAd;
    private javax.swing.JLabel txtdate;
    private javax.swing.JLabel txttime;
    // End of variables declaration//GEN-END:variables
}
