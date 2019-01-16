<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>

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
    <title>작성글</title>
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
                <a class="navbar-brand" href="/NCSE_BLOG">

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
                            <li><a  href="board.do">Dashboard</a></li>
                            <li><a href="ui.html">UI Elements</a></li>
                            <li><a href="table.html">Data Tables</a></li>
                            <li><a href="forms.html">Forms</a></li>
                             <li><a href="login.html">Login Page</a></li>
                            <li><a class="menu-top-active" href="blank.html">Blank Page</a></li>

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
                    
						
                        
                        <link rel="stylesheet" href="/NCSE_BLOG/daumeditor/css/editor.css" type="text/css" charset="utf-8"/> 
<script src="/NCSE_BLOG/daumeditor/js/editor_loader.js?environment=development" type="text/javascript" charset="utf-8"></script> 
<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery/1.9.0/jquery.js"></script>
 
<form name="tx_editor_form" id="tx_editor_form" action="write.do" method="post" accept-charset="utf-8"> 
	<table width="100%"> 
		<tr> 
			<td>제목</td> 
			<td><input type="text" id="title" name="title"/></td> 
		</tr> 
		<tr> 
			<td>내용</td> 
			<td id="editorTd">
				<%@ include file="/daumeditor/editor_template.html"%>
			</td> 
		</tr> 
		<tr> 
			<td colspan="2"> 
				<input type="button" id="save" value="저장"/> 
				<input type="button" value="취소"/> 
			</td> 
		</tr> 
	</table>
	
	<script>
function setConfig(){
			var config = {
					txHost: '', /* 런타임 시 리소스들을 로딩할 때 필요한 부분으로, 경로가 변경되면 이 부분 수정이 필요. ex) http://xxx.xxx.com */
					txPath: '', /* 런타임 시 리소스들을 로딩할 때 필요한 부분으로, 경로가 변경되면 이 부분 수정이 필요. ex) /xxx/xxx/ */
					txService: 'sample', /* 수정필요없음. */
					txProject: 'sample', /* 수정필요없음. 프로젝트가 여러개일 경우만 수정한다. */
					initializedId: "", /* 대부분의 경우에 빈문자열 */
					wrapper: "tx_trex_container", /* 에디터를 둘러싸고 있는 레이어 이름(에디터 컨테이너) */
					form: 'tx_editor_form'+"", /* 등록하기 위한 Form 이름 */
					txIconPath: "/NCSE_BLOG/daumeditor/images/icon/editor/", /*에디터에 사용되는 이미지 디렉터리, 필요에 따라 수정한다. */
					txDecoPath: "/NCSE_BLOG/daumeditor/images/deco/contents/", /*본문에 사용되는 이미지 디렉터리, 서비스에서 사용할 때는 완성된 컨텐츠로 배포되기 위해 절대경로로 수정한다. */
					canvas: {
						styles: {
							color: "#123456", /* 기본 글자색 */
							fontFamily: "굴림", /* 기본 글자체 */
							fontSize: "10pt", /* 기본 글자크기 */
							backgroundColor: "#fff", /*기본 배경색 */
							lineHeight: "1.5", /*기본 줄간격 */
							padding: "8px" /* 위지윅 영역의 여백 */
						},
						showGuideArea: false
					},
					events: {
						preventUnload: false
					},
					sidebar: {
						attachbox: {
							show: true,
							confirmForDeleteAll: true
						}
					},
					size: {
						contentWidth: 0 /* 지정된 본문영역의 넓이가 있을 경우에 설정 */
					}
				};
				EditorJSLoader.ready(function(Editor) {
					editor = new Editor(config);
				});
}

	$(function(){
		$.ajax({
	        type:"POST", 
	        url: "/NCSE_BLOG/daumeditor/editor_template.html",
	        success: function(data){
	        	$("#editorTd").html(data);
	        	setConfig();
	        }, 
	        error : function(request, status, error) {
				alert("에러");
			}
		});	
		$("#save").click(function(){ Editor.save(); })
	})
	
	// submit 전 다음에디터 validation체크 
	function validForm(editor) 
	{ 
		var validator = new Trex.Validator(); 
		var content = editor.getContent(); 
		if (!validator.exists(content)) 
		{ 
			alert('내용을 입력하세요'); return false; 
		} 
		return true; 
	} 
	
	//validForm 함수가 true로 return된 후에 동작하는 함수 
	function setForm(editor) 
	{ 
		var form = editor.getForm(); 
		var content = editor.getContent(); 
		var textarea = document.createElement('textarea'); 
		//textarea를 생성하여 해당태그에 에디터 입력값들을 신규생성 textarea에 담는다 
		textarea.name = 'content'; 
		textarea.value = content; 
		form.createField(textarea); 
		
		return true; 
	}
</script>
</form>
                  
                    </div>
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
