var selectedItem = null;
function setSelectedItem(item){
    selectedItem = item;
}
function activateMoveRightButton(){
    document.getElementById('btnMoveRight').disabled = false;
}
function removeSelectedItemFromLeftTable(){
    selectedItem.parentNode.removeChild(selectedItem);
}
function addSelectedItemToRightTable(){
    var table = document.getElementById('rightDeleteStudentTBody');
    var newRow = table.insertRow();
    for(i = 0; i < 3; i++){
        var newCell = newRow.insertCell(i);
        newCell.innerHTML = selectedItem.children[i].innerHTML;
    }
    newRow.id = "row" + newRow.children[0].innerHTML;
}
function deactivateMoveRightButton(){
    document.getElementById('btnMoveRight').disabled = true;
}
function activateMoveLeftButton(){
    document.getElementById('btnMoveLeft').disabled = false;
}
function removeSelectedItemFromRightTable(){
    selectedItem.parentNode.removeChild(selectedItem);
}
function addSelectedItemToLeftTable(){
    var table = document.getElementById('leftDeleteStudentTBody');
    var newRow = table.insertRow();
    for(i = 0; i < 3; i++){
        var newCell = newRow.insertCell(i);
        newCell.innerHTML = selectedItem.children[i].innerHTML;
    }
    newRow.id = "row" + newRow.children[0].innerHTML;
}
function deactivateMoveLeftButton(){
    document.getElementById('btnMoveLeft').disabled = true;
}
function toggleColor(){
    for(i = 0; i < document.getElementById("rightDeleteStudentTBody").children.length; i++){
        document.getElementById("rightDeleteStudentTBody").children[i].style.backgroundColor = "white";
    }
    for(i = 0; i < document.getElementById("leftDeleteStudentTBody").children.length; i++){
        document.getElementById("leftDeleteStudentTBody").children[i].style.backgroundColor = "white";
    }
    selectedItem.style.backgroundColor = "#F2F2F2";
}
function toggleDeleteButton(){
    if(document.getElementById('rightDeleteStudentTBody').children.length == 0){
        document.getElementById("btnDelete").disabled = true;
    }else{
        document.getElementById('btnDelete').disabled = false;
   }
}