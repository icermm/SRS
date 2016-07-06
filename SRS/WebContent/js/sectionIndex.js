var url = location.href;
var es=/courseNo=/;
es.exec(url);
var courseNo=RegExp.rightContext;
$(document).ready(function(){
	$.getJSON("../getSections", function(jsonData) {
		var html="";
			for(var i=0;i<jsonData.length;i++){
				html+="<tr><td>"+(i+1)+"</td>"+
					  "<td><a href=\"enrolledStudents.html?sectionNo="+jsonData[i].sectionNo+"\">"+jsonData[i].courseName+"</td>" +
					  "<td>"+jsonData[i].professor+"</td><td>"+jsonData[i].dayOfWeek+"&emsp;"+jsonData[i].timeOfDay+"</td>" +
				      "<td>"+jsonData[i].room+"</td><td>"+jsonData[i].seatingCapacity+"</td>";
			}
		$("#showcourse").append(html);
	});
	
	$.getJSON("../getCourseCatalog", function(jsonData) {
		var html="";
			for(var i=0;i<jsonData.length;i++){
				html+="<option>"+jsonData[i].courseName+"</option>";
			}
		$("#courseName").append(html);
		$("#cName").append(html);
	});
	
	$.getJSON("../getProfessors", function(jsonData) {
		var html="";
			for(var i=0;i<jsonData.length;i++){
				html+="<option value='"+jsonData[i].name+"'>"+jsonData[i].name+"</option>";
			}
		$("#professor").append(html);
	});
});


function showSection(sectionNo){
	$.getJSON("../singleSection",{sectionNo:sectionNo}, function(jsonData) {
				$("#sNo").empty();
				$("#sNo").append(jsonData.sectionNo);
				$("#secNo").val(jsonData.sectionNo);
				$("#dOfW").val(jsonData.dayOfWeek);
				$("#tOfD").val(jsonData.timeOfDay);
				$("#cName").val(jsonData.courseName);
				$("#r").val(jsonData.room);
				$.getJSON("../getProfessors", function(json) {
					var html="";
						for(var i=0;i<json.length;i++){
							if(jsonData.professor==json[i].name){
								html+="<option selected='selected' value='"+json[i].name+"'>"+json[i].name+"</option>";
							}else{
								html+="<option value='"+json[i].name+"'>"+json[i].name+"</option>";
							}
						}
					$("#pname").append(html);
				});
				$("#sCapacity").val(jsonData.seatingCapacity);	
	});
}



