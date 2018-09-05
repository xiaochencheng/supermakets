<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<table>
			<tr>
				<td>
					id
				</td>
				<td>
					${user.id}
				</td>	
			</tr>
			<tr>
				<td>
					用户编码
				</td>
				<td>
					${user.userCode}
				</td>	
			</tr>		
			<tr>
				<td>
					用户名称
				</td>
				<td>
					${user.userName}
				</td>	
			</tr>		
			<tr>
				<td>
					用户密码
				</td>
				<td>
					${user.userPassword}
				</td>	
			</tr>			
			<tr>
				<td>
					${user.idPicPath}
				</td>
				<td>
					<img src="/SMBMS208/statics/uploadfiles/${user.idPicPath}">
					
				</td>	
			</tr>							
		</table>
	</div>
</body>
</html>