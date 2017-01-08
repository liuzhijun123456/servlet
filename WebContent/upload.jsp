<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Servlet-Upload</title>
</head>
<body>
	<form action="uploads" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<td>姓名：</td>
				<td><input type="text" name="username" value="" /></td>
			</tr>
			<tr>
				<td>文件</td>
				<td><input type="file" name="file"/></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="上传"/></td>
			</tr>
		</table>
	</form>
</body>
</html>