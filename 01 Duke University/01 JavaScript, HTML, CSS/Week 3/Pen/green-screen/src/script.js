var image;

function upload(){
  var canvas = document.getElementById("cv");
  var file = document.getElementById("finput");
  
  image = new SimpleImage(file);
  image.drawTo(canvas);
}

function grayscale(){  
  for (var pixel of image.values()){
    var average = (pixel.getRed()+pixel.getGreen()+pixel.getBlue())/3;
   
    pixel.setRed(average);
    pixel.setGreen(average);
    pixel.setBlue(average);
  }
  
  var canvas = document.getElementById("cv2");
  image.drawTo(canvas);
}