package com.upload;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * servlet3.0上传实例
 * 2017-01-08 pm
 * implements @MultipartConfig
 * @author liuzhijun
 *
 */
@MultipartConfig
@WebServlet(name="servletUploadServlet",urlPatterns="/upload")
public class ServletUploadServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		Part part = request.getPart("file");
		String header = part.getHeader("content-disposition");
		//String submittedFileName = part.getSubmittedFileName();
		String fileName = header.substring(header.lastIndexOf("\\")+1,header.lastIndexOf("\""));
		String[] items = fileName.split(";");
		for (String string : items) {
			if(string.trim().startsWith("filename")){
				fileName = string.substring(string.indexOf("=")+2);
				fileName = generateUUID() + string.substring(string.indexOf("."));
				System.out.println("fileName======>"+fileName);
			}
		}
		PrintWriter out = response.getWriter();
		out.println("head:content-disposition: " + header + "<br>");
		out.println("fileName: " +fileName + "<br>");
		//out.println("submittedFileName: " + submittedFileName + "<br>");
		out.println(part.getName() + "<br>");
        out.println("此文件的大小："+part.getSize()+"<br />");
        out.println("此文件类型："+part.getContentType()+"<br />");
        out.println("文本框内容："+request.getParameter("name")+"<br />");
	}
	
	private String generateUUID() {
	    return UUID.randomUUID().toString().replace("-", "_");
	}
	
	public static void main(String[] args) {
		//private String generateUUID() {
		//    return UUID.randomUUID().toString().replace("-", "_");
		//}
		System.out.println(UUID.randomUUID().toString().replace("-", "_"));
	}
}