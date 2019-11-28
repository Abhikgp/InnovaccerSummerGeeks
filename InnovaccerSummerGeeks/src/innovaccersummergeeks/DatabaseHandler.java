/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package innovaccersummergeeks;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author abhishek
 */
public class DatabaseHandler {
    public DatabaseHandler(){
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost/InnovaccerDB?","abhi","password");
        }catch(ClassNotFoundException e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }catch(Exception e){
            e.printStackTrace();
        }
        preparedStatement = null;
        resultSet = null;
    }
    
    public void insertHost(Host host){
        try{
            preparedStatement = connect.prepareStatement("INSERT INTO HOST_TABLE (NAME, EMAIL, PHONE) "
                    + "VALUES ("
                    +"\""+host.name+"\","
                    +"\""+host.email+"\","
                    +"\""+host.phone+"\""
                    + ")");
            preparedStatement.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public Host getHost(String email){
        Host v = null;
        try{
            preparedStatement = connect.prepareStatement("SELECT * from HOST_TABLE WHERE EMAIL = \"" + email +"\"");
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {   
                String host_name, host_email, host_phone;
                host_name  = resultSet.getString("NAME");
                host_email = resultSet.getString("EMAIL");
                host_phone = resultSet.getString("PHONE");
                v = new Host(host_name, host_email, host_phone);
            }
       }catch(Exception e){
            e.printStackTrace();
        }
        
        return v;
    }
    
    
    public int insertEntry(VisitorEntry v){
        try{
            String[] s = v.check_in.split(":");
            int check_in_hr =  Integer.parseInt(s[0]);
            int check_in_min = Integer.parseInt(s[1]);
            int check_out_hr = -1;
            int check_out_min = -1;
            
            if(v.check_out != null){
                check_out_hr = Integer.parseInt(v.check_out.split(":")[0]);
                check_out_min = Integer.parseInt(v.check_out.split(":")[1]);
            }
            
            preparedStatement = connect.prepareStatement("INSERT INTO VISITOR_ENTRY (HOST_EMAIL, NAME, EMAIL, PHONE, ENTRY_DATE, CHECKIN_HOUR, CHECKIN_MIN, CHECKOUT_HOUR, CHECKOUT_MIN) "
                    + "VALUES ("
                    +"\""+v.host_email+"\","
                    +"\""+v.visitor_name+"\","
                    +"\""+v.visitor_email+"\","
                    +"\""+v.visitor_phone+"\","
                    +"\""+v.date+"\","
                    +check_in_hr+","
                    +check_in_min+","
                    +check_out_hr+","
                    +check_out_min
                    + ")", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                int token = resultSet.getInt(1);
                return token;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return -1;
    }
    
    public VisitorEntry getEntry(int token){
        VisitorEntry v = null;
        try{
            preparedStatement = connect.prepareStatement("SELECT * from VISITOR_ENTRY WHERE TOKEN = " + token);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {   
                String host_email, visitor_name, visitor_email, visitor_phone, date, check_in, check_out;
                token = resultSet.getInt("TOKEN");
                host_email = resultSet.getString("HOST_EMAIL");
                visitor_name = resultSet.getString("NAME");
                visitor_email = resultSet.getString("EMAIL");
                visitor_phone = resultSet.getString("PHONE");
                date = resultSet.getString("ENTRY_DATE");
                check_in = String.format("%2d:%2d", resultSet.getInt("CHECKIN_HOUR"), resultSet.getInt("CHECKIN_MIN"));
                check_out = String.format("%2d:%2d", resultSet.getInt("CHECKOUT_HOUR"), resultSet.getInt("CHECKOUT_MIN"));
                v = new VisitorEntry(token, host_email, visitor_name, visitor_email, visitor_phone, date, check_in, check_out);
            }
       }catch(Exception e){
            e.printStackTrace();
        }
        
        return v;
    }
    
    public VisitorEntry[] getEntryList(){
        Vector<VisitorEntry> v = new Vector<VisitorEntry>();
        try{
            preparedStatement = connect.prepareStatement("SELECT * from VISITOR_ENTRY ");
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {   
                int token;
                String host_email, visitor_name, visitor_email, visitor_phone, date, check_in, check_out;
                token = resultSet.getInt("TOKEN");
                host_email = resultSet.getString("HOST_EMAIL");
                visitor_name = resultSet.getString("NAME");
                visitor_email = resultSet.getString("EMAIL");
                visitor_phone = resultSet.getString("PHONE");
                date = resultSet.getString("ENTRY_DATE");
                check_in = String.format("%2d:%2d", resultSet.getInt("CHECKIN_HOUR"), resultSet.getInt("CHECKIN_MIN"));
                check_out = String.format("%2d:%2d", resultSet.getInt("CHECKOUT_HOUR"), resultSet.getInt("CHECKOUT_MIN"));
                v.add(new VisitorEntry(token, host_email, visitor_name, visitor_email, visitor_phone, date, check_in, check_out));
            }
       }catch(Exception e){
            e.printStackTrace();
        }
        
        return v.toArray(new VisitorEntry[v.size()]);
    }
    
    public void updateCheckoutTime(int token, String time){
        try{
            int checkout_hour =  Integer.parseInt(time.split(":")[0]);
            int checkout_min = Integer.parseInt(time.split(":")[1]);
            preparedStatement = connect.prepareStatement("UPDATE VISITOR_ENTRY SET CHECKOUT_HOUR = "+checkout_hour+", CHECKOUT_MIN = "+checkout_min+"  WHERE TOKEN="+token);
            preparedStatement.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private Connection connect;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
}
