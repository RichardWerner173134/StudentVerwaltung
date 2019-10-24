$(document).ready(function(){
    $(btnGetStudentList).click(function (){
        var req = new XMLHttpRequest();
        req.onreadystatechange = function(){
            if(req.readyState==4 && req.status==200){
                document.getElementById("studentlist").innerHTML= createStudentList(req.responseText);
            }
        }
        req.open("GET", "http://localhost:8080/students");
        req.send();
    });
});
function createStudentList2(responseText){
    var json = JSON.parse(responseText);
    var returnText = "<ul>";
    for(i = 0; i < json.length; i++){
        returnText += "<li>" + json[i].name + "</li>"
    }
    returnText += "</ul>";
    return returnText;
}
function getStudents(responseText){
    var json = JSON.parse(responseText);
    var students = null;
    for(i = 0; i < json.length;i++){
        students[i] = [json[i].id,json[i].name];
    }
    return students;
}