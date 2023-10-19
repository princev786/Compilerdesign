package com.compilerdesign.lex;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.*;

/**
 * Servlet implementation class FindTokens
 */
@WebServlet("/FindTokens")
public class FindTokens extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindTokens() {
        super();
        // TODO Auto-generated constructor stub
    }

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str = request.getParameter("str");
		
		//list to store values
        HashSet<String> variables = new HashSet<>();
        HashSet<String> constants = new HashSet<>();
        HashSet<Character> operators = new HashSet<>();
        HashSet<Character> special_symbols = new HashSet<>();
        
        int i=0;
        while(i<str.length()){
            if(Character.isAlphabetic(str.charAt(i))){
                int j=i+1;
               if(j<str.length()){
                 while(Character.isAlphabetic(str.charAt(j))){
                    j++;
                }
               }
                variables.add(str.substring(i,j));
                i=j;
            }

            else if(Character.isDigit(str.charAt(i))){
                int j=i+1;
                while(Character.isDigit(j)){
                    j++;
                }
                constants.add(str.substring(i, j));
                i=j;
            }

            else if(str.charAt(i) == '+' || str.charAt(i) == '-' || str.charAt(i) == '*' || str.charAt(i) == '/' || str.charAt(i) == '%' || str.charAt(i) == '='){
                operators.add(str.charAt(i));
                i++;
            }

            else{
                special_symbols.add(str.charAt(i));
                i++;
            }

        }
        
        //storing hahset to session
        HttpSession session = request.getSession();
		session.setAttribute("variables", variables);
		session.setAttribute("constants", constants);
		session.setAttribute("operators", operators);
		session.setAttribute("special_symbols", special_symbols);
		response.sendRedirect("index.jsp");
	}

}
