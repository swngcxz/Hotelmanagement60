/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import admin.changePass;
import canillasapp.loginForm;
import configgg.Session;
import configgg.dbconnect;
import configgg.passwordHasher;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import static register.regDash.email;
import static register.regDash.usname;

/**
 *
 * @author Josh
 */
public class userForm extends javax.swing.JFrame {

    /**
     * Creates new form userForm
     */
    public userForm() {
        initComponents();
    }
    
    Color navcolor = new Color(102,0,0);
    Color hovercolor = new Color(255,0,0);
    
    public String destination ="";
    File selectedFile;
    public String oldpath;
    public String path;
    
    public int FileExistenceChecker(String path){
        File file = new File(path);
        String fileName = file.getName();
        
        Path filePath = Paths.get("src/userimages", fileName);
        boolean fileExists = Files.exists(filePath);
        
        if (fileExists) {
            return 1;
        } else {
            return 0;
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
    
       public void imageUpdater(String existingFilePath, String newFilePath){
        File existingFile = new File(existingFilePath);
        if (existingFile.exists()) {
            String parentDirectory = existingFile.getParent();
            File newFile = new File(newFilePath);
            String newFileName = newFile.getName();
            File updatedFile = new File(parentDirectory, newFileName);
            existingFile.delete();
            try {
                Files.copy(newFile.toPath(), updatedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Image updated successfully.");
            } catch (IOException e) {
                System.out.println("Error occurred while updating the image: "+e);
            }
        } else {
            try{
                Files.copy(selectedFile.toPath(), new File(destination).toPath(), StandardCopyOption.REPLACE_EXISTING);
            }catch(IOException e){
                System.out.println("Error on update!");
            }
        }
   }

    
    public boolean duplicateCheck(){
        
        dbconnect dbc = new dbconnect();
        
        try{
            String query="SELECT * FROM TBL_USER WHERE u_username = '"+un.getText()+"' OR u_email ='"+em.getText()+"'";
            ResultSet rs = dbc.getData(query);
            
            
            if(rs.next())
            {
               email = rs.getString("u_email");
                System.out.println(""+email);
                if(email.equals(em.getText()))
                {
                    JOptionPane.showMessageDialog(null, "Email is already used!");
                }
                em.setText("");
                
               usname = rs.getString("u_username");
                System.out.println(""+usname);
                if(usname.equals(un.getText()))
                {
                    JOptionPane.showMessageDialog(null, "Username is already used!");
                }
                un.setText("");
               return true;

            }
            else
            {
                return false;
            }
        }
        catch(SQLException ex)
            {
                System.out.println(""+ex);
                return false;
            }
        
    }
    
     public boolean updateCheck(){
        
        dbconnect dbc = new dbconnect();
        
        try{
            String query="SELECT * FROM TBL_USER WHERE (u_username = '"+un.getText()+"' OR u_email ='"+em.getText()+"') AND u_id != '"+uida.getText()+"'";
            ResultSet rs = dbc.getData(query);
            
            
            if(rs.next())
            {
               email = rs.getString("u_email");
                System.out.println(""+email);
                if(email.equals(em.getText()))
                {
                    JOptionPane.showMessageDialog(null, "Email is already used!");
                }
                em.setText("");
                
               usname = rs.getString("u_username");
                System.out.println(""+usname);
                if(usname.equals(un.getText()))
                {
                    JOptionPane.showMessageDialog(null, "Username is already used!");
                }
                un.setText("");
               return true;

            }
            else
            {
                return false;
            }
        }
        catch(SQLException ex)
            {
                System.out.println(""+ex);
                return false;
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

        rfh1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        uida = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        em = new javax.swing.JTextField();
        un = new javax.swing.JTextField();
        at = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        ln = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        us = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        fn = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        image = new javax.swing.JLabel();
        select = new javax.swing.JButton();
        remove = new javax.swing.JButton();
        ud = new javax.swing.JButton();
        add = new javax.swing.JButton();
        clr = new javax.swing.JButton();
        cnl = new javax.swing.JButton();
        rfh = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        acc_name = new javax.swing.JLabel();
        acc_lname = new javax.swing.JLabel();
        cp = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        uid = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        img = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        p1 = new javax.swing.JPanel();
        show = new javax.swing.JLabel();
        disable = new javax.swing.JLabel();

        rfh1.setBackground(new java.awt.Color(0, 51, 204));
        rfh1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        rfh1.setText("Refreh");
        rfh1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rfh1ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(35, 87, 117));

        jLabel11.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("   ACCOUNT INFORMATION");
        jLabel11.setToolTipText("");

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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 876, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
                .addGap(54, 54, 54))
        );

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));
        jPanel1.setForeground(new java.awt.Color(204, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        uida.setBackground(new java.awt.Color(204, 255, 255));
        uida.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        uida.setBorder(null);
        uida.setEnabled(false);
        uida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uidaActionPerformed(evt);
            }
        });
        jPanel1.add(uida, new org.netbeans.lib.awtextra.AbsoluteConstraints(372, 39, 246, 36));

        jLabel8.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel8.setText("Account type:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(262, 391, -1, 28));

        jLabel4.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel4.setText("Last Name:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(262, 159, 92, 28));

        em.setBackground(new java.awt.Color(204, 255, 255));
        em.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        em.setBorder(null);
        jPanel1.add(em, new org.netbeans.lib.awtextra.AbsoluteConstraints(372, 207, 246, 36));

        un.setBackground(new java.awt.Color(204, 255, 255));
        un.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        un.setBorder(null);
        jPanel1.add(un, new org.netbeans.lib.awtextra.AbsoluteConstraints(372, 263, 246, 38));

        at.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        at.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "User" }));
        jPanel1.add(at, new org.netbeans.lib.awtextra.AbsoluteConstraints(368, 381, 250, 38));

        jLabel6.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel6.setText("Username:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(262, 272, 92, 29));

        ln.setBackground(new java.awt.Color(204, 255, 255));
        ln.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ln.setBorder(null);
        jPanel1.add(ln, new org.netbeans.lib.awtextra.AbsoluteConstraints(372, 151, 246, 36));

        jLabel5.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel5.setText("E-mail:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(262, 215, 92, 28));

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel3.setText("User ID:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(262, 47, 92, 28));

        us.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        us.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Pending" }));
        us.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usActionPerformed(evt);
            }
        });
        jPanel1.add(us, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 439, 250, 37));

        jLabel9.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel9.setText("Status:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(262, 448, -1, 28));

        fn.setBackground(new java.awt.Color(204, 255, 255));
        fn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        fn.setBorder(null);
        jPanel1.add(fn, new org.netbeans.lib.awtextra.AbsoluteConstraints(372, 95, 246, 36));

        jLabel10.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel10.setText("First Name:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(262, 103, 92, 28));

        jLabel15.setText("_________________________________________");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 270, 250, 60));

        jLabel14.setText("_________________________________________");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 330, 250, 50));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        image.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(image, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(image, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 50, -1, -1));

        select.setBackground(new java.awt.Color(0, 51, 204));
        select.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        select.setText("Select");
        select.setBorder(null);
        select.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectMouseClicked(evt);
            }
        });
        select.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectActionPerformed(evt);
            }
        });
        jPanel1.add(select, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 360, 94, 40));

        remove.setBackground(new java.awt.Color(0, 51, 204));
        remove.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        remove.setText("Remove");
        remove.setBorder(null);
        remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeActionPerformed(evt);
            }
        });
        jPanel1.add(remove, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 360, 118, 40));

        ud.setBackground(new java.awt.Color(0, 51, 204));
        ud.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        ud.setText("Update");
        ud.setEnabled(false);
        ud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                udActionPerformed(evt);
            }
        });
        jPanel1.add(ud, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 500, 94, -1));

        add.setBackground(new java.awt.Color(0, 51, 204));
        add.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        add.setText("ADD");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });
        jPanel1.add(add, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 500, 94, -1));

        clr.setBackground(new java.awt.Color(0, 51, 204));
        clr.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        clr.setText("Clear");
        clr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clrActionPerformed(evt);
            }
        });
        jPanel1.add(clr, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 500, 94, -1));

        cnl.setBackground(new java.awt.Color(0, 51, 204));
        cnl.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        cnl.setText("Cancel");
        cnl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cnlActionPerformed(evt);
            }
        });
        jPanel1.add(cnl, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 500, 94, -1));

        rfh.setBackground(new java.awt.Color(0, 51, 204));
        rfh.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        rfh.setText("Refreh");
        rfh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rfhActionPerformed(evt);
            }
        });
        jPanel1.add(rfh, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 500, 94, -1));

        jPanel3.setBackground(new java.awt.Color(95, 126, 149));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        acc_name.setFont(new java.awt.Font("Lucida Console", 1, 24)); // NOI18N
        acc_name.setForeground(new java.awt.Color(255, 255, 255));
        acc_name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        acc_name.setText("fname");
        jPanel3.add(acc_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 230, 40));

        acc_lname.setFont(new java.awt.Font("Lucida Console", 1, 24)); // NOI18N
        acc_lname.setForeground(new java.awt.Color(255, 255, 255));
        acc_lname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        acc_lname.setText("lname");
        jPanel3.add(acc_lname, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 230, 40));

        cp.setBackground(new java.awt.Color(102, 0, 0));
        cp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cpMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cpMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cpMouseExited(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Change Pass");

        javax.swing.GroupLayout cpLayout = new javax.swing.GroupLayout(cp);
        cp.setLayout(cpLayout);
        cpLayout.setHorizontalGroup(
            cpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cpLayout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        cpLayout.setVerticalGroup(
            cpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel3.add(cp, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 470, 180, 40));

        uid.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        uid.setForeground(new java.awt.Color(255, 255, 255));
        uid.setText("UID");
        jPanel3.add(uid, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 190, 43));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        img.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(img, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(img, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 210, 170));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 236, 603));

        jLabel7.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel7.setText("Password:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(262, 333, 92, 28));

        password.setBackground(new java.awt.Color(204, 255, 255));
        password.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        password.setBorder(null);
        password.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordActionPerformed(evt);
            }
        });
        jPanel1.add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 320, 250, 40));

        jLabel16.setText("_________________________________________");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 210, 250, 60));

        jLabel17.setText("_________________________________________");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 150, 250, 70));

        jLabel18.setText("_________________________________________");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 100, 250, 60));

        jLabel19.setText("_________________________________________");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 40, 250, 60));

        p1.setBackground(new java.awt.Color(153, 153, 153));

        show.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        show.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_eye_20px_1.png"))); // NOI18N
        show.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        show.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showMouseClicked(evt);
            }
        });

        disable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        disable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_invisible_20px_1.png"))); // NOI18N
        disable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        disable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                disableMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout p1Layout = new javax.swing.GroupLayout(p1);
        p1.setLayout(p1Layout);
        p1Layout.setHorizontalGroup(
            p1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(disable, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(p1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(p1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(show, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        p1Layout.setVerticalGroup(
            p1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(disable, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(p1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(p1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(show, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel1.add(p1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 310, 50, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed

        if(fn.getText().isEmpty() || ln.getText().isEmpty() || em.getText().isEmpty() || un.getText().isEmpty() || password.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "All fields must not be empty!");
        }
        
        else if(password.getText().length() < 8)
        {
            JOptionPane.showMessageDialog(null, "Password character should be above 8");
            password.setText("");
        }
        else if(duplicateCheck())
        {
            JOptionPane.showMessageDialog(null, "Duplicate exist!");
        }
   
        else{   
            
            dbconnect dbc = new dbconnect();
            
            try
            {
            String pass = passwordHasher.hashPassword(password.getText());

            if(dbc.insertData("INSERT INTO tbl_user(u_fname, u_lname, u_email, u_username, u_password, u_type, u_status, u_image) "
                    + "VALUES ('"+fn.getText()+"', '"+ln.getText()+"', '"+em.getText()+"', '"+un.getText()+"', '"+pass+"', '"+at.getSelectedItem()+"', '"+us.getSelectedItem()+"', 'null') "))
            {
                int l = JOptionPane.showConfirmDialog(null, "Registration Complete! /nDo you Want to login?", "Select", JOptionPane.YES_NO_OPTION);
                if(l == 0){
                    loginForm lgn = new loginForm();
                    lgn.setVisible(true);
                    this.dispose();
                    
                }
                else
                {
                    userDash usd = new userDash();
                    usd.setVisible(true);
                    this.dispose();
                }
                

            }
            else{
                 JOptionPane.showMessageDialog(null, "Connection Error!");
            }
            }catch(NoSuchAlgorithmException ex)
            {
                System.out.println(""+ex);
            }
        }
    }//GEN-LAST:event_addActionPerformed

    private void udActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_udActionPerformed
       
        if( fn.getText().isEmpty() ||ln.getText().isEmpty() || em.getText().isEmpty() || un.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "All fields must not be empty!");
        }

        
        else if(updateCheck())
        {
            JOptionPane.showMessageDialog(null, "Duplicate exist!");
        }

        else
        {
        
           dbconnect dbc = new dbconnect();
           dbc.updateData("UPDATE tbl_user SET u_fname = '"+fn.getText()+"', u_lname = '"+ln.getText()+"', u_email= '"+em.getText()+"',"
                   + " u_username = '"+un.getText()+"',"
                           + " u_type = '"+at.getSelectedItem()+"', u_status = '"+us.getSelectedItem()+"', u_image = '"+destination+"' WHERE u_id = '"+uida.getText()+"'");
           
           
           if(destination.isEmpty())
           {
               File existingFile = new File(oldpath);
               if(existingFile.exists())
               {
                   existingFile.delete();
               }
           }else
           {
               if(!(oldpath.equals(path)))
               {
                   imageUpdater(oldpath, path);
               }
           }
           
           userDash ud = new userDash();
           ud.setVisible(true);
           this.dispose();
           
        }
    }//GEN-LAST:event_udActionPerformed

    private void clrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clrActionPerformed
        
        fn.setText("");
        ln.setText("");
        em.setText("");
        
        
    }//GEN-LAST:event_clrActionPerformed

    private void cnlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cnlActionPerformed
        userDash usd = new userDash();
        usd.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_cnlActionPerformed

    private void rfhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rfhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rfhActionPerformed

    private void rfh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rfh1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rfh1ActionPerformed

    private void selectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectActionPerformed
        JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    try {
                        selectedFile = fileChooser.getSelectedFile();
                        destination = "src/userimages/" + selectedFile.getName();
                        path  = selectedFile.getAbsolutePath();
                        
                        
                        if(FileExistenceChecker(path) == 1){
                          JOptionPane.showMessageDialog(null, "File Already Exist, Rename or Choose another!");
                            destination = "";
                            path="";
                        }else{
                            image.setIcon(ResizeImage(path, null, image));
                            select.setEnabled(false);
                            remove.setEnabled(true);
                            
                        }
                    } catch (Exception ex) {
                        System.out.println("File Error!");
                    }
                }
    }//GEN-LAST:event_selectActionPerformed

    private void removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeActionPerformed
       remove.setEnabled(false);
       select.setEnabled(true);
       image.setIcon(null);
       destination ="";
       path ="";
    }//GEN-LAST:event_removeActionPerformed

    private void selectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectMouseClicked
        

    }//GEN-LAST:event_selectMouseClicked

    private void cpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cpMouseClicked
         
        changePass cp = new changePass();
        cp.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_cpMouseClicked

    private void cpMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cpMouseEntered
        cp.setBackground(hovercolor);
    }//GEN-LAST:event_cpMouseEntered

    private void cpMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cpMouseExited
        cp.setBackground(navcolor);
    }//GEN-LAST:event_cpMouseExited

    private void usActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
         Session sess = Session.getInstance();
        dbconnect db = new dbconnect();
        
        if(sess.getUid() == 0)
        {
            JOptionPane.showMessageDialog(null, "no account found, Login First!");
            loginForm lf = new loginForm();
            lf.setVisible(true);
            this.dispose();
        }
        else
        {
            uid.setText("USERID "+sess.getUid());
           acc_name.setText(""+sess.getFname());
           acc_lname.setText(""+sess.getLname());
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

    private void disableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_disableMouseClicked
        password.setEchoChar((char)0);
        disable.setVisible(false);
        disable.setEnabled(false);
        show.setEnabled(true);
        show.setEnabled(true);
    }//GEN-LAST:event_disableMouseClicked

    private void showMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showMouseClicked
        password.setEchoChar((char)8226);
        disable.setVisible(true);
        disable.setEnabled(true);
        show.setEnabled(false);
        show.setEnabled(false);
    }//GEN-LAST:event_showMouseClicked

    private void passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        userDash adb = new userDash();
        adb.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void uidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_uidaActionPerformed

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
            java.util.logging.Logger.getLogger(userForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(userForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(userForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(userForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new userForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel acc_lname;
    public javax.swing.JLabel acc_name;
    public javax.swing.JButton add;
    public javax.swing.JComboBox<String> at;
    private javax.swing.JButton clr;
    private javax.swing.JButton cnl;
    public javax.swing.JPanel cp;
    public javax.swing.JLabel disable;
    public javax.swing.JTextField em;
    public javax.swing.JTextField fn;
    public javax.swing.JLabel image;
    public javax.swing.JLabel img;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    public javax.swing.JTextField ln;
    public javax.swing.JPanel p1;
    public javax.swing.JPasswordField password;
    public javax.swing.JButton remove;
    private javax.swing.JButton rfh;
    private javax.swing.JButton rfh1;
    public javax.swing.JButton select;
    public javax.swing.JLabel show;
    public javax.swing.JButton ud;
    public javax.swing.JLabel uid;
    public javax.swing.JTextField uida;
    public javax.swing.JTextField un;
    public javax.swing.JComboBox<String> us;
    // End of variables declaration//GEN-END:variables
}
