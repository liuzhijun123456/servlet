package com.upload;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig
@WebServlet(name="uploads",urlPatterns="/uploads", asyncSupported=true)
public class Upload extends HttpServlet{

	@Override
	protected void doPost(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
		//final AsyncContext asyncContext = req.getAsyncContext();
		final AsyncContext ac = req.startAsync(req, res);
		ac.start(new Runnable() {
			public void run() {
				try {
					//set charset utf-8
					req.setCharacterEncoding("UTF-8");
					//set response type
					res.setContentType("text/html;charset=utf-8");
					PrintWriter writer = res.getWriter();
					Part part = req.getPart("file");
					//get file name  UUID
					final String fileName = getFileName(part);
					if(isFileVaild(part)){
						String path = req.getServletContext().getRealPath("/WEB-INF/upload/");
						mkdir(path);
						part.write(path + fileName);
						writer.print("success");
					}else{
						writer.print("error");
					}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ServletException e) {
					e.printStackTrace();
				}
			}
		});
	}

	//file name
	private String getFileName(Part part){
		String header = part.getHeader("content-disposition");
		String fileName = header.substring(header.lastIndexOf("\\")+1,header.lastIndexOf("\""));
		String[] items = fileName.split(";");
		for (String string : items) {
			if(string.trim().startsWith("filename")){
				fileName = string.substring(string.indexOf("=")+2);
				fileName = generateUUID() + string.substring(string.indexOf("."));
			}
		}
		return fileName;
	}
	
	private String generateUUID() {
	    return UUID.randomUUID().toString().replace("-", "_");
	}
	
	private boolean isFileVaild(Part part){
		if(part.getContentType() == null){
			return false;
		}else if(part.getName() == null){
			return false;
		}
		return true;
	}
	
	private void mkdir(String path){
		File pathDir = new File(path);
		if(!pathDir.exists()){
			pathDir.mkdirs();
		}
	}
}
