
package configgg;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;




public class dbconnect {
    
    private Connection connect;
    
     public dbconnect(){
            try{
                connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbssample", "root", "");
            }catch(SQLException ex){
                    System.out.println("Can't connect to database: "+ex.getMessage());
            }
        }
     
     //Function to retrieve data
        public ResultSet getData(String sql) throws SQLException{
            Statement stmt = connect.createStatement();
            ResultSet rst = stmt.executeQuery(sql);
            return rst;
        }
    
       
        public boolean insertData(String sql){
            try{
                PreparedStatement pst = connect.prepareStatement(sql);
                pst.executeUpdate();
                 System.out.println("Inserted Successfully!");
                pst.close();
               return true;
            }catch(SQLException ex){
                System.out.println("Connection Error: "+ex);
               return false;
            }
        }
        

    
     //Function to update data
        public void updateData(String sql, boolean show){
            try{
                PreparedStatement pst = connect.prepareStatement(sql);
                    int rowsUpdated = pst.executeUpdate();
                        if(rowsUpdated > 0){
                            if(show == false){
                                System.out.println("Updated");
                            
                                 JOptionPane.showMessageDialog(null, "Data Updated Successfully!");
                            }
                           
                        }else{
                            System.out.println("Data Update Failed!");
                        }
                        pst.close();
            }catch(SQLException ex){
                System.out.println("Connection Error: "+ex);
            }
        
        }
        
        public void updateData(String sql){
            this.updateData(sql, true);
        }
        
        
        public void delete(int id, String table, String tblid, boolean show){
            try{
                
                PreparedStatement pst = connect.prepareStatement("DELETE FROM "+table+" WHERE "+tblid+" = ? ");
                pst.setInt(1, id);
                int row = pst.executeUpdate();
                if(row > 0 ){
                    if(show == false){
                        System.out.println("DELETED");
                    }else{
                         JOptionPane.showMessageDialog(null,"Deleted");
                    }
                   
                }else{
                    System.out.println("Failed");
                }
                
            }catch(SQLException ex){
                
                JOptionPane.showMessageDialog(null,"Error" + ex.getMessage());
            }
        }
        
        public void delete(int id, String table, String tblid){
            this.delete(id, table, tblid, true);
        }
  }



