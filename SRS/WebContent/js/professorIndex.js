$(document).ready(function(){
	$.getJSON("../getProfessors", function(jsonData) {
		var html="";
			for(var i=0;i<jsonData.length;i++){
				html+="<tr><td>"+(i+1)+"</td><td>"+jsonData[i].name+"</td><td>"+jsonData[i].PSsn+"</td><td>"+jsonData[i].title+"</td><td>"+jsonData[i].department+"</td>" +
					  "<td>	<a  onclick=\"deleteProfessor('"+jsonData[i].PSsn+"');\">删除</a>";
			}
		$("#showprofessor").append(html);
	});
});

function addProfessor(){
	$("#addprofessor").submit(); 
}

function deleteProfessor(PSsn){
	if(confirm("确认删除")==true){	$("#Faculty").attr("action","../deleteProfessor?PSsn="+PSsn);
		$("#Faculty").submit(); 
	}
}

