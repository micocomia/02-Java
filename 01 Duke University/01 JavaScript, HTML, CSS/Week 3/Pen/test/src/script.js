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
  
  var temp = id1.style.backgroundColor;
  var temp2 = id2.style.backgroundColor;
  
  if (temp != "lightblue" && temp2 != "lightyellow"){
    id1.style.backgroundColor = "lightblue";
    id2.style.backgroundColor = "lightyellow";
  } else {
    id1.style.backgroundColor = "white";
    id2.style.backgroundColor = "white";
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

function fillcanvas(){
  var id5 = document.getElementById("id5") 
  var id6 = document.getElementById("id6") 
    
  var temp = id5.style.backgroundColor;
  var temp2 = id6.style.backgroundColor;
  
  if (temp != "cyan" && temp2 != "red"){
    id5.style.backgroundColor = "cyan";
    id6.style.backgroundColor = "red";
  } else {
    id5.style.backgroundColor = "white";
    id6.style.backgroundColor = "white";
  }
  
  var ctx = id5.getContext("2d");
  var ctx2 = id6.getContext("2d");
  
  ctx.clearRect(0,0,id5.width,id5.height);
  ctx2.clearRect(0,0,id6.width,id6.height);
}

function context(){
  var id5 = document.getElementById("id5") 
  var id6 = document.getElementById("id6") 
    
  var ctx = id5.getContext("2d");
  var ctx2 = id6.getContext("2d");
  
  ctx.fillStyle = "red"
  ctx2.fillStyle = "cyan"
  ctx.fillRect(10,10,50,50);
  ctx2.fillRect(100,10,50,50);
  
  ctx.fillStyle = "cyan";
  ctx2.fillStyle = "red";
  ctx.font = "20px Arial";
  ctx2.font = "20px Arial";
  ctx.fillText("Cyan",10,20);
  ctx2.fillText("Red",100,20);
}