function dochange(message){
  alert(message);
}

function pick(){
  var choice = confirm('Are you sure?');
  var message
  
  if (choice == true){
    message="You pressed OK!";
  }else{
    message = "Are you sure you want to cancel?";
  };
  
  dochange(message);
}

function color(){
  var id1 = document.getElementById("id1")
  var id2 = document.getElementById("id2")
    
  if (id1.className != "bluebg" && id2.className != "yellowbg"){
    id1.className = "bluebg";
    id2.className = "yellowbg";
  } else {
    id1.className = "whitebg";
    id2.className = "whitebg";
  }
}

function text(){
  var id1 = document.getElementById("id1")
  var id2 = document.getElementById("id2")
    
  if (id1.innerHTML != "Hello" && id2.innerHTML != "Goodbye"){
    id1.innerHTML = "Hello";
    id2.innerHTML = "Goodbye";
  } else {
    id1.innerHTML = "Goodbye";
    id2.innerHTML = "Hello";
  }
}

function switchb(){
  var id3 = document.getElementById("id3") 
  var id4 = document.getElementById("id4") 
  var temp;
  
  temp = id3.getAttribute("value")
  id3.setAttribute("value",id4.getAttribute("value"));
  id4.setAttribute("value",temp);
  
  temp = id3.getAttribute("onclick")
  id3.setAttribute("onclick",id4.getAttribute("onclick"));
  id4.setAttribute("onclick",temp);
}