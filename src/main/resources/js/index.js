$(document).ready(function(){
    $(btnGetStudentList).click(function (){
        $.ajax({
        dataType: 'json',
        url: 'http://localhost:8080/home/students'
        });
    });
});