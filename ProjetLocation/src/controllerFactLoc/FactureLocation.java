package controllerFactLoc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FactureLocation
 */
@WebServlet("/FactureLocation")
public class FactureLocation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FactureLocation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub


//		System.out.println("telechargement");
		
		try{
			
			int total = 0;
			
	        //chargement du pilote
	        Class.forName("com.mysql.jdbc.Driver");
	        //Creer la connection
	        Connection conn = DriverManager.getConnection
	                ("jdbc:mysql://localhost:3306/locationski", "root", "");
	        
	        PreparedStatement ps = conn.prepareStatement("SELECT * FROM `locations` WHERE client = ? ");
	        ps.setString(1, (String) request.getSession().getAttribute("id"));
            ResultSet rs = ps.executeQuery();
            String idLocations ="";
            
            while(rs.next()){
            	idLocations = rs.getString("idLocations");
            	
            }
     
//            System.out.println("locations");
            
            
            PreparedStatement ps1 = conn.prepareStatement("SELECT * FROM `lignesloc` WHERE location = ? ");
	        ps1.setString(1, idLocations);
            ResultSet rs1 = ps1.executeQuery();
            
            
//            System.out.println("lignesloc");
                 
            OutputStream out = response.getOutputStream();
        	String headerKey = "Content-Disposition";
	        String headerValue = String.format("attachment; filename=\"Report"+".txt\";");
	        response.setHeader(headerKey, headerValue);
	        
	        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	        Date dateNow = new Date(System.currentTimeMillis());
            
	        String text = "Facture Location \t\t "+formatter.format(dateNow)+" \n\n\n\n\n\n";
	  
            while(rs1.next()){
            
            	String article = "";   
            	String prixJournalier = "";

                PreparedStatement ps2 = conn.prepareStatement("SELECT * FROM articles WHERE idArticle = ? ");
		        ps2.setString(1, rs1.getString("article"));
		        ResultSet rs2 = ps2.executeQuery();
		        
		       
		        while(rs2.next()){
		        	article = rs2.getString("libelle");
		        	prixJournalier = rs2.getString("prixJournalier");
		        	
		        }
		        
//		        System.out.println("article");
		        
		        final DateTimeFormatter NEW_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		        final DateTimeFormatter OLD_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		        
		        String oldString = rs1.getString("debut");
		        LocalDate debut = LocalDate.parse(oldString, OLD_FORMATTER);
		        String dateDebut = debut.format(NEW_FORMATTER);
		        
		        LocalDate date1 = LocalDate.parse(rs1.getString("debut"));
		        
		        String oldString2 = rs1.getString("fin");
		        LocalDate fin = LocalDate.parse(oldString, OLD_FORMATTER);
		        String dateFin = fin.format(NEW_FORMATTER);
		        
		        LocalDate date2 = LocalDate.parse(rs1.getString("fin"));
		        
		        Period period = Period.between(debut, fin);
		        int diffJours = period.getDays();
		        
		        if(diffJours == 0) {
		        	diffJours = 1;
		        }

		        
//		        System.out.println(diffJours+" jours");
		        
		        int totalLigne = diffJours*Integer.parseInt(prixJournalier);
		        
		        total += totalLigne;

            	text += "Article : "+article+" | "+" Du "+dateDebut+" Au "+dateFin+" | Prix Journalier : "+prixJournalier+" euros x "+diffJours+" Jours | Total "+totalLigne+" euros \n";
            	
            	rs2.close();
     		    ps2.close();
            }	
            		
            text += "\n\n\n\n\n\n Montant Total "+total+" euros";
            	
		    StringBuffer sb = new StringBuffer(text);
			InputStream in = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
		        
		        // obtains response's output stream
		    OutputStream outStream = response.getOutputStream();
		    byte[]outputByte = new byte[4096];//copy binary contect to output stream
		    while(in.read(outputByte, 0, 4096) != -1)
			{
				   out.write(outputByte, 0, 4096);
			}

			in.close();
			out.flush();
		    outStream.close(); 
		    
		    rs.close();
		    ps.close();
		    
		    
		    rs1.close();
		    ps1.close();
		    
		    conn.close();
			        				        
			}catch(Exception e){
			    System.out.println(e);
			}

//		System.out.println("FINtelechargement");
		request.getRequestDispatcher("locations-client.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
