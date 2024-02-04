package servlets;
//hey
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import classes.Connectivity;
import classes.VerifyRecaptcha;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author KAMAKSHI
 */
public class RegistrationHandler extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            System.out.println("change reflected again");
            /*String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
            System.out.println(gRecaptchaResponse+ "-"); 
            //boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
            // logging example
            int f=0,se=0,ue=0;
            //System.out.println("::Captcha Verify "+verify);   
            String fname,mname,lname,email,mob,ins,level,eventid,fullname,tmn0,tmn1,tmn2,tmn3,uid="",regId,pubg;
            boolean verify=true;*/
            // logging example
            int f=0,se=0,ue=0,e=0; 
            boolean verify=false;
            String fname,mname,lname,email,mob,ins,level,eventid,fullname,tmn0,tmn1,tmn2,tmn3,uid="",regId,pubg;
            //verify=true;
            /*String gRecaptchaResponse = request.getParameter("response");
            if(gRecaptchaResponse!=null)
            {
                //System.out.println("grecaptcha:"+request.getParameter("g-recaptcha-response"));
                String veri = VerifyRecaptcha.verify(gRecaptchaResponse);
                out.print(veri);
                //System.exit(0);
                e=1;
            }*/
            if(e==0)
            {
            if(request.getParameter("fname")!=null&&request.getParameter("lname")!=null&&request.getParameter("ea")!=null&&request.getParameter("mob")!=null&&request.getParameter("lev")!=null)
            {
                //out.print("ajax request.");
                fname=request.getParameter("fname").trim();
                mname=request.getParameter("mname").trim();
                lname=request.getParameter("lname").trim();
                email=request.getParameter("ea");
                mob=request.getParameter("mob");
                ins=request.getParameter("institute");
                level=request.getParameter("lev");
                eventid=request.getParameter("eve");
                if(eventid.equals("TH"))
                {
                    if(level.equals("school"))
                    {
                        eventid+="S";
                    }
                    else if(level.equals("college"))
                    {
                        eventid+="C";
                    }
                }
                else if(eventid.equals("VWP"))
                {
                    pubg=request.getParameter("pubg");
                    System.out.println(pubg);
                    if(pubg!=null)
                    {
                    switch (pubg) {
                        case "solo":
                            eventid+="S";
                            System.out.println(eventid);
                            break;
                        case "duo":
                            eventid+="D";
                            System.out.println(eventid);
                            break;
                        case "squad":
                            eventid+="SQ";
                            System.out.println(eventid);
                            break;
                        default:
                            break;
                    }
                    }
                }
                f=1;
                System.out.println("ajax call");
                System.out.println(eventid);
            }
            else
            {
                System.out.println("grecaptcha:"+request.getParameter("g-recaptcha-response"));
                String gRecaptchaResponse=request.getParameter("g-recaptcha-response");
                String res=VerifyRecaptcha.verify(gRecaptchaResponse);
                JsonReader jsonReader = Json.createReader(new StringReader(res));
		JsonObject jsonObject = jsonReader.readObject();
		jsonReader.close();
		verify=jsonObject.getBoolean("success");
                System.out.println("output in servlet:"+verify);
                email=request.getParameter("email");
                mob=request.getParameter("mobno");
                ins=request.getParameter("institute");
                level=request.getParameter("level");
                eventid=request.getParameter("event");
                fname=request.getParameter("first_name").trim();
                mname=request.getParameter("middle_name").trim();
                lname=request.getParameter("last_name").trim();
                System.out.println("form submitted.");
            }
            if(mname.trim().isEmpty())
            {
                fullname=fname+" "+lname;
            }
            else
            {
                fullname=fname+" "+mname+" "+lname;
            }
            fullname=fullname.trim();
            //out.print(fname+"<br>"+mname+"<br>"+lname+"<br>"+email+"<br>"+mob+"<br>"+ins+"<br>"+level+"<br>"+eventid+"<br>"+fullname);
            Connectivity conn=new Connectivity();
            Connection con=conn.createConnection();
            PreparedStatement pst=con.prepareStatement("Select id from users where name=? and email=? and mobNo=? and level=? ");//check if the user exists in users table
            pst.setString(1,fullname);
            pst.setString(2,email);
            pst.setString(3,mob);
            pst.setString(4,level);
            ResultSet rs=pst.executeQuery();
            if(rs.next())
            {
                //out.print("uid found ");
                ue=1;
                uid=rs.getString(1);
                System.out.print(uid);
                pst=con.prepareStatement("Select id from registrations where uId=? and eId=? ");
                pst.setString(1,uid);
                pst.setString(2,eventid);
                rs=pst.executeQuery();
                if(rs.next())
                {
                    out.print("User is already registered for the same event.");
                    se=1;
                }
                else
                {
                     System.out.print("User is not registered for this event.");
                }
            }
            if (verify) 
            {
             if(f==0&&ue==0)
            {
                System.out.print("no uid found");
                Statement st=con.createStatement();
                rs=st.executeQuery("SELECT id FROM users ORDER BY ID DESC LIMIT 1");
                rs.next();
                int id=Integer.parseInt(rs.getString(1));
                id+=1;
                uid=String.valueOf(id);
                //uid=rs.getString(1);
                System.out.println("uid="+uid);
                pst=con.prepareStatement("Insert into users values(?,?,?,?,?,?)");
                pst.setString(1,uid);
                pst.setString(2,fullname);
                pst.setString(3,email);
                pst.setString(4,mob);
                pst.setString(5,ins);
                pst.setString(6,level);
                pst.executeUpdate();
                System.out.println("values successfully inserted in users table.");
            }
            //out.print(fname+"<br>"+mname+"<br>"+lname+"<br>"+email+"<br>"+mob+"<br>"+ins+"<br>"+level+"<br>"+eventid+"<br>"+fullname);
            if(f==0&&se==1)
            {
                response.sendRedirect("registration_form.html");
            }
            if(f==0&&se==0)//if the request is not an ajax request and is not for the same event in which the user is already registered.
            {   
                if(eventid.equals("TH"))
                {
                    if(level.equals("school"))
                    {
                        eventid+="S";
                    }
                    else if(level.equals("college"))
                    {
                        eventid+="C";
                    }
                }
                else if(eventid.equals("VWP"))
                {
                    pubg=request.getParameter("pubg");
                    switch (pubg) {
                        case "solo":
                            eventid+="S";
                            break;
                        case "duo":
                            eventid+="D";
                            break;
                        case "squad":
                            eventid+="SQ";
                            break;
                        default:
                            break;
                    }
                }
                pst=con.prepareStatement("update events set totalReg=totalReg+1 where id=?");
                pst.setString(1,eventid);
                pst.execute();
                System.out.println("registration value increased for "+eventid);
                regId=eventid+"2019"+uid;
                pst=con.prepareStatement("Insert into registrations values(?,?,?,?,?)");
                pst.setString(1,regId);
                pst.setString(2,uid);
                pst.setString(3,eventid);
                pst.setString(4,"Online");
                pst.setInt(5,0);
                pst.executeUpdate();
                //out.print("Your Registration id is: "+regId);
                switch (eventid) {
                    case "CG":
                    case "CH":
                    case "BTV":
                    case "VWPD":
                        tmn1=request.getParameter("tmn1");
                        pst=con.prepareStatement("Insert into teammembers values(?,?)");
                        pst.setString(1,regId);
                        pst.setString(2,tmn1);
                        pst.executeUpdate();
                        break;
                    case "HA":
                    case "THS":
                    case "EX":
                    case "VWPSQ":
                        tmn1=request.getParameter("tmn1");
                        tmn2=request.getParameter("tmn2");
                        tmn3=request.getParameter("tmn3");
                        pst=con.prepareStatement("Insert into teammembers values(?,?)");
                        pst.setString(1,regId);
                        if("".equals(tmn3))
                        {
                            pst.setString(2,tmn1+","+tmn2);
                        }
                        else{
                            pst.setString(2,tmn1+","+tmn2+","+tmn3);
                        }
                        pst.executeUpdate();
                        break;
                    case "VWC":
                    case "VWCG":
                    case "THC":
                        tmn1=request.getParameter("tmn1");
                        tmn2=request.getParameter("tmn2");
                        tmn3=request.getParameter("tmn3");
                        tmn0=request.getParameter("tmn0");
                        pst=con.prepareStatement("Insert into teammembers values(?,?)");
                        pst.setString(1,regId);
                        pst.setString(2,tmn0+","+tmn1+","+tmn2+","+tmn3);
                        pst.executeUpdate();
                        break;
                    case "TKT":
                        tmn1=request.getParameter("chessRank");
                        tmn2=request.getParameter("chessID");
                        if(!"".equals(tmn1)&&!"".equals(tmn2))
                        {
                            pst=con.prepareStatement("Insert into chessusers values(?,?,?)");
                            pst.setString(1,regId);
                            pst.setString(2,tmn1);
                            pst.setString(3,tmn2);
                            pst.executeUpdate();
                        }
                        break;
                    default:
                        break;
                    
                } 
                //Display event details
               out.print("<!DOCTYPE html>");
                out.print("<html lang='en'>");
                out.print("<head>");
                out.print("<meta charset='utf-8'>");
                out.print("<meta name='viewport' content='width=device-width, initial-scale=1'>");
                out.print("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css'>");
                out.print("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js'></script>");
                out.print("<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js'></script>");
                out.print("<script src='teamMembers.js'></script>");
                out.print("<style>");
                out.print("body{background-image:url('img/reciept_background.jpg');background-repeat: no-repeat; background-size: cover; }");
                out.print(".container{padding: 10px 10px 15px 10px;width: 600px;background-color: #333333d4; border: 1px solid; margin-top: 10%; border-radius: 10px; box-shadow: 0px 0px 12px 2px #272727;}}");
                out.print("h2 { font-size:35px; font-family:Candara; color:white;}");
                out.print("@media only screen and (max-width: 1020px) {");
                out.print("body{height:100vh;width:100vh;padding-top: 10vh}");
                out.print(".container{margin: 30px;padding: 10px;width: min-content;}}");
                out.print("</style>");
                out.print("</head>");
                out.println("<body style='background-color:black;color:white;'>");
                String event="",price="",team="";
                switch(eventid)
                {
                    case "BTV":
                        event="Bang The Virus";
                        price="100";
                        break;
                    case "CG":
                        event="Codography";
                        price="100";
                        break;
                    case "CH":
                        price="100";
                        event="Cyber Hunt";
                        break;
                    case "COP":
                        price="50";
                        event="Crack-O-Pattern";
                        break;
                    case "EP":
                        if ("school".equals(level))
                            price="50";
                        else
                            price="100";
                        event="Extempore";
                        break;
                    case "EX":
                        event="Exquizite";
                        price="150";
                        break;
                    case "HA":
                        event="Hackathon";
                        price="150";
                        break;
                    case "JC":
                        price="50";
                        event="Jumbled Coding";
                        break;
                    case "MF":
                        price="50";
                        event="Magnum Focus";
                        break;
                    case "PA":
                        price="100";
                        event="Pixel Art";
                        break;
                    case "THC":
                        price="200";
                        event="Techtonic Hunt";
                        break;
                    case "THS":
                        price="150";
                        event="Techtonic Hunt";
                        break;
                    case "TKT":
                        price="100";
                        event="The Knights Tour";
                        break;
                    case "VWC":
                        price="250";
                        event="Virtual War: Counter Strike";
                        break;
                    case "VWCG":
                        price="500";
                        event="Virtual War: CSGO";
                        break;
                    case "VWN":
                        price="50";
                        event="Virtual War: Need For Speed";
                        break;
                    case "VWPS":
                        price="50";
                        event="Virtual War: PUBG Mobile";
                        break;
                    case "VWPD":
                        price="100";
                        event="Virtual War: PUBG Mobile";
                        break;
                    case "VWPSQ":
                        price="200";
                        event="Virtual War: PUBG Mobile";
                        break;
                    case "WDV":
                        if ("school".equals(level))
                            price="50";
                        else
                            price="100";
                        event="Web Da Vinci";
                        break;
                }
                out.println("<div> <div class='container' id='main'>");
                out.println("<p>Congratulations, You are successfully registered for the event "+event+".</p>");
                out.println("<h1>Registration Details</h1>");
                out.println("<table>");
                out.println("<tr>");
                        out.println("<td>");
                            out.println("Registration ID:");
                        out.println("</td>");
                        out.println("<td>");
                            out.println(regId);
                        out.println("</td>");
                    out.println("</tr>");
                    out.println("<tr>");
                        out.println("<td>");
                            out.println("Name: ");
                        out.println("</td>");
                        out.println("<td>");
                            out.println(fullname);
                        out.println("</td>");
                    out.println("</tr>");
                    out.println("<tr>");
                        out.println("<td>");
                            out.println("Email Address:");
                        out.println("</td>");
                        out.println("<td>");
                            out.println(email);
                        out.println("</td>");
                    out.println("</tr>");
                   out.println("<tr>");
                        out.println("<td>");
                            out.println("Mobile Number:");
                        out.println("</td>");
                        out.println("<td>");
                            out.println(mob);
                        out.println("</td>");
                    out.println("</tr>");
                    out.println("<tr>");
                        out.println("<td>");
                            out.println("Institue/School:");
                        out.println("</td>");
                        out.println("<td>");
                            out.println(ins);
                        out.println("</td>");
                    out.println("</tr>");
                    out.println("<tr>");
                        out.println("<td>");
                            out.println("Event:");
                        out.println("</td>");
                        out.println("<td>");
                            out.println(event);
                        out.println("</td>");
                    out.println("</tr>");
                    out.println("<tr>");
                        out.println("<td>");
                            out.println("Level: ");
                        out.println("</td>");
                        out.println("<td>");
                            out.println(level);
                        out.println("</td>");
                    out.println("</tr>");
                    out.println("<tr>");
                        out.println("<td>");
                            out.println("Price:");
                        out.println("</td>");
                        out.println("<td>");
                            out.println("Rs. "+price);
                        out.println("</td>");
                    out.println("</tr>");
                    switch (eventid) {
                    case "CG":
                    case "CH":
                    case "VWPD":
                        tmn1=request.getParameter("tmn1");
                        team=tmn1;
                        out.println("<tr>");
                        out.println("<td>");
                            out.println("Team members: ");
                        out.println("</td>");
                        out.println("<td>");
                            out.println(team);
                        out.println("</td>");
                    out.println("</tr>");
                        break;
                    case "THS":
                    case "EX":
                        tmn1=request.getParameter("tmn1");
                        tmn2=request.getParameter("tmn2");
                        team=tmn1+","+tmn2;
                        out.println("<tr>");
                        out.println("<td>");
                            out.println("Team members: ");
                        out.println("</td>");
                        out.println("<td>");
                            out.println(team);
                        out.println("</td>");
                    out.println("</tr>");
                        break;
                    case "HA":
                    case "VWPSQ":
                        tmn1=request.getParameter("tmn1");
                        tmn2=request.getParameter("tmn2");
                        tmn3=request.getParameter("tmn3");
                        team=tmn1+","+tmn2+","+tmn3;
                        out.println("<tr>");
                        out.println("<td>");
                            out.println("Team members: ");
                        out.println("</td>");
                        out.println("<td>");
                            out.println(team);
                        out.println("</td>");
                    out.println("</tr>");
                        break;
                    case "VWCG":
                    case "VWC":
                    case "THC":
                        tmn1=request.getParameter("tmn1");
                        tmn2=request.getParameter("tmn2");
                        tmn3=request.getParameter("tmn3");
                        tmn0=request.getParameter("tmn0");
                        team=tmn0+","+tmn1+","+tmn2+","+tmn3;
                        pst.executeUpdate();
                        out.println("<tr>");
                        out.println("<td>");
                            out.println("Team members: ");
                        out.println("</td>");
                        out.println("<td>");
                            out.println(team);
                        out.println("</td>");
                    out.println("</tr>");
                        break;
                    /*case "TKT":
                        String rank,cid;
                        tmn1=request.getParameter("chessRank");
                        tmn2=request.getParameter("chessID");
                        rank=tmn1;
                        cid=tmn2;
                        break*/
                    default:
                        break;
                    //Display event details
                } 
                
                out.println("</table>");
                out.println("<p>You have to pay Rs."+price+" after 10th November 2019 before the event at our registration desk (BCA Building,Faculty of science,Maharaja Sayaji Rao University).</p>");
                out.println("<p>Event's timings and other details will be sent to your email @"+email+".</p>");
                out.println("<p style='color:red;'>Your registration id is:"+regId+"<br/>Please keep a screenshot of this page or registration id with you, whenever you come for the event.</p>");
                out.println("</body>");
                out.println("</html>");
            }
          }
           else if(f==0)
            {
                System.out.print("Wrong Recaptcha");
                response.sendRedirect("registration_form.html");
            }
        }}
        catch(ClassNotFoundException|SQLException e)
        {
            System.out.println(e);
            //response.getWriter().println("Something went wrong, please try again after some time");
            //response.sendRedirect("errorpage.html");
        }
        catch (Exception e)
        {
            System.out.println(e);
            //response.getWriter().println("Something went wrong, please try again after some time");
            //response.sendRedirect("errorpage.html");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
