$(document).ready(function(){
    $("#newStudent").click(function(){
        var data = {
            vorname: document.getElementById('vorname').value,
            nachname: document.getElementById('nachname').value
        }
        jQuery.ajax({
            url: "http://localhost:8080/students",
            type: "POST",
            data: JSON.stringify(data),
            contentType: "application/json",
            success: function (){
                document.location.href = "http://localhost:8080/students";
                alert("Student erfolgreich hochgeladen.");
            }
        });
     });
     $("#btnNewSubject").click(function() {
        jQuery.ajax({
            url: "http://localhost:8080/subjects",
            type: "POST",
            data: document.getElementById('name').value,
            contentType: "text/plain",
            success: function (){
                document.location.href = "http://localhost:8080/subjects";
                alert("Fach erfolgreich hochgeladen.");
            }
        })
     });
     $("#btnNewCourse").click(function() {
        var subject = document.getElementById('dropdown').value;
        if(subject == ""){
            return;
        }
        jQuery.ajax({
            url: "http://localhost:8080/courses",
            type: "POST",
            data: subject,
            contentType: "text/plain",
            success: function(){
                document.location.href = "http://localhost:8080/courses";
                alert("Kurs erfolgreich hochgeladen.");
            }
        })
     });
     $(".studentTableEntries").mouseover(function(event){
        var target = jQuery(this).attr("id");
        document.getElementById(target).style.backgroundColor="yellow";
     });
     $(".studentTableEntries").mouseout(function(event){
        var target = jQuery(this).attr("id");
        document.getElementById(target).style.backgroundColor="white";
     });
     $(".studentTableEntries").click(function(event){
        var target = jQuery(this).attr("id");
        document.location.href = "http://localhost:8080/students/" + document.getElementById(target).children[0].innerHTML + "/courses";
     });
});