$(document).ready(function(){
    $("#newStudent").click(function(){
        jQuery.ajax({
            url: "http://localhost:8080/students",
            type: "POST",
            data: document.getElementById('name').value,
            contentType: "text/plain",
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
        jQuery.ajax({
            url: "http://localhost:8080/courses",
            type: "POST",
            data: document.getElementById('name').value,
            contentType: "text/plain",
            success: function(){
                document.location.href = "http://localhost:8080/courses";
                alert("Kurs erfolgreich hochgeladen.");
            }
        })
     });
});