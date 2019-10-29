<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script>
var dir = "C:/Users/Admin/Desktop/videos/";
var fileextension = ".mp4";
$.ajax({
    //This will retrieve the contents of the folder if the folder is configured as 'browsable'
    url: dir,
    success: function (data) {
        //List all .png file names in the page
        $(data).find("a:contains(" + fileextension + ")").each(function () {
            var filename = this.href.replace(window.location, "").replace("http://", "");
            $("body").append("<img src='" + dir + filename + "'>");
        });
    }
});
</script>
</head>
<body>
This is hello world app for CDN
<a href="/playvideo" >videos</a>
</body>
</html>