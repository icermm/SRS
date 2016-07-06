$(document).ready(function(){
	$.getJSON("../getCourseCatalog", function(jsonData) {
		var html="";var option="<option value='0'>null</option>";
			for(var i=0;i<jsonData.length;i++){
				option+="<option>"+jsonData[i].courseName+"</option>";
				html+="<tr><td>"+(i+1)+"</td><td>"+jsonData[i].courseNo+"</td><td>"+jsonData[i].courseName+"</td><td>"+jsonData[i].credits+"</td>" +
					  "<td><a  onclick=\"deleteCourse('"+jsonData[i].courseNo+"');\">删除</a>" 
					 
			}
		$("#prerequisites").append(option);
		$("#showcourses").append(html);
	});
	
});


function showCourse(courseName){
	$.getJSON("../singleCourse",{courseName:courseName}, function(jsonData) {
				$("#cNo").empty();
				$("#cNo").append(jsonData.courseNo);
				$("#courNo").val(jsonData.courseNo);
				$("#cName").val(jsonData.courseName);
				$("#cCredits").val(jsonData.credits);
				$.getJSON("../getCourseCatalog", function(json) {
				
				var option="<option value='no'>无</option>";
				for(var i=0;i<json.length;i++){
					if(jsonData.pcourseName==json[i].courseName){
						option+="<option selected='selected'>"+json[i].courseName+"</option>";
						}
					else{
							option+="<option>"+json[i].courseName+"</option>";
						}		
					}
					$("#pre").append(option);
				});
	});
}


function addCourse(){
	$("#addcourse").submit(); 
}

function deleteCourse(courseNo){if(confirm("确认删除")==true){
		$("#CourseCatalog").attr("action","../deleteCourse?courseNo="+courseNo);
		$("#CourseCatalog").submit(); 
	}
}
