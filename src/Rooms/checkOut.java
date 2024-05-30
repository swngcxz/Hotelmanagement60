/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rooms;

import admin.adminDash;
import configgg.Session;
import configgg.dbconnect;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.proteanit.sql.DbUtils;
import register.userForm;

/**
 *
 * @author SCC-COLLEGE
 */
public class checkOut extends javax.swing.JFrame {

    /**
     * Creates new form adminDash
     */
    public checkOut() {
        initComponents();
        
        displayData();
        
        date();
        time();
      
    }
    
      private void date(){
        
        Date d = new Date();
        
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
         
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
            ResultSet rs = dbc.getData("SELECT t.transID, t.roomID, t.u_id, t.name, t.Contact, r.roomType, r.roomNo, t.Check_In, t.Check_Out, t.status "
                    + "\n FROM tbl_transaction t\n JOIN tbl_room r ON t.roomID = r.roomID;");;
            cosTable.setModel(DbUtils.resultSetToTableModel(rs));
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

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        p3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        p4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        pw = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        cnm = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        cno = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        tprc = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        p9 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        p10 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        nod = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        cosTable = new javax.swing.JTable();
        p5 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        rtp = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        rno = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cki = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        cko = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        tid = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        cname = new javax.swing.JLabel();
        cimg = new javax.swing.JLabel();
        cid = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        rid = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        rp = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtdate = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txttime = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        tuid = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        tnm = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.setMinimumSize(new java.awt.Dimension(1366, 768));
        jPanel2.setPreferredSize(new java.awt.Dimension(1366, 768));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Copperplate Gothic Bold", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Check-Out");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 1120, -1));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setText("BACK");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 10, 73, 41));

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 70));

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
        jLabel11.setText("PRINT");

        javax.swing.GroupLayout p3Layout = new javax.swing.GroupLayout(p3);
        p3.setLayout(p3Layout);
        p3Layout.setHorizontalGroup(
            p3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                .addContainerGap())
        );
        p3Layout.setVerticalGroup(
            p3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.add(p3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 590, 260, 50));

        p4.setBackground(new java.awt.Color(0, 153, 0));
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
        jLabel13.setText("CHECK-OUT");
        jLabel13.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jLabel13AncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        javax.swing.GroupLayout p4Layout = new javax.swing.GroupLayout(p4);
        p4.setLayout(p4Layout);
        p4Layout.setHorizontalGroup(
            p4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        p4Layout.setVerticalGroup(
            p4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.add(p4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 660, 260, 50));

        jLabel10.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Room Type:");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 270, 100, 20));

        pw.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel2.add(pw, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 90, -1, -1));

        jLabel6.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Name:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 260, 92, 30));

        jLabel5.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Room No.:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 300, 110, 20));

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Contact No.:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 300, 100, 30));

        jLabel14.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Total Price:");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 370, -1, 30));

        cnm.setBackground(new java.awt.Color(51, 51, 51));
        cnm.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cnm.setForeground(new java.awt.Color(255, 255, 255));
        cnm.setBorder(null);
        cnm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cnmActionPerformed(evt);
            }
        });
        jPanel2.add(cnm, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 260, 250, 30));

        jLabel19.setForeground(new java.awt.Color(51, 204, 255));
        jLabel19.setText("______________________________________________");
        jPanel2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 270, 300, 30));

        cno.setBackground(new java.awt.Color(51, 51, 51));
        cno.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cno.setForeground(new java.awt.Color(255, 255, 255));
        cno.setBorder(null);
        cno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cnoActionPerformed(evt);
            }
        });
        jPanel2.add(cno, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 300, 250, 30));

        jLabel20.setForeground(new java.awt.Color(51, 204, 255));
        jLabel20.setText("_______________________________________________");
        jPanel2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 310, 300, 30));

        tprc.setBackground(new java.awt.Color(51, 51, 51));
        tprc.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        tprc.setForeground(new java.awt.Color(255, 255, 255));
        tprc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tprc.setBorder(null);
        tprc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tprcActionPerformed(evt);
            }
        });
        jPanel2.add(tprc, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 370, 280, 40));

        jLabel21.setForeground(new java.awt.Color(51, 204, 255));
        jLabel21.setText("______________________________________________");
        jPanel2.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 390, 290, 40));

        p9.setBackground(new java.awt.Color(102, 102, 102));
        p9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        p9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p9MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                p9MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                p9MouseExited(evt);
            }
        });

        jLabel22.setBackground(new java.awt.Color(0, 102, 153));
        jLabel22.setFont(new java.awt.Font("Verdana", 1, 20)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("CLEAR");

        javax.swing.GroupLayout p9Layout = new javax.swing.GroupLayout(p9);
        p9.setLayout(p9Layout);
        p9Layout.setHorizontalGroup(
            p9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                .addContainerGap())
        );
        p9Layout.setVerticalGroup(
            p9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jPanel2.add(p9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 520, 260, 50));

        p10.setBackground(new java.awt.Color(102, 102, 102));
        p10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        p10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p10MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                p10MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                p10MouseExited(evt);
            }
        });

        jLabel23.setBackground(new java.awt.Color(0, 102, 153));
        jLabel23.setFont(new java.awt.Font("Verdana", 1, 20)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("SELECT");

        javax.swing.GroupLayout p10Layout = new javax.swing.GroupLayout(p10);
        p10.setLayout(p10Layout);
        p10Layout.setHorizontalGroup(
            p10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                .addContainerGap())
        );
        p10Layout.setVerticalGroup(
            p10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jPanel2.add(p10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, 260, 50));

        jLabel7.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("No. of Days:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 210, 100, 20));

        nod.setBackground(new java.awt.Color(51, 51, 51));
        nod.setFont(nod.getFont().deriveFont(nod.getFont().getSize()+3f));
        nod.setForeground(new java.awt.Color(255, 255, 255));
        nod.setBorder(null);
        nod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nodActionPerformed(evt);
            }
        });
        jPanel2.add(nod, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 210, 270, 20));

        jLabel27.setForeground(new java.awt.Color(51, 204, 255));
        jLabel27.setText("_______________________________________________");
        jPanel2.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 210, 290, 30));

        jScrollPane1.setViewportView(cosTable);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 460, 950, 250));

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
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        p5Layout.setVerticalGroup(
            p5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.add(p5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 260, 50));

        jLabel24.setForeground(new java.awt.Color(51, 51, 51));
        jLabel24.setText("_________________________________________");
        jPanel2.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 310, 280, 40));

        jLabel26.setForeground(new java.awt.Color(51, 51, 51));
        jLabel26.setText("_________________________________________");
        jPanel2.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 410, 290, 30));

        rtp.setBackground(new java.awt.Color(51, 51, 51));
        rtp.setFont(rtp.getFont().deriveFont(rtp.getFont().getSize()+3f));
        rtp.setForeground(new java.awt.Color(255, 255, 255));
        rtp.setBorder(null);
        rtp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rtpActionPerformed(evt);
            }
        });
        jPanel2.add(rtp, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 270, 270, 20));

        jLabel29.setForeground(new java.awt.Color(51, 204, 255));
        jLabel29.setText("_______________________________________________");
        jPanel2.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 270, 290, 30));

        rno.setBackground(new java.awt.Color(51, 51, 51));
        rno.setFont(rno.getFont().deriveFont(rno.getFont().getSize()+3f));
        rno.setForeground(new java.awt.Color(255, 255, 255));
        rno.setBorder(null);
        rno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rnoActionPerformed(evt);
            }
        });
        jPanel2.add(rno, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 300, 270, 20));

        jLabel30.setForeground(new java.awt.Color(51, 204, 255));
        jLabel30.setText("_______________________________________________");
        jPanel2.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 300, 290, 30));

        jLabel12.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Checked-In:");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 150, 92, 20));

        jLabel9.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Check-Out:");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 180, 110, -1));

        cki.setBackground(new java.awt.Color(51, 51, 51));
        cki.setFont(cki.getFont().deriveFont(cki.getFont().getSize()+3f));
        cki.setForeground(new java.awt.Color(255, 255, 255));
        cki.setBorder(null);
        cki.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckiActionPerformed(evt);
            }
        });
        jPanel2.add(cki, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 150, 270, 20));

        jLabel31.setForeground(new java.awt.Color(51, 204, 255));
        jLabel31.setText("_______________________________________________");
        jPanel2.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 150, 290, 30));

        cko.setBackground(new java.awt.Color(51, 51, 51));
        cko.setFont(cko.getFont().deriveFont(cko.getFont().getSize()+3f));
        cko.setForeground(new java.awt.Color(255, 255, 255));
        cko.setBorder(null);
        cko.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckoActionPerformed(evt);
            }
        });
        jPanel2.add(cko, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 180, 270, 20));

        jLabel32.setForeground(new java.awt.Color(51, 204, 255));
        jLabel32.setText("_______________________________________________");
        jPanel2.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 180, 290, 30));

        jLabel17.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Checked-in by:");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 100, 170, 20));

        tid.setEditable(false);
        tid.setBackground(new java.awt.Color(51, 51, 51));
        tid.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tid.setForeground(new java.awt.Color(255, 255, 255));
        tid.setBorder(null);
        tid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tidActionPerformed(evt);
            }
        });
        jPanel2.add(tid, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 220, 250, 30));

        jLabel33.setForeground(new java.awt.Color(51, 204, 255));
        jLabel33.setText("______________________________________________");
        jPanel2.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 230, 300, 30));

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel4MouseExited(evt);
            }
        });
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cname.setFont(new java.awt.Font("Verdana", 1, 20)); // NOI18N
        cname.setForeground(new java.awt.Color(255, 255, 255));
        cname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cname.setText("username");
        jPanel5.add(cname, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 240, 28));
        jPanel5.add(cimg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 230));

        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 240, -1));

        cid.setFont(new java.awt.Font("Verdana", 0, 13)); // NOI18N
        cid.setForeground(new java.awt.Color(255, 255, 255));
        cid.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cid.setText("Current user ID");
        jPanel4.add(cid, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 240, 20));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 260, 280));

        jLabel28.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Room ID");
        jPanel2.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 240, 92, 20));

        rid.setBackground(new java.awt.Color(51, 51, 51));
        rid.setFont(rid.getFont().deriveFont(rid.getFont().getSize()+3f));
        rid.setForeground(new java.awt.Color(255, 255, 255));
        rid.setBorder(null);
        rid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ridActionPerformed(evt);
            }
        });
        jPanel2.add(rid, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 240, 270, 20));

        jLabel34.setForeground(new java.awt.Color(51, 204, 255));
        jLabel34.setText("_______________________________________________");
        jPanel2.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 240, 290, 30));

        jLabel35.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("Room price:");
        jPanel2.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 330, 110, -1));

        rp.setBackground(new java.awt.Color(51, 51, 51));
        rp.setFont(rp.getFont().deriveFont(rp.getFont().getSize()+3f));
        rp.setForeground(new java.awt.Color(255, 255, 255));
        rp.setBorder(null);
        rp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rpActionPerformed(evt);
            }
        });
        jPanel2.add(rp, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 330, 270, 20));

        jLabel36.setForeground(new java.awt.Color(51, 204, 255));
        jLabel36.setText("_______________________________________________");
        jPanel2.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 330, 290, 30));

        jLabel25.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Date");
        jPanel2.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 70, -1, 28));

        txtdate.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        txtdate.setForeground(new java.awt.Color(255, 255, 255));
        txtdate.setText("0");
        jPanel2.add(txtdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 100, -1, 28));

        jLabel8.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Time");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 70, -1, 28));

        txttime.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        txttime.setForeground(new java.awt.Color(255, 255, 255));
        txttime.setText("0");
        jPanel2.add(txttime, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 100, -1, 28));

        jLabel37.setForeground(new java.awt.Color(51, 204, 255));
        jLabel37.setText("_____________________________________________");
        jPanel2.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 140, 290, 50));

        tuid.setEditable(false);
        tuid.setBackground(new java.awt.Color(51, 51, 51));
        tuid.setFont(tuid.getFont().deriveFont(tuid.getFont().getSize()+3f));
        tuid.setForeground(new java.awt.Color(255, 255, 255));
        tuid.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tuid.setBorder(null);
        tuid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tuidActionPerformed(evt);
            }
        });
        jPanel2.add(tuid, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 140, 250, 40));

        jLabel18.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Employee ID:");
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 140, 120, 40));

        jLabel38.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("Customer ID:");
        jPanel2.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 220, 120, 30));

        jLabel39.setForeground(new java.awt.Color(51, 204, 255));
        jLabel39.setText("_____________________________________________");
        jPanel2.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 170, 290, 50));

        tnm.setEditable(false);
        tnm.setBackground(new java.awt.Color(51, 51, 51));
        tnm.setFont(tnm.getFont().deriveFont(tnm.getFont().getSize()+3f));
        tnm.setForeground(new java.awt.Color(255, 255, 255));
        tnm.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tnm.setBorder(null);
        tnm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tnmActionPerformed(evt);
            }
        });
        jPanel2.add(tnm, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 170, 250, 40));

        jLabel40.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("Employee name:");
        jPanel2.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 170, 130, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 768, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        Session sess = Session.getInstance();
