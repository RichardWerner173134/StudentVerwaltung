$(document).ready(function(){
    $("#newStudent").click(function(){
        var data = {
            vorname: document.getElementById('vorname').value,
            nachname: document.getElementById('nachname').value,
            street: document.getElementById('street').value,
            number: document.getElementById('number').value,
            city: document.getElementById('city').value,
            postalCode: document.getElementById('postalCode').value
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
     $(".tableEntries").mouseover(function(event){
        var target = jQuery(this).attr("id");
        document.getElementById(target).style.backgroundColor="yellow";
     });
     $(".tableEntries").mouseout(function(event){
        var target = jQuery(this).attr("id");
        document.getElementById(target).style.backgroundColor="white";
     });
     $($("#studentlistTBody").children()).click(function(event){
        var target = jQuery(this).attr("id");
        document.location.href = "http://localhost:8080/students/" + document.getElementById(target).children[0].innerHTML + "/courses";
     });
     $($("#courselistTBody").children()).click(function(event){
        var target = jQuery(this).attr("id");
        document.location.href = "http://localhost:8080/courses/" + document.getElementById(target).children[0].innerHTML + "/students";
     });
     $($("#subjectlistTBody").children()).click(function(event){
        var target = jQuery(this).attr("id");
        document.location.href = "http://localhost:8080/subjects/" + document.getElementById(target).children[0].innerHTML + "/courses";
     });
     $("#btnNewCourseForStudent").click(function(){
        var studentId = document.getElementById('h1').innerHTML.split("=")[1];
        var courseId = document.getElementById('selectedCourse').value.split("=")[1];
        var course = {
            courseId: courseId,
            studentId: studentId
        }
        $.ajax({
            url: "http://localhost:8080/students/" + studentId + "/courses/" + courseId,
            type: "PUT",
            data: JSON.stringify(course),
            contentType: "application/json",
            success: function(){
                document.location.href = "http://localhost:8080/students/"+ studentId + "/courses";
                alert("Kursanmeldung erfolgreich");
            }
        });
     });
    $("#leftDeleteStudentTBody").on("click", "tr", function(event){
        var target = document.getElementById(jQuery(this).attr("id"));
        setSelectedItem(target);
        toggleColor();
        activateMoveRightButton();
        deactivateMoveLeftButton();
    });
    $("#btnMoveRight").click(function(){
        removeSelectedItemFromLeftTable();
        addSelectedItemToRightTable();
        deactivateMoveRightButton();
        toggleDeleteButton();
    });
    $("#rightDeleteStudentTBody").on("click", "tr", function(event){
        var target = document.getElementById(jQuery(this).attr("id"));
        setSelectedItem(target);
        toggleColor();
        activateMoveLeftButton();
        deactivateMoveRightButton();
    });
    $("#btnMoveLeft").click(function(){
        removeSelectedItemFromRightTable();
        addSelectedItemToLeftTable();
        deactivateMoveLeftButton();
        toggleDeleteButton();
    });
    $("#btnDelete").click(function(){
        var url = "http://localhost:8080/students";
        for(i = 0; i < document.getElementById('rightDeleteStudentTBody').children.length; i++){
            var element = document.getElementById('rightDeleteStudentTBody').children[i];
            $.ajax({
                async: false,
                url: url + "/" + document.getElementById('rightDeleteStudentTBody').children[i].children[0].innerHTML,
                type: "DELETE",
                contentType: "text/plain",
                success: function(){
                    console.log("Löschen erfolgreich für StudentId" + element.children[0].innerHTML);
                    document.getElementById('rightDeleteStudentTBody').removeChild(element);
                    toggleDeleteButton();
                    i--;
                },
                error: function(){
                    console.log("Löschen fehlgeschlagen für StudentId" + element.children[0].innerHTML);
                    document.getElementById('rightDeleteStudentTBody').removeChild(element);
                    var newRow = document.getElementById('leftDeleteStudentTBody').insertRow();
                    for(j = 0; j < 3; j++){
                        var newCell = newRow.insertCell(j);
                        newCell.innerHTML = element.children[j].innerHTML;
                    }
                    newRow.id = "row" + newRow.children[0].innerHTML;
                    toggleDeleteButton();
                    i--;
                },
            });
        }
    });
});