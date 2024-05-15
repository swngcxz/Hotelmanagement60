/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import canillasapp.loginForm;
import configgg.dbconnect;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
            String query="SELECT * FROM TBL_USER WHERE (u_username = '"+un.getText()+"' OR u_email ='"+em.getText()+"') AND u_id != '"+uid.getText()+"'";
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
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        uid = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        em = new javax.swing.JTextField();
        un = new javax.swing.JTextField();
        pw = new javax.swing.JTextField();
        at = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        ln = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        us = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        fn = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        image = new javax.swing.JLabel();
        select = new javax.swing.JButton();
        remove = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        add = new javax.swing.JButton();
        ud = new javax.swing.JButton();
        del = new javax.swing.JButton();
        clr = new javax.swing.JButton();
        cnl = new javax.swing.JButton();
        rfh = new javax.swing.JButton();

        rfh1.setBackground(new java.awt.Color(0, 51, 204));
        rfh1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        rfh1.setText("Refreh");
        rfh1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rfh1ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 153, 153));

        jLabel7.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel7.setText("Password:");

        uid.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        uid.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel8.setText("Account type:");

        jLabel4.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel4.setText("Last Name:");

        em.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        un.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        pw.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        pw.setEnabled(false);

        at.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        at.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "User" }));

        jLabel6.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel6.setText("Username:");

        ln.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel5.setText("E-mail:");

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel3.setText("User ID:");

        us.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        us.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Pending" }));

        jLabel9.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel9.setText("Status:");

        fn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel10.setText("First Name:");

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
                .addComponent(image, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                .addContainerGap())
        );

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

        remove.setBackground(new java.awt.Color(0, 51, 204));
        remove.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        remove.setText("Remove");
        remove.setBorder(null);
        remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(pw, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(at, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(us, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(un)
                        .addComponent(em)
                        .addComponent(ln, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(uid, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(select, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(remove, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(563, 563, 563))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(uid, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ln, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(em, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(un, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pw, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(at, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(remove, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(select, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(us, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 20, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(102, 0, 0));

        add.setBackground(new java.awt.Color(0, 51, 204));
        add.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        add.setText("ADD");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        ud.setBackground(new java.awt.Color(0, 51, 204));
        ud.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        ud.setText("Update");
        ud.setEnabled(false);
        ud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                udActionPerformed(evt);
            }
        });

        del.setBackground(new java.awt.Color(0, 51, 204));
        del.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        del.setText("Delete");
        del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delActionPerformed(evt);
            }
        });

        clr.setBackground(new java.awt.Color(0, 51, 204));
        clr.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        clr.setText("Clear");
        clr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clrActionPerformed(evt);
            }
        });

        cnl.setBackground(new java.awt.Color(0, 51, 204));
        cnl.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        cnl.setText("Cancel");
        cnl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cnlActionPerformed(evt);
            }
        });

        rfh.setBackground(new java.awt.Color(0, 51, 204));
        rfh.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        rfh.setText("Refreh");
        rfh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rfhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ud, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(del, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(clr, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cnl, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(rfh, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(add)
                    .addComponent(ud)
                    .addComponent(del)
                    .addComponent(clr)
                    .addComponent(cnl)
                    .addComponent(rfh))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 802, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed

        if( fn.getText().isEmpty() ||ln.getText().isEmpty() || em.getText().isEmpty() || un.getText().isEmpty() || pw.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "All fields must not be empty!");
        }

        else if(pw.getText().length() < 8)
        {
            JOptionPane.showMessageDialog(null, "Password character should be above 8");
            pw.setText("");
        }
        else if(duplicateCheck())
        {
            JOptionPane.showMessageDialog(null, "Duplicate exist!");
        }

        else
        {
            
            
                dbconnect dbc = new dbconnect();

                if(dbc.insertData("INSERT INTO tbl_user(u_fname, u_lname, u_email, u_username, u_password, u_type, u_status, u_image) "
                        + "VALUES ('"+fn.getText()+"', '"+ln.getText()+"', '"+em.getText()+"', '"+un.getText()+"', '"+pw.getText()+"',"
                                + " '"+at.getSelectedItem()+"', '"+us.getSelectedItem()+"', '"+destination+"') "))
                {

                    try
                    {
                        Files.copy(selectedFile.toPath(), new File(destination).toPath(),StandardCopyOption.REPLACE_EXISTING);
                        JOptionPane.showMessageDialog(null, "Registration Success!");
                        userDash usd = new userDash();
                        usd.setVisible(true);
                        this.dispose();

                    }
                    catch(IOException ex)
                    {
                        System.out.println("Insert Image Error: "+ex);
                    }
                }
                
                else
                {
                    JOptionPane.showMessageDialog(null, "Connection Error!");
                }
            }
    }//GEN-LAST:event_addActionPerformed

    private void udActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_udActionPerformed
       
        if( fn.getText().isEmpty() ||ln.getText().isEmpty() || em.getText().isEmpty() || un.getText().isEmpty() || pw.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "All fields must not be empty!");
        }

        else if(pw.getText().length() < 8)
        {
            JOptionPane.showMessageDialog(null, "Password character should be above 8");
            pw.setText("");
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
                           + " u_type = '"+at.getSelectedItem()+"', u_status = '"+us.getSelectedItem()+"', u_image = '"+destination+"' WHERE u_id = '"+uid.getText()+"'");
           
           
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

    private void delActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_delActionPerformed

    private void clrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clrActionPerformed
        // TODO add your handling code here:
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
    public javax.swing.JButton add;
    public javax.swing.JComboBox<String> at;
    private javax.swing.JButton clr;
    private javax.swing.JButton cnl;
    private javax.swing.JButton del;
    public javax.swing.JTextField em;
    public javax.swing.JTextField fn;
    public javax.swing.JLabel image;
    private javax.swing.JLabel jLabel10;
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
    public javax.swing.JTextField ln;
    public javax.swing.JTextField pw;
    public javax.swing.JButton remove;
    private javax.swing.JButton rfh;
    private javax.swing.JButton rfh1;
    public javax.swing.JButton select;
    public javax.swing.JButton ud;
    public javax.swing.JTextField uid;
    public javax.swing.JTextField un;
    public javax.swing.JComboBox<String> us;
    // End of variables declaration//GEN-END:variables
}
