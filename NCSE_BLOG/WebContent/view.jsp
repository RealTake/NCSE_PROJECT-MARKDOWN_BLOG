<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <!--[if IE]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <![endif]-->
    <title></title>
    <!-- BOOTSTRAP CORE STYLE  -->
    <link href="assets/css/bootstrap.css" rel="stylesheet" />
    <!-- FONT AWESOME ICONS  -->
    <link href="assets/css/font-awesome.css" rel="stylesheet" />
    <!-- CUSTOM STYLE  -->
    <link href="assets/css/style.css" rel="stylesheet" />
     <!-- HTML5 Shiv and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
    <header>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <strong>Email: </strong>info@yourdomain.com
                    &nbsp;&nbsp;
                    <strong>Support: </strong>+90-897-678-44
                </div>

            </div>
        </div>
    </header>
    <!-- HEADER END-->
    <div class="navbar navbar-inverse set-radius-zero">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/">

                    <img src="assets/img/logo.png" />
                </a>

            </div>

            <div class="left-div">
                <div class="user-settings-wrapper">
                    <ul class="nav">

                        <li class="dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false">
                                <span class="glyphicon glyphicon-user" style="font-size: 25px;"></span>
                            </a>
                            <div class="dropdown-menu dropdown-settings">
                                <div class="media">
                                    <a class="media-left" href="#">
                                        <img src="assets/img/64-64.jpg" alt="" class="img-rounded" />
                                    </a>
                                    <div class="media-body">
                                        <h4 class="media-heading">Jhon Deo Alex </h4>
                                        <h5>Developer & Designer</h5>

                                    </div>
                                </div>
                                <hr />
                                <h5><strong>Personal Bio : </strong></h5>
                                Anim pariatur cliche reprehen derit.
                                <hr />
                                <a href="#" class="btn btn-info btn-sm">Full Profile</a>&nbsp; <a href="login.html" class="btn btn-danger btn-sm">Logout</a>

                            </div>
                        </li>


                    </ul>
                </div>
            </div>
        </div>
    </div>
    <!-- LOGO HEADER END-->
    <section class="menu-section">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="navbar-collapse collapse ">
                        <ul id="menu-top" class="nav navbar-nav navbar-right">
                            <li><a href="board.do?type=PJ_board">PJ_board</a></li>
                            <li><a href="board.do?type=FR_board">FR_board</a></li>
                            <li><a href="board.do?type=ITnews_board">ITnews_board</a></li>
                            <li><a href="board.do?type=ST_board">ST_board</a></li>
                            <li><a href="board.do?type=_board">미정_board</a></li>
                            <li><a href="board.do?type=_board">미정_board</a></li>

                        </ul>
                    </div>
                </div>

            </div>
        </div>
    </section>
    <!-- MENU SECTION END-->
    <div class="content-wrapper">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h4 class="page-head-line">${view.title}</h4>

                </div>

            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="alert alert-warning">
                    
						<table width="1000" class="table table-striped table-bordered table-hover"border="1">
								<tr>
									<td>아이디</td>
									<td>${view.id}</td>
									<td>날짜</td>
									<td>${view.date}</td>
									<td>좋아요</td>
									<td>${view.like}</td>
									<td>싫어요</td>
									<td>${view.disLike}</td>
								</tr>
						</table>

						</br>
						
						<table class="table table-striped table-bordered table-hover"	 width="1000" border="1">
							<tr>
								<td>${view.content}</td>
							</tr>
						</table>
                        
                        <br />
                        <a href="board.do?type=${view.type}" class="glyphicon glyphicon-align-left text-success" target="_self">목록</a>&nbsp&nbsp&nbsp&nbsp&nbsp
                        <a href="modifyCheck?bid=${view.bId}&type=${view.type}" class="glyphicon glyphicon-edit  text-danger" target="_self">수정</a>&nbsp&nbsp&nbsp&nbsp&nbsp
                        <a href="delete.do?bid=${view.bId}&type=${view.type}" class="glyphicon glyphicon-info-sign text-danger" target="_self">삭제</a>
                        
                  
                    </div>
                    
                    <div class="alert alert-warning">
                    	<c:forEach items="${view.comments}" var="dto">
						<table class="table table-striped table-bordered table-hover" width="1000" border="1">
							<tr>
								<td width="200px">아이디: ${dto.name}</td>
								
							</tr>
							<tr>
								<td width="50px">댓글</td>
								<td>${dto.comment }</td>
							</tr>
			            </table>
			            	</c:forEach>
                  	</div>
                  	
                    <br/>
                    <% if(session.getAttribute("user_id") != null)
                    	{
                    	%>
	                    	<form method="post" action="comment.do" class="alert alert-warning">
	                    		<input type="hidden" name="bid" value="${view.bId }"/>
	                    		<input type="hidden" name="type" value="${view.type }"/>
			                    <table class="table table-striped table-bordered table-hover" width="1000" border="1">
									<tr>
										<td><span class="glyphicon glyphicon-comment  text-warning"></span></td>
										<td><input name="comment" rows="8" cols="50" style="margin: 20px; height: 100px; width: 900px;"><input type="submit" value="댓글" style="margin: 10px; height: 90px; width: 85px;"></td>
									</tr>
			                    </table>
		                    </form>
	                    <%} 
	                %>
                    
                </div>

            </div>
        </div>
    </div>
    <!-- CONTENT-WRAPPER SECTION END-->
    <footer>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    &copy; 2015 YourCompany | By : <a href="http://www.designbootstrap.com/" target="_blank">DesignBootstrap</a>
                </div>

            </div>
        </div>
    </footer>
    <!-- FOOTER SECTION END-->
    <!-- JAVASCRIPT AT THE BOTTOM TO REDUCE THE LOADING TIME  -->
    <!-- CORE JQUERY SCRIPTS -->
    <script src="assets/js/jquery-1.11.1.js"></script>
    <!-- BOOTSTRAP SCRIPTS  -->
    <script src="assets/js/bootstrap.js"></script>
</body>
</html>
