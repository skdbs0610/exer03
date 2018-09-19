<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>MVC</title>
<link rel="stylesheet" href="<%=application.getContextPath()%>/css/style.css" />
</head>
<body>
	<%--
		mvc pattern 의 application 에서는 
		action 이나 link , ajax 목적지를 controller 매핑경로로 설정
	 --%>
	<div align="center">
		<h1>〔회원가입〕</h1>
		<div style="margin-left: 10%; margin-right: 10%; text-align: left;">
		
		<h3 style="color: blue;">Step1.당신의 계정을 생성하세요.</h3>
		<h3 style="color: silver;">	Step2.당신의 관심사항을 설정하세요.</h3>
		<form action="join.do" method="post" autocomplete="off" >
			<p>
				<b>아이디(*)</b><br/>
				<input type="text" id="inputid" name="id" placeholder="account id" onkeyup="checkId(this.value);" value="<%=request.getAttribute("id")%>" style="width: 99%;"/><span id="idspan"></span>
			</p>
			<p>
				<b>비밀번호(*)</b><br/>	
				<input type="password" id="inputpass" name="pass" placeholder="account pass" onkeyup="checkPass(this.value);"  value="<%=request.getAttribute("pass") %>" style="width: 99%;"/> <span id="pwspan"></span>
			</p>
			<p>
				<b>비밀번호 재확인(*)</b><br/>	
				<input type="password" name="pass2" placeholder="confirm pass" onkeyup="doubleCheck(this.value);"  style="width: 99%;"/><span id="pw2span"></span>
			</p>
			<p>
				<b>이름(*)</b><br/>	
				<input type="text" name="name" id="id" placeholder="user name" value="<%=request.getAttribute("name") %>" style="width: 99%;"/> 
			</p>
			<p>
				<b>성별(*)</b><br/>	
				<select name="gender" style="width: 100%;">
					<option value="M">남성</option>
					<option value="F">여성</option>
				</select>
			</p>
			<p>
				<button type="submit" style="widt h: 100%;">가입 신청</button>
			</p>
		</form>
		<script>
			var checkId = function(id){
				var id = document.getElementById("inputid").value;
				console.log("id="+id);
				var r = /^[A-Za-z]{1}[0-9A-Za-z]{3,11}$/;
				console.log(r.test(id));
				if(r.test(id)){
					var req = new XMLHttpRequest();
					req.open("get","signup_check.do?id="+id); 
					req.onreadystatechange = function() {
						if(this.readyState==4) {
							var m =JSON.parse(this.responseText.trim());
							console.log("m="+m);
							if(m==true){
								document.getElementById("idspan").innerHTML = "이미 사용중인 아이디입니다.";
								document.getElementById("idspan").style.color ="red";
							}else {
								document.getElementById("idspan").innerHTML = "아주 멋진 아이디에요.";
								document.getElementById("idspan").style.color ="green";
							}
						}	
					}
					req.send();
				}else{
					document.getElementById("idspan").innerHTML = "아이디는 영문숫자혼용 4~12자로 설정바랍니다.";
					document.getElementById("idspan").style.color ="red";
				}
				//valid();
		};
		
		
		var checkPass = function(pass){
			var pass = document.getElementById("inputpass").value;
			console.log("pass="+id);
			var r = /^[0-9A-Za-z-가-횧!@#$%^&*]{4,12}$/;
			console.log(r.test(pass));
			if(r.test(pass)){
				var req = new XMLHttpRequest();
				req.open("get","signup_check.do?pass="+pass); 
				req.onreadystatechange = function() {
					if(this.readyState==4) {
						var resp = this.responseText.trim();
						if(resp == "true"){
							document.getElementById("pwspan").innerHTML = "유효하지 않은 비밀번호입니다.";
							document.getElementById("pwspan").style.color ="red";
						}else {
							document.getElementById("pwspan").innerHTML = "안전한 비밀번호입니다.";
							document.getElementById("pwspan").style.color ="green";
						}
					}
				}
				req.send();
			}else{
				document.getElementById("pwspan").innerHTML = "비밀번호는 4~12자로 입력해주세요.";
				document.getElementById("pwspan").style.color ="red";
			}
			//valid();
	};
	
	var doubleCheck= function(pw){
		var pass2 = pw;
		var passw= document.getElementById("inputpass").value;
		console.log("비번2="+pass2);
		//console.log("password : "+password);
		console.log(passw==pass2);
		if(passw==pass2){
			document.getElementById("pw2span").innerHTML="비밀번호 중복확인 ok";
			document.getElementById("pw2span").style.color="green";
			
		}else{
			document.getElementById("pw2span").innerHTML="비밀번호가 일치하지 않습니다.";
			document.getElementById("pw2span").style.color="red";
		}
		//valid();
	}
		
		</script>
		
		</div>
	</div>
</body>
</html>