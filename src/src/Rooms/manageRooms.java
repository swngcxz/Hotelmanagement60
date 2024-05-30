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
import java.io.File;
import java.io.IOException;

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
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;
import register.userForm;

/**
 *
 * @author SCC-COLLEGE
 */
public class manageRooms extends javax.swing.JFrame {

    /**
     * Creates new form adminDash
     */
    public manageRooms() {
        initComponents();
        displayRoom();
        displayData();
        rt();
        date();
        time();
        voidHide();
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
    
    private void voidHide(){
        dbconnect db = new dbconnect();
        Session sess = Session.getInstance();
        try{
              ResultSet rs = db.getData("SELECT * FROM tbl_user WHERE u_id = '"+sess.getUid()+"'");
             
              if(rs.next()){
                  String type = rs.getString("u_type");
                  
                  if(type.equals("Admin")){
                      System.out.println("revealed");
                  }else{
                      vd.hide();
                  }
              }
              
              
           }catch(SQLException ex){
               System.out.println(""+ex);
           }
    }
    
    private void rt(){
        dbconnect db = new dbconnect();
        try{
            ResultSet res = db.getData("SELECT DISTINCT roomType FROM tbl_rooms WHERE r_status = 'Active' ");
            
            while(res.next()){
                String rm = res.getString("roomType");
                txtrt.addItem(rm);
                
            }
        }catch(SQLException e){
            System.out.println(""+e);
        }
        
    }
    
     public void displayData(){
        try{
            dbconnect dbc = new dbconnect();
            ResultSet rs = dbc.getData("SELECT Name, ContactNo, RoomType, RoomNo, Check_In, Check_Out, c_balance FROM tbl_cost");
            cosTable.setModel(DbUtils.resultSetToTableModel(rs));
             rs.close();
        }catch(SQLException ex){
            System.out.println("Errors: "+ex.getMessage());
        
        }
        
    
    }
    
    
     public void displayRoom(){
        try{
            dbconnect dbc = new dbconnect();
            ResultSet rs = dbc.getData("SELECT roomNo, roomType,price, r_status FROM tbl_rooms");
            tableRoom.setModel(DbUtils.resultSetToTableModel(rs));
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
        vd = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        p4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        pw = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        rt2 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        rt3 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        rt5 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        p9 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        cosTable = new javax.swing.JTable();
        p5 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableRoom = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        txtrt = new javax.swing.JComboBox<>();
        txtrno = new javax.swing.JComboBox<>();
        txttime = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtdate = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cout = new com.toedter.calendar.JDateChooser();
        p10 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        rt6 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        id = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 153, 0));

        jLabel1.setFont(new java.awt.Font("Copperplate Gothic Bold", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Check-In");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setText("BACK");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1066, Short.MAX_VALUE)
                .addGap(29, 29, 29)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, -1));

        vd.setBackground(new java.awt.Color(102, 0, 0));
        vd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        vd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vdMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                vdMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                vdMouseExited(evt);
            }
        });

        jLabel11.setBackground(new java.awt.Color(0, 102, 153));
        jLabel11.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("VOID ");

        javax.swing.GroupLayout vdLayout = new javax.swing.GroupLayout(vd);
        vd.setLayout(vdLayout);
        vdLayout.setHorizontalGroup(
            vdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vdLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        vdLayout.setVerticalGroup(
            vdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vdLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.add(vd, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 280, 220, 60));

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
        jLabel13.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Book Transaction");
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
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.add(p4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 310, 280, 60));

        jLabel8.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel8.setText("Time");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 70, -1, 28));

        jLabel10.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel10.setText("Name:");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 92, 28));

        pw.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel2.add(pw, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 90, -1, -1));

        jLabel6.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel6.setText("Room type:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 90, 92, 28));

        jLabel5.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel5.setText("Contact no.:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 110, 28));

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel3.setText("Room No.:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 130, 92, 28));

        jLabel14.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel14.setText("Room Price:");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 190, -1, 28));

        rt2.setBackground(new java.awt.Color(204, 255, 255));
        rt2.setFont(rt2.getFont().deriveFont(rt2.getFont().getSize()+3f));
        rt2.setBorder(null);
        rt2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rt2ActionPerformed(evt);
            }
        });
        jPanel2.add(rt2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, 240, 40));

        jLabel19.setForeground(new java.awt.Color(51, 51, 51));
        jLabel19.setText("_________________________________________");
        jPanel2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 250, 50));

        rt3.setBackground(new java.awt.Color(204, 255, 255));
        rt3.setFont(rt3.getFont().deriveFont(rt3.getFont().getSize()+3f));
        rt3.setBorder(null);
        rt3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rt3ActionPerformed(evt);
            }
        });
        jPanel2.add(rt3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 190, 240, 40));

        jLabel20.setForeground(new java.awt.Color(51, 51, 51));
        jLabel20.setText("_________________________________________");
        jPanel2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 200, 250, 50));

        rt5.setBackground(new java.awt.Color(204, 255, 255));
        rt5.setFont(rt5.getFont().deriveFont(rt5.getFont().getSize()+3f));
        rt5.setBorder(null);
        rt5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rt5ActionPerformed(evt);
            }
        });
        jPanel2.add(rt5, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 180, 240, 40));

        jLabel21.setForeground(new java.awt.Color(51, 51, 51));
        jLabel21.setText("_________________________________________");
        jPanel2.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 190, 250, 50));

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
        jLabel22.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("CLEAR");

        javax.swing.GroupLayout p9Layout = new javax.swing.GroupLayout(p9);
        p9.setLayout(p9Layout);
        p9Layout.setHorizontalGroup(
            p9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                .addContainerGap())
        );
        p9Layout.setVerticalGroup(
            p9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.add(p9, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 310, 170, 60));

        jScrollPane1.setViewportView(cosTable);

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
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 994, Short.MAX_VALUE)
                .addContainerGap())
        );
        p5Layout.setVerticalGroup(
            p5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(p5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(111, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(p5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );

        jTabbedPane1.addTab("CUSTOMER DETAILS", jPanel3);

        tableRoom.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tableRoom);

        jPanel5.setBackground(new java.awt.Color(255, 51, 51));

        jLabel2.setText("UNCLEAN");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(0, 204, 204));

        jLabel12.setText("RESERVED");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(0, 204, 0));

        jLabel23.setText("AVAILABLE");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel23)
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(0, 51, 153));

        jLabel24.setText("BOOKED");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel24)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1205, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(95, 95, 95))
        );

        jTabbedPane1.addTab("ROOM DETAILS", jPanel4);

        jPanel2.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, 1230, 420));
        jTabbedPane1.getAccessibleContext().setAccessibleName("ROOM DETAILS");
        jTabbedPane1.getAccessibleContext().setAccessibleDescription("ROOM DETAILS");

        txtrt.setBackground(new java.awt.Color(204, 255, 255));
        txtrt.setEditable(true);
        txtrt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));
        txtrt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtrtActionPerformed(evt);
            }
        });
        jPanel2.add(txtrt, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 80, 250, 40));

        txtrno.setBackground(new java.awt.Color(204, 255, 255));
        txtrno.setEditable(true);
        jPanel2.add(txtrno, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 120, 250, 40));

        txttime.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        txttime.setText("0");
        jPanel2.add(txttime, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 100, -1, 28));

        jLabel25.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel25.setText("Date");
        jPanel2.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 70, -1, 28));

        txtdate.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        txtdate.setText("0");
        jPanel2.add(txtdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 100, -1, 28));

        jLabel4.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel4.setText("Check-Out");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 180, 92, 28));
        jPanel2.add(cout, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 170, 240, 40));

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

        jLabel26.setBackground(new java.awt.Color(0, 102, 153));
        jLabel26.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("EDIT");

        javax.swing.GroupLayout p10Layout = new javax.swing.GroupLayout(p10);
        p10.setLayout(p10Layout);
        p10Layout.setHorizontalGroup(
            p10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                .addContainerGap())
        );
        p10Layout.setVerticalGroup(
            p10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.add(p10, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 310, 170, 60));

        jLabel16.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel16.setText("Total Price:");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 230, -1, 28));

        rt6.setBackground(new java.awt.Color(204, 255, 255));
        rt6.setFont(rt6.getFont().deriveFont(rt6.getFont().getSize()+3f));
        rt6.setBorder(null);
        rt6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rt6ActionPerformed(evt);
            }
        });
        jPanel2.add(rt6, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 220, 240, 40));

        jLabel27.setForeground(new java.awt.Color(51, 51, 51));
        jLabel27.setText("_________________________________________");
        jPanel2.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 230, 250, 50));

        jLabel17.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel17.setText("Transaction ID:");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 120, 28));

        id.setBackground(new java.awt.Color(204, 255, 255));
        id.setFont(id.getFont().deriveFont(id.getFont().getSize()+3f));
        id.setBorder(null);
        id.setEnabled(false);
        id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idActionPerformed(evt);
            }
        });
        jPanel2.add(id, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, 200, 40));

        jLabel28.setForeground(new java.awt.Color(51, 51, 51));
        jLabel28.setText("_________________________________________");
        jPanel2.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 210, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1290, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 853, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        
        
    }//GEN-LAST:event_formWindowActivated

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        adminDash ads = new adminDash();
        ads.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1MouseClicked

    private void p4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p4MouseClicked
        dbconnect db = new dbconnect();
        try{
            ResultSet rs = db.getData("SELECT * FROM tbl_rooms");
        
            
            
            
            if(rs.next()){
                   
                
                    if(db.insertData("INSERT INTO tbl_cost(Name,ContactNo,RoomType,RoomNo,Check_in,Check_out,c_balance, c_status)"
                            + "VALUES('"+rt2.getText()+"','"+rt3.getText()+"', '"+txtrt.getSelectedItem()+"','"+txtrno.getSelectedItem()+"',"
                                    + "CURRENT_TIMESTAMP,NULL,'"+rt5.getText()+"', 'Active')"))
                    {
                         db.updateData("UPDATE tbl_rooms SET r_status = 'Booked' WHERE roomNo = '"+txtrno.getSelectedItem()+"'",false);
                         JOptionPane.showMessageDialog(null,"Checked In");
                         DefaultTableModel model = (DefaultTableModel)cosTable.getModel();
                         model.setRowCount(0);
                         displayData(); 
                         
                        id.setText("");
                        txtrt.setSelectedItem("");
                        txtrno.setSelectedItem("");
                        rt2.setText("");
                        rt3.setText("");
                        cout.setDate(null);
                        
                    }else{
                        JOptionPane.showMessageDialog(null,"Invalid");
                    }
                   
                }
             
                
            
            
           
        } catch (SQLException ex) {
            Logger.getLogger(manageRooms.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//GEN-LAST:event_p4MouseClicked

    private void p4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p4MouseEntered
        p4.setBackground(new Color(0,102,0));
    }//GEN-LAST:event_p4MouseEntered

    private void p4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p4MouseExited
       p4.setBackground(new Color(0,153,0));
    }//GEN-LAST:event_p4MouseExited

    private void p5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p5MouseExited
        p5.setBackground(new Color(102,102,102));// TODO add your handling code here:
    }//GEN-LAST:event_p5MouseExited

    private void p5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p5MouseEntered
       p5.setBackground(new Color(204,204,204)); // TODO add your handling code here:
    }//GEN-LAST:event_p5MouseEntered

    private void p5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p5MouseClicked
        DefaultTableModel model = (DefaultTableModel)cosTable.getModel();
        model.setRowCount(0);
        displayRoom();    
    }//GEN-LAST:event_p5MouseClicked

    private void vdMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vdMouseExited
        vd.setBackground(new Color(102,0,0));
    }//GEN-LAST:event_vdMouseExited

    private void vdMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vdMouseEntered
        vd.setBackground(new Color(153,0,0));
    }//GEN-LAST:event_vdMouseEntered

    private void vdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vdMouseClicked
        dbconnect db = new dbconnect();
        int row = cosTable.getSelectedRow();

        if(row < 0)
        {
            JOptionPane.showMessageDialog(null,"Please Select");
        }
        else
        {
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
       }
    }//GEN-LAST:event_vdMouseClicked

    private void rt2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rt2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rt2ActionPerformed

    private void rt3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rt3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rt3ActionPerformed

    private void p9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p9MouseClicked
                    id.setText("");
                    txtrt.setSelectedItem("");
                    txtrno.setSelectedItem("");
                    rt2.setText("");
                    rt3.setText("");
                    
                    
           
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

    private void txtrtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtrtActionPerformed
        dbconnect db = new dbconnect();
        String rts = txtrt.getSelectedItem().toString();
        txtrno.removeAllItems();
        try{
            ResultSet res = db.getData("SELECT * FROM tbl_rooms WHERE roomType ='"+rts+"' AND r_status = 'Active' ");
            
            while(res.next()){
                
                txtrno.addItem(res.getString("roomNo"));
                rt5.setText(res.getString("price"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(manageRooms.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }//GEN-LAST:event_txtrtActionPerformed

    private void rt5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rt5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rt5ActionPerformed

    private void p10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p10MouseClicked
       dbconnect db = new dbconnect();
        int row = cosTable.getSelectedRow();

        if(row < 0)
        {
            JOptionPane.showMessageDialog(null,"Please Select");
        }
        else
        {
            TableModel mod = cosTable.getModel();
           try{

           ResultSet rs = db.getData("SELECT * FROM tbl_cost WHERE roomNo = '"+mod.getValueAt(row, 3)+"'");

           if(rs.next()){
               
                id.setText(""+rs.getInt("id"));
                rt2.setText(""+rs.getString("Name"));
                rt3.setText(""+rs.getString("contactNo"));
                txtrt.setSelectedItem(""+rs.getString("roomType"));
                txtrno.setSelectedItem(""+rs.getInt("roomNo"));
          
               
               
               
               
               
               }
        
        } catch (SQLException ex) 
        {
             Logger.getLogger(manageRooms.class.getName()).log(Level.SEVERE, null, ex);
         }
       }
    }//GEN-LAST:event_p10MouseClicked

    private void p10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p10MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_p10MouseEntered

    private void p10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p10MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_p10MouseExited

    private void rt6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rt6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rt6ActionPerformed

    private void idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idActionPerformed

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
            java.util.logging.Logger.getLogger(manageRooms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(manageRooms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(manageRooms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(manageRooms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new manageRooms().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable cosTable;
    private com.toedter.calendar.JDateChooser cout;
    private javax.swing.JTextField id;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel p10;
    private javax.swing.JPanel p4;
    private javax.swing.JPanel p5;
    private javax.swing.JPanel p9;
    public javax.swing.JTextField pw;
    private javax.swing.JTextField rt2;
    private javax.swing.JTextField rt3;
    private javax.swing.JTextField rt5;
    private javax.swing.JTextField rt6;
    private javax.swing.JTable tableRoom;
    private javax.swing.JLabel txtdate;
    private javax.swing.JComboBox<String> txtrno;
    private javax.swing.JComboBox<String> txtrt;
    private javax.swing.JLabel txttime;
    private javax.swing.JPanel vd;
    // End of variables declaration//GEN-END:variables
}