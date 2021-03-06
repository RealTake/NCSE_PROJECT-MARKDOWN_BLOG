<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	String check = request.getParameter("check");
	if(check == null)
	{
		%>
			
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Join</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" media="screen" href="main.css" />
    <script src="main.js"></script>

    <style>

        html, body{height: 100%; overflow: hidden;}
        body{
            margin-top: 0;
            margin-bottom: 0;
            margin-left: 0;
            margin-right: 0;
        }

        .logo{
            height: 12%;
            width: 100%;
            margin: 15px 0px;
            text-align: center;

        }

        .input{
            height: 120%;
            width: 100%;
            background-color: #f5f6f7;
            text-align: center;
        }

        .info{
            height: 100%;
            width: 21%;
            background-color: #f5f6f7;
            display: inline-block;
            text-align: left;
            font-size: 11px;
            font-weight: 530;
            
        }

        input{
            width: 100%;
            height: 30px;
            margin-bottom: 30px;
            margin-top: 3px;
        }

        select{
            width: 101%;
            height: 35px;
            margin-bottom: 30px;
            margin-top: 3px;
        }

        #logo_img{
            height: 105px; 
            width: 320px;
        }

        #input_btn{
            width: 101%;
            height: 40px;
            background-color: #d0f3f9;
            border: 0;
            font-family: 맑은고딕;
        }
    </style>
</head>




<body style="overflow:auto">
    <div class="logo">
        <img id="logo_img" src="C:/Users/DELL/Documents/design/plugliquid_logo_black.png" alt="logo">
    </div>

    <div class="input">
        <div class="info">
            <form action="join.do" accept-charset="utf-8" method="POST">
                <br><br>아이디<br><input type="text" name="id">
                비밀번호<br><input type="password" name="pw">
                비밀번호 확인<br><input type="password" name="pwCheck">
                이름<br><input type="text" name="name">
                이메일<br><input type="email" name="email">
                성별<br><select name="sex">
                    <option value="man">남자</option>
                    <option value="woman">여자</option>
                </select>
                <br>닉네임<br><input type="text" name="nick">
                전화번호<br><input type="text" name="ph">
                한줄소개<br><input type="text" name="self_imp">
                개인 SNS<br><input type="text" name="platform_link">
                <input id="input_btn" type="submit" value="가입하기">
            </form>
        </div>
   
    </div> 
</body>
</html>
		<%
	}
	else if(check.equals("false"))
	{
		out.print("<script> alert('이미 존재 하는 아이디입니다'); </script>");
		out.print("<script> location.href='join' </script>");
	}
%>