dbconnect db = new dbconnect();

if (sess.getUid() == 0) {
    // Handle the case where the session user ID is 0
} else {
    cname.setText("" + sess.getFname());
    cid.setText("" + sess.getUid());

    // Get the current session user image
    try {
        ResultSet rsCurrentUser = db.getData("SELECT * FROM tbl_user WHERE u_id = '" + sess.getUid() + "'");
        if (rsCurrentUser.next()) {
            cimg.setIcon(ResizeImage(rsCurrentUser.getString("u_image"), null, cimg));
        }
        rsCurrentUser.close();
    } catch (SQLException ex) {
        System.out.println("" + ex);
    }

   
}


        
    }//GEN-LAST:event_formWindowActivated

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        adminDash ads = new adminDash();
        ads.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1MouseClicked

    private void p4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p4MouseClicked
    
        dbconnect db = new dbconnect();
        int row = cosTable.getSelectedRow();

        
        TableModel mod = cosTable.getModel();
               try{

               ResultSet rs = db.getData("SELECT * FROM tbl_cost WHERE roomNo = '"+mod.getValueAt(row, 3)+"'");

               if(rs.next()){
                   ResultSet up = db.getData("SELECT * FROM tbl_rooms WHERE roomNo = '"+mod.getValueAt(row, 3)+"'");
                   Object val = rs.getString("id");
                   String id = val.toString();

                   int l = JOptionPane.showConfirmDialog(null, "Are you Sure you want to Check Out?", "Select", JOptionPane.YES_NO_OPTION);

                   if(l == 0){
                       int s_id = Integer.parseInt(id);
                       db.delete(s_id,"tbl_cost","id",false);
                       if(up.next()){
                           db.updateData("UPDATE tbl_rooms SET r_status = 'Archived' WHERE roomNo = '"+mod.getValueAt(row, 3)+"'",false);
                           JOptionPane.showMessageDialog(null, "Checked out");
                       }
                   }
                   DefaultTableModel model = (DefaultTableModel)cosTable.getModel();
                   model.setRowCount(0);
                   displayData(); 

            }

            } catch (SQLException ex) 
            {
                 Logger.getLogger(manageRooms.class.getName()).log(Level.SEVERE, null, ex);
             }        
        
    }//GEN-LAST:event_p4MouseClicked

    private void p4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p4MouseEntered
        p4.setBackground(new Color(0,102,0));
    }//GEN-LAST:event_p4MouseEntered

    private void p4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p4MouseExited
       p4.setBackground(new Color(0,153,0));
    }//GEN-LAST:event_p4MouseExited

    private void p3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p3MouseExited
        p3.setBackground(new Color(102,0,0));
    }//GEN-LAST:event_p3MouseExited

    private void p3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p3MouseEntered
        p3.setBackground(new Color(153,0,0));
    }//GEN-LAST:event_p3MouseEntered

    private void p3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p3MouseClicked

    }//GEN-LAST:event_p3MouseClicked

    private void cnmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cnmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cnmActionPerformed

    private void cnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cnoActionPerformed

    private void p9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p9MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_p9MouseClicked

    private void p9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p9MouseEntered
        p9.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_p9MouseEntered

    private void p9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p9MouseExited
        p9.setBackground(new Color(102,102,102));
    }//GEN-LAST:event_p9MouseExited

    private void jLabel13AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jLabel13AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel13AncestorAdded

    private void tprcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tprcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tprcActionPerformed

    private void p10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p10MouseClicked
        int rowIndex = cosTable.getSelectedRow();
        
        if(rowIndex < 0 )
        {
            JOptionPane.showMessageDialog(null, "Please select an item!");
        }
        else
        {

            try
            {
                dbconnect db = new dbconnect();
                TableModel tbl= cosTable.getModel();
                ResultSet rs = db.getData("SELECT * FROM tbl_user WHERE u_id = '"+tbl.getValueAt(rowIndex, 0)+"'");
                if(rs.next())
                {
                    
  
                    cnm.setText(""+rs.getInt("Name"));
                    cno.setText(""+rs.getString("ContactNo"));
                    rtp.setText(""+rs.getString("RoomType"));
                    rno.setText(""+rs.getString("RoomNo"));
                    cki.setText(""+rs.getString("Check_In"));
                    cko.setText(""+rs.getString("Check_Out"));
                   
                    
                    
                    
                }
                
            }
            catch(SQLException ex)
            {
                System.out.println(""+ex);

            }
            
        }
    }//GEN-LAST:event_p10MouseClicked

    private void p10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p10MouseEntered
       p10.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_p10MouseEntered

    private void p10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p10MouseExited
        p10.setBackground(new Color(102,102,102));
    }//GEN-LAST:event_p10MouseExited

    private void p5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p5MouseExited
        p5.setBackground(new Color(102,102,102));// TODO add your handling code here:
    }//GEN-LAST:event_p5MouseExited

    private void p5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p5MouseEntered
        p5.setBackground(new Color(204,204,204)); // TODO add your handling code here:
    }//GEN-LAST:event_p5MouseEntered

    private void p5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p5MouseClicked
        DefaultTableModel model = (DefaultTableModel)cosTable.getModel();
        model.setRowCount(0);
        displayData();
    }//GEN-LAST:event_p5MouseClicked

    private void nodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nodActionPerformed

    private void rnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rnoActionPerformed

    private void ckiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ckiActionPerformed

    private void ckoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ckoActionPerformed

    private void tidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tidActionPerformed

    private void rtpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rtpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rtpActionPerformed

    private void ridActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ridActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ridActionPerformed

    private void rpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rpActionPerformed

    private void jPanel4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseEntered
         jPanel4.setBackground(new Color(102,102,102));
         
    }//GEN-LAST:event_jPanel4MouseEntered

    private void jPanel4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseExited
        jPanel4.setBackground(new Color(51,51,51));
    }//GEN-LAST:event_jPanel4MouseExited

    private void tuidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tuidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tuidActionPerformed

    private void tnmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tnmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tnmActionPerformed

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
            java.util.logging.Logger.getLogger(checkOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(checkOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(checkOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(checkOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new checkOut().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel cid;
    private javax.swing.JLabel cimg;
    public javax.swing.JTextField cki;
    public javax.swing.JTextField cko;
    private javax.swing.JLabel cname;
    public javax.swing.JTextField cnm;
    public javax.swing.JTextField cno;
    private javax.swing.JTable cosTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTextField nod;
    private javax.swing.JPanel p10;
    private javax.swing.JPanel p3;
    private javax.swing.JPanel p4;
    private javax.swing.JPanel p5;
    private javax.swing.JPanel p9;
    public javax.swing.JTextField pw;
    public javax.swing.JTextField rid;
    public javax.swing.JTextField rno;
    public javax.swing.JTextField rp;
    public javax.swing.JTextField rtp;
    public javax.swing.JTextField tid;
    public javax.swing.JTextField tnm;
    public javax.swing.JTextField tprc;
    public javax.swing.JTextField tuid;
    private javax.swing.JLabel txtdate;
    private javax.swing.JLabel txttime;
    // End of variables declaration//GEN-END:variables
}
