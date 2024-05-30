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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import net.proteanit.sql.DbUtils;


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
        getTotalCost();
        

        
        srchf.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
            filterTable(srchf.getText(), cosTable); 
            filterTable(srchf.getText(), tableRoom);
        }

       }); 
        
        srchf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                filterTable(srchf.getText(), cosTable); 
                filterTable(srchf.getText(), tableRoom);
            }
        }); 
    }
    
    
    //TIME//
        private void date(){

           Date d = new Date();

           SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");


           String dt = sdf.format(d);

           txtdate.setText(dt);        
           cin.setText(dt);

       }

           Timer t;
           SimpleDateFormat st;

            private void time() {
         Timer t = new Timer(1000, new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent ae) {
                 Date dt = new Date();
                 SimpleDateFormat st = new SimpleDateFormat("hh:mm:ss a");
                 String tm = st.format(dt);

                 // Update the timeLabel with the formatted time string
                 txttime.setText("" + tm);
             }
         });

         t.start();
     }
    
        
    //FOR SUPER ADMIN SOON TO FIX   
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
                ResultSet res = db.getData("SELECT DISTINCT roomType FROM tbl_room WHERE r_status = 'Active' ");

                while(res.next()){
                    String rm = res.getString("roomType");
                    txtrt.addItem(rm);

                }
            }catch(SQLException e){
                System.out.println(""+e);
            }
            
            

        }
    
 
    //DISPLAY TABLES
        public void displayData(){
            try{
                dbconnect dbc = new dbconnect();
                ResultSet rs = dbc.getData("SELECT t.transID, t.roomID, t.u_id, t.name, t.Contact, r.roomType, r.roomNo, t.Check_In, t.Check_Out, t.status "
                        + "\n FROM tbl_transaction t\n JOIN tbl_room r ON t.roomID = r.roomID;");
                cosTable.setModel(DbUtils.resultSetToTableModel(rs));
                 rs.close();
            }catch(SQLException ex){
                System.out.println("Errors: "+ex.getMessage());

            }  
        }


        public void displayRoom(){
            try{
                dbconnect dbc = new dbconnect();
                ResultSet rs = dbc.getData("SELECT roomNo, roomType, r_price, r_status FROM tbl_room");
                tableRoom.setModel(DbUtils.resultSetToTableModel(rs));
                 rs.close();
            }catch(SQLException ex){
                System.out.println("Errors: "+ex.getMessage());

            }
        }
     
     
     
     
    
    
    
    
  public static int getHeightFromWidth(String imagePath, int desiredWidth) {
        try {
          
            File imageFile = new File(imagePath);
            BufferedImage image = ImageIO.read(imageFile);
            
          
            int originalWidth = image.getWidth();
            int originalHeight = image.getHeight();
        
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
    
 
   
    
    
    //SEARCH BAR & HIGHLIGHT//
    
    
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

    
    public void getTotalCost()
    {
        Date d = new Date();

            SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");

            String dt = sdf.format(d);

            Date checkInDate = d;
            Date checkOutDate = cout.getDate();

            // Ensure both check-in and check-out dates are selected
            if (checkInDate != null && checkOutDate != null) {
                // Calculate the total number of days between check-in and check-out dates
                long totalDays = getDifferenceInDays(checkInDate, checkOutDate);
                String query = "SELECT * FROM `tbl_room` ORDER BY `tbl_room`.`r_price` ASC";
                double roomRate = 0.0;
                double totalCost = totalDays * roomRate;
                    
                    tprc.setText(""+totalCost);
                    
            }
            
    }
    
     private long getDifferenceInDays(Date startDate, Date endDate) 
     {
        // Validate dates
         
        if (endDate.before(startDate)) {
            // Swap dates if end date is before start date
            Date tempDate = startDate;
            startDate = endDate;
       
         endDate = tempDate;
        }

        long diffInMillies = Math.abs(endDate.getTime() - startDate.getTime());
        long days = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        System.out.println("Start Date: " + startDate);
        System.out.println("End Date: " + endDate);
        System.out.println("Milliseconds Difference: " + diffInMillies);
        System.out.println("Days Difference: " + days);

        return days;
    }
     
   private double calculateTotalPayment() {
    dbconnect db = new dbconnect();
    double totalPayment = 0;

    String roomType = txtrt.getSelectedItem().toString(); // Assuming txtrt is your combo box for room type
    String roomNumber = txtrno.getSelectedItem().toString(); // Assuming txtrno is your combo box for room number

    Date checkInDate = new Date(); // Assuming you get the check-in date from somewhere
    Date checkOutDate = cout.getDate(); // Assuming cout is your date picker for check-out date

    // Check if both room type and room number are selected
    if (!roomType.isEmpty() && !roomNumber.isEmpty()) {
        // Query the database to get the room price based on room type and room number
        String query = "SELECT r_price FROM tbl_room WHERE roomType = ? AND roomNo = ?";
        try {
            PreparedStatement pst = db.connect.prepareStatement(query);
            pst.setString(1, roomType);
            pst.setString(2, roomNumber);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                double roomPrice = rs.getDouble("r_price");

                if (checkOutDate == null) {
                    // Display room price if checkout date is not selected
                    totalPayment = roomPrice;
                } else {
                    // Calculate total number of days between check-in and check-out dates
                    long totalDays = getDifferenceInDays(checkInDate, checkOutDate);
                    totalPayment = totalDays * roomPrice;
                }
            } else {
                tprc.setText("Room data not found");
            }

            rs.close();
            pst.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            tprc.setText("Error calculating total payment");
        }
    } else {
        // Handle case where room type or room number is not selected
        if (roomType.isEmpty()) {
            // Prompt user to select room type
            tprc.setText("Please select a room type");
        } else {
            // Prompt user to select room number
            tprc.setText("Please select a room number");
        }
    }

    return totalPayment;
}

     
     
     public int getRoomID(String roomNumber) {
    int roomId = -1; // Default value or error code if room ID is not found
    dbconnect db = new dbconnect(); // Initialize your database connection

    try {
        String query = "SELECT roomID FROM tbl_room WHERE roomNo = ?";
        PreparedStatement pst = db.connect.prepareStatement(query);
        pst.setString(1, roomNumber);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            roomId = rs.getInt("roomID");
        }

        rs.close();
        pst.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
        // Handle the exception or log it as needed
    }

    return roomId;
    
    
}
     
     
     private void resetComponents() {
    // Reset text fields and combo boxes to default values
    rt2.setText(""); // Assuming rt2 is your JTextField
    rt3.setText(""); // Assuming rt3 is your JTextField
    cout.setDate(null); // Assuming cout is your JDateChooser
    txtrt.setSelectedIndex(0); // Reset combo box selection
    txtrno.setSelectedIndex(0); // Reset combo box selection
    tprc.setText("0"); // Set total payment to zero
    id.setText("");
    rt5.setText("");
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
        jLabel8 = new javax.swing.JLabel();
        txttime = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtdate = new javax.swing.JLabel();
        vd = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        p4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        pw = new javax.swing.JTextField();
        roomid = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        rt2 = new javax.swing.JTextField();
        conv = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        rt3 = new javax.swing.JTextField();
        rt5 = new javax.swing.JTextField();
        namev = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
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
        jLabel4 = new javax.swing.JLabel();
        cout = new com.toedter.calendar.JDateChooser();
        p10 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        tprc = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        id = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cin = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        srch = new javax.swing.JLabel();
        srchf = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        vd1 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        p11 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 153, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Copperplate Gothic Bold", 1, 40)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Check-In");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 251, 69));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setText("BACK");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 20, 73, 30));

        jLabel8.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 204, 204));
        jLabel8.setText("Time");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 0, -1, 28));

        txttime.setFont(new java.awt.Font("Verdana", 1, 20)); // NOI18N
        txttime.setText("0");
        jPanel1.add(txttime, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 30, -1, 28));

        jLabel25.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(204, 204, 204));
        jLabel25.setText("Date");
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(593, 0, -1, 28));

        txtdate.setFont(new java.awt.Font("Verdana", 1, 20)); // NOI18N
        txtdate.setText("0");
        jPanel1.add(txtdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(603, 30, -1, 28));

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1310, -1));

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

        jPanel2.add(vd, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 360, 220, 60));

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

        jPanel2.add(p4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 390, 280, 60));

        jLabel10.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel10.setText("Name:");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 92, 28));

        pw.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel2.add(pw, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 90, -1, -1));

        roomid.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jPanel2.add(roomid, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 100, 92, 28));

        jLabel5.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel5.setText("Contact no.:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 110, 28));

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel3.setText("Room No.:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 210, 92, 28));

        jLabel14.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel14.setText("Room Price:");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 260, -1, 28));

        rt2.setBackground(new java.awt.Color(204, 255, 255));
        rt2.setFont(rt2.getFont().deriveFont(rt2.getFont().getSize()+3f));
        rt2.setBorder(null);
        rt2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rt2ActionPerformed(evt);
            }
        });
        jPanel2.add(rt2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 210, 260, 40));

        conv.setForeground(new java.awt.Color(255, 0, 0));
        conv.setToolTipText("");
        jPanel2.add(conv, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 310, 260, -1));

        jLabel20.setForeground(new java.awt.Color(51, 51, 51));
        jLabel20.setText("_________________________________________");
        jPanel2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 280, 250, 50));

        rt3.setBackground(new java.awt.Color(204, 255, 255));
        rt3.setFont(rt3.getFont().deriveFont(rt3.getFont().getSize()+3f));
        rt3.setBorder(null);
        rt3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rt3ActionPerformed(evt);
            }
        });
        jPanel2.add(rt3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 270, 260, 40));

        rt5.setBackground(new java.awt.Color(204, 255, 255));
        rt5.setFont(rt5.getFont().deriveFont(rt5.getFont().getSize()+3f));
        rt5.setBorder(null);
        rt5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rt5ActionPerformed(evt);
            }
        });
        jPanel2.add(rt5, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 250, 240, 40));

        namev.setForeground(new java.awt.Color(255, 0, 0));
        jPanel2.add(namev, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 250, 250, -1));

        jLabel19.setForeground(new java.awt.Color(51, 51, 51));
        jLabel19.setText("_________________________________________");
        jPanel2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 220, 250, 50));

        jLabel21.setForeground(new java.awt.Color(51, 51, 51));
        jLabel21.setText("_________________________________________");
        jPanel2.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 260, 250, 50));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

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

        jPanel2.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 480, 1230, 420));
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
        jPanel2.add(txtrt, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 150, 250, 40));

        txtrno.setBackground(new java.awt.Color(204, 255, 255));
        txtrno.setEditable(true);
        txtrno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtrnoActionPerformed(evt);
            }
        });
        jPanel2.add(txtrno, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 200, 250, 40));

        jLabel4.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel4.setText("Check-Out");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 240, 92, 28));

        cout.setDateFormatString("dd MMM yyyy");
        cout.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cout.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                coutComponentAdded(evt);
            }
        });
        cout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                coutMouseClicked(evt);
            }
        });
        cout.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                coutKeyTyped(evt);
            }
        });
        jPanel2.add(cout, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 230, 240, 40));

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

        jPanel2.add(p10, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 390, 170, 60));

        tprc.setBackground(new java.awt.Color(204, 255, 255));
        tprc.setFont(tprc.getFont().deriveFont(tprc.getFont().getSize()+3f));
        tprc.setBorder(null);
        tprc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tprcActionPerformed(evt);
            }
        });
        jPanel2.add(tprc, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 300, 240, 40));

        jLabel27.setForeground(new java.awt.Color(51, 51, 51));
        jLabel27.setText("_________________________________________");
        jPanel2.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 310, 250, 50));

        jLabel17.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel17.setText("Transaction ID:");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 120, 28));

        id.setEditable(false);
        id.setBackground(new java.awt.Color(204, 255, 255));
        id.setFont(id.getFont().deriveFont(id.getFont().getSize()+3f));
        id.setBorder(null);
        id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idActionPerformed(evt);
            }
        });
        jPanel2.add(id, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 160, 220, 40));

        jLabel28.setForeground(new java.awt.Color(51, 51, 51));
        jLabel28.setText("_________________________________________");
        jPanel2.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, 210, 50));

        jLabel7.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel7.setText("Check-in");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 180, 70, 20));

        cin.setEditable(false);
        cin.setBackground(new java.awt.Color(204, 255, 255));
        cin.setFont(cin.getFont().deriveFont(cin.getFont().getSize()+3f));
        cin.setText(" ");
        cin.setBorder(null);
        cin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cinActionPerformed(evt);
            }
        });
        jPanel2.add(cin, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 160, 220, 40));

        jLabel29.setForeground(new java.awt.Color(51, 51, 51));
        jLabel29.setText("_________________________________________");
        jPanel2.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 170, 210, 50));

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
        jPanel9.add(srchf, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 220, 20));

        jPanel2.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 450, 270, 40));

        jLabel9.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel9.setText("Room type:");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 160, 92, 28));

        jLabel18.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel18.setText("Room ID:");
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 100, 92, 28));

        vd1.setBackground(new java.awt.Color(204, 255, 255));
        vd1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(51, 255, 255), null));
        vd1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        vd1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vd1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                vd1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                vd1MouseExited(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel16.setText("PAYMENT");

        javax.swing.GroupLayout vd1Layout = new javax.swing.GroupLayout(vd1);
        vd1.setLayout(vd1Layout);
        vd1Layout.setHorizontalGroup(
            vd1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vd1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        vd1Layout.setVerticalGroup(
            vd1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, vd1Layout.createSequentialGroup()
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2.add(vd1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 310, 90, 30));

        p11.setBackground(new java.awt.Color(102, 102, 102));
        p11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        p11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p11MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                p11MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                p11MouseExited(evt);
            }
        });

        jLabel30.setBackground(new java.awt.Color(0, 102, 153));
        jLabel30.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("CLEAR");

        javax.swing.GroupLayout p11Layout = new javax.swing.GroupLayout(p11);
        p11.setLayout(p11Layout);
        p11Layout.setHorizontalGroup(
            p11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                .addContainerGap())
        );
        p11Layout.setVerticalGroup(
            p11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.add(p11, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 390, -1, 60));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 925, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        Session sess = Session.getInstance();

        // Validate input fields
        if (rt2.getText().isEmpty() || rt3.getText().isEmpty() || tprc.getText().isEmpty() || txtrt.getSelectedItem() == null || txtrno.getSelectedItem() == null || cout.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Please fill in all required fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate name field (rt2) to be a varchar (not integers)
        if (rt2.getText().matches("\\d+")) {
            namev.setText("Name should be a letters, not an integer.");
            return;
        }

        // Validate contact number (rt3) to be integers and exactly eleven digits long
        try {
            long contactNumber = Long.parseLong(rt3.getText());
            if (String.valueOf(contactNumber).length() != 11) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            conv.setText("Contact number should be a valid 11-digit integer.");
            return;
        }

        // Get the current date
        String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        // Validate checkout date (cout) not before the current date
        String selectedCheckoutDate = new SimpleDateFormat("yyyy-MM-dd").format(cout.getDate());
        if (selectedCheckoutDate.compareTo(currentDate) < 0) {
            JOptionPane.showMessageDialog(null, "Checkout date cannot be before the current date.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Your transaction insertion code here
            // Insert data into tbl_transaction
            String insertTransactionQuery = "INSERT INTO tbl_transaction (u_id, roomID, name, contact, check_in, check_out, totalPayment, status)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, 'Active')";
            PreparedStatement insertTransactionPst = db.connect.prepareStatement(insertTransactionQuery);
            insertTransactionPst.setString(1, String.valueOf(sess.getUid())); // Assuming u_id is a string in the database
            insertTransactionPst.setInt(2, Integer.parseInt(txtrno.getSelectedItem().toString())); // Assuming txtrno contains the room ID
            insertTransactionPst.setString(3, rt2.getText());
            insertTransactionPst.setString(4, rt3.getText());
            insertTransactionPst.setString(5, currentDate); // Use current date as check-in date
            insertTransactionPst.setString(6, selectedCheckoutDate); // Use selected checkout date
            insertTransactionPst.setDouble(7, Double.parseDouble(txtrt.getSelectedItem().toString().replace("PHP ", ""))); // Remove "PHP " prefix and parse as double

            int rowsInserted = insertTransactionPst.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Transaction details inserted into tbl_transaction.");

                // Update r_status in tbl_room to 'Booked'
                String updateRoomQuery = "UPDATE tbl_room SET r_status = 'Booked' WHERE roomID = ?";
                PreparedStatement updateRoomPst = db.connect.prepareStatement(updateRoomQuery);
                updateRoomPst.setInt(1, Integer.parseInt(txtrno.getSelectedItem().toString())); // Assuming txtrno contains the room ID

                int rowsUpdated = updateRoomPst.executeUpdate();

                if (rowsUpdated > 0) {
                    System.out.println("Room status updated to 'Booked' in tbl_room.");
                } else {
                    System.out.println("Failed to update room status in tbl_room.");
                }

                updateRoomPst.close();

                // Display success message and update GUI
                JOptionPane.showMessageDialog(null, "Checked In");
                DefaultTableModel model = (DefaultTableModel) cosTable.getModel();
                model.setRowCount(0);
                displayData();

                manageRooms mrm = new manageRooms();
                mrm.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Failed to insert transaction details.");
            }

            insertTransactionPst.close();
        } catch (SQLException | NumberFormatException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error processing transaction.");
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

if (row < 0) {
    JOptionPane.showMessageDialog(null, "Please select a row.");
} else {
    TableModel mod = cosTable.getModel();
    try {
        // Get the transID from the selected row
          int transID = Integer.parseInt(mod.getValueAt(row, 0).toString()); // Assuming transID is in column 0

        // Fetch the transaction details based on transID
        ResultSet rs = db.getData("SELECT * FROM tbl_transaction WHERE transID = '" + transID + "'");

        if (rs.next()) {
            int roomId = rs.getInt("roomID");
            int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to void the transaction?", "Confirm", JOptionPane.YES_NO_OPTION);

            if (confirmation == JOptionPane.YES_OPTION) {
                // Delete the row from tbl_transaction based on transID
                db.delete(transID, "tbl_transaction", "transID", false);

                // Update room status to 'Active'
                db.updateData("UPDATE tbl_room SET r_status = 'Active' WHERE roomID = " + roomId, false);

                // Refresh the table data
                DefaultTableModel model = (DefaultTableModel) cosTable.getModel();
                model.setRowCount(0); // Clear existing rows
                displayData(); // Reload data
            }
        } else {
            JOptionPane.showMessageDialog(null, "Transaction details not found.");
        }
    } catch (SQLException ex) {
        Logger.getLogger(manageRooms.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null, "Error processing request.");
    }
}
    }//GEN-LAST:event_vdMouseClicked

    private void rt2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rt2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rt2ActionPerformed

    private void rt3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rt3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rt3ActionPerformed

    private void jLabel13AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jLabel13AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel13AncestorAdded

    private void txtrtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtrtActionPerformed
        dbconnect db = new dbconnect();
        String rts = txtrt.getSelectedItem().toString();
        txtrno.removeAllItems();

    try {
        String query = "SELECT roomNo, r_price FROM tbl_room WHERE roomType = ? AND r_status = 'Active'";
        PreparedStatement pst = db.connect.prepareStatement(query);
        pst.setString(1, rts);
        ResultSet res = pst.executeQuery();

        while (res.next()) {
            txtrno.addItem(res.getString("roomNo"));
            rt5.setText(res.getString("r_price"));
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

        if (row < 0) {
            JOptionPane.showMessageDialog(null, "Please select a row.");
        } else {
            TableModel model = cosTable.getModel();
            if (model != null) {
                try {
                    String u_id = model.getValueAt(row, 2).toString(); // Assuming column index 2 contains u_id

                    if (u_id != null && !u_id.isEmpty()) { // Check if u_id is not null or empty
                        String sql = "SELECT t.transID, t.roomID, t.u_id, t.name, t.Contact, r.roomType, r.roomNo, t.check_in, t.check_out, t.totalpayment, t.status " +
                                     "FROM tbl_transaction t " +
                                     "JOIN tbl_room r ON t.roomID = r.roomID " +
                                     "WHERE t.u_id = ?"; // Use parameterized query to avoid SQL injection

                        PreparedStatement pst = db.connect.prepareStatement(sql);
                        pst.setString(1, u_id);
                        ResultSet rs = pst.executeQuery();

                        if (rs != null && rs.next()) {
                            id.setText(String.valueOf(rs.getInt("transID")));
                            rt2.setText(rs.getString("name")); 
                            rt3.setText(rs.getString("contact"));
                            txtrt.setSelectedItem(rs.getString("roomType"));
                            txtrno.setSelectedItem(rs.getString("roomNo"));

                            // Assuming 'check_in' and 'check_out' are of type java.sql.Date or java.util.Date
                            Date checkInDate = rs.getDate("check_in");
                            Date checkOutDate = rs.getDate("check_out");
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
                            if (checkInDate != null) {
                                cin.setText(dateFormat.format(checkInDate));
                            }
                            if (checkOutDate != null) {
                                cout.setDate(checkOutDate);
                            }

                            // Assuming 'totalpayment' is of type double
                            double totalPayment = rs.getDouble("totalpayment");
                            tprc.setText(String.format("PHP %.2f", totalPayment));

                            // Assuming 'status' is a String field
                            // Set any other fields you need here

                        } else {
                            JOptionPane.showMessageDialog(null, "No data found for the selected user ID.");
                        }

                        rs.close();
                        pst.close();
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid user ID.");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error retrieving data from the database.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Table model is null.");
            }
        }
    }//GEN-LAST:event_p10MouseClicked

    private void p10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p10MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_p10MouseEntered

    private void p10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p10MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_p10MouseExited

    private void tprcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tprcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tprcActionPerformed

    private void idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idActionPerformed

    private void cinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cinActionPerformed

    private void srchfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_srchfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_srchfActionPerformed

    private void srchfMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_srchfMouseEntered
    
    }//GEN-LAST:event_srchfMouseEntered

    private void srchfMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_srchfMouseExited
    
    }//GEN-LAST:event_srchfMouseExited

    private void srchfMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_srchfMouseReleased
      srch.setText("Search");
    }//GEN-LAST:event_srchfMouseReleased

    private void srchfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_srchfKeyPressed
       
    }//GEN-LAST:event_srchfKeyPressed

    private void srchfKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_srchfKeyTyped
        srch.setText("");
    }//GEN-LAST:event_srchfKeyTyped

    private void srchfKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_srchfKeyReleased
        
    }//GEN-LAST:event_srchfKeyReleased

    private void coutComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_coutComponentAdded
            
        
        String roomType = txtrt.getSelectedItem().toString();
        String roomNumber = txtrno.getSelectedItem().toString();
        Date checkInDate = new Date();
        Date checkOutDate = null;

        try 
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            checkOutDate = cout.getDate();
            
        } catch (Exception ex) 
        {
            ex.printStackTrace();
        }


        if (!roomType.isEmpty() && !roomNumber.isEmpty() && checkOutDate != null) 
        {

            if (checkOutDate.after(checkInDate)) 
            {

                calculateTotalPayment();
            } 
            else 
            {

                tprc.setText("Check-out date must be after check-in date");

                cout.setDate(null);
            }
        } 
        else 
        {

            if (roomType.isEmpty()) 
            {

                tprc.setText("Please select a room type");
            } else if (roomNumber.isEmpty()) 
            {

                tprc.setText("Please select a room number");
            } else if (checkOutDate == null) 
            {

                tprc.setText("Please select a check-out date");
            }
        }


       
    }//GEN-LAST:event_coutComponentAdded

    private void txtrnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtrnoActionPerformed
            
     String selectedRoomNo = txtrno.getSelectedItem().toString(); // Get the selected room number

        if (!selectedRoomNo.isEmpty()) {
            dbconnect db = new dbconnect();

            // Check if the database connection is valid
            if (db.connect != null) {
                String query = "SELECT roomID FROM tbl_room WHERE roomNo = ?";
                try {
                    PreparedStatement pst = db.connect.prepareStatement(query);
                    pst.setString(1, selectedRoomNo);
                    ResultSet rs = pst.executeQuery();

                    if (rs.next()) {
                        int roomID = rs.getInt("roomID");
                        roomid.setText(String.valueOf(roomID)); // Set roomID in roomid text field
                    } else {
                        roomid.setText("Room ID not found");
                    }

                    rs.close();
                    pst.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    roomid.setText("Error fetching room ID");
                }
            } else {
                roomid.setText("Database connection error");
            }
        } else {
            roomid.setText("Please select a room number");
        }
    
    }//GEN-LAST:event_txtrnoActionPerformed

    private void coutKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_coutKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_coutKeyTyped

    private void coutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_coutMouseClicked
        
    }//GEN-LAST:event_coutMouseClicked

    private void vd1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vd1MouseClicked
       
        
        String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String selectedCheckoutDate = new SimpleDateFormat("yyyy-MM-dd").format(cout.getDate());
        if (selectedCheckoutDate.compareTo(currentDate) < 0) {
            JOptionPane.showMessageDialog(null, "Checkout date cannot be before the current date.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (cout.getDate() == null || txtrno.getSelectedItem() == null) {
            // Set text to prompt user to select date or room number
            tprc.setText(rt5.getText());
        } else {
            // Calculate and set total payment if both date and room number are selected
            double totalPayment = calculateTotalPayment();
            tprc.setText(String.format("PHP %.2f", totalPayment));
            txtrt.setEnabled(false);
        }
       
     
    }//GEN-LAST:event_vd1MouseClicked

    private void vd1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vd1MouseEntered
        vd1.setBackground(new Color(51,255,255));
    }//GEN-LAST:event_vd1MouseEntered

    private void vd1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vd1MouseExited
       vd1.setBackground(new Color(204,255,255));
    }//GEN-LAST:event_vd1MouseExited

    private void p11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p11MouseClicked
        manageRooms mrm = new manageRooms();
        mrm.setVisible(true);
        this.dispose();
        
       
    }//GEN-LAST:event_p11MouseClicked

    private void p11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p11MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_p11MouseEntered

    private void p11MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p11MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_p11MouseExited

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
    private javax.swing.JTextField cin;
    private javax.swing.JLabel conv;
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
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel namev;
    private javax.swing.JPanel p10;
    private javax.swing.JPanel p11;
    private javax.swing.JPanel p4;
    private javax.swing.JPanel p5;
    public javax.swing.JTextField pw;
    private javax.swing.JLabel roomid;
    private javax.swing.JTextField rt2;
    private javax.swing.JTextField rt3;
    private javax.swing.JTextField rt5;
    private javax.swing.JLabel srch;
    private javax.swing.JTextField srchf;
    private javax.swing.JTable tableRoom;
    private javax.swing.JTextField tprc;
    private javax.swing.JLabel txtdate;
    private javax.swing.JComboBox<String> txtrno;
    private javax.swing.JComboBox<String> txtrt;
    private javax.swing.JLabel txttime;
    private javax.swing.JPanel vd;
    private javax.swing.JPanel vd1;
    // End of variables declaration//GEN-END:variables
}
