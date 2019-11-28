package innovaccersummergeeks;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author abhishek
 */
public class VisitorEntry{
    public final int token;
    public final String host_email, 
                   visitor_name, 
                   visitor_email,
                   visitor_phone,
                   date,
                   check_in,
                   check_out;
    
    public VisitorEntry(int token, 
            String host_email,
            String visitor_name,
            String visitor_email,
            String visitor_phone,
            String date, 
            String check_in,
            String check_out
    ){
        this.token = token;
        this.host_email = host_email;
        this.visitor_name = visitor_name;
        this.visitor_email = visitor_email;
        this.visitor_phone = visitor_phone;
        this.date =  date;
        this.check_in = check_in;
        this.check_out = check_out;
    }
    
    public static String colHeadings(){
        return String.format("%8s %20s %30s %20s %30s %15s %10s %10s", 
                "Token",
                "Host Email",
                "Name", 
                "Email",
                "Phone",
                "Date",
                "Check-in Time",
                "Check-out Time");
    }
    
    public String toString(){
        return String.format("%8d %20s %30s %20s %30s %15s %20s %20s", 
                this.token,
                this.host_email,
                this.visitor_name, 
                this.visitor_email,
                this.visitor_phone, 
                this.date,
                this.check_in,
                this.check_out);
    }
}
