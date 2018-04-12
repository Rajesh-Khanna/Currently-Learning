var sw = 800,sh = 500;

(function(){
  colorBoard();
  var draw2 = SVG('drawboard').size(sw,sh);
  var rect2 = draw2.polyline('0,0 '+sw+',0 '+sw+','+sh).fill('#304');
  shape_display();
  document.getElementsByClassName("slider")[0].addEventListener("mousedown",function(){
    document.getElementsByClassName("slider")[0].addEventListener("mousemove",changeColor);
  });
  document.getElementsByClassName("slider")[1].addEventListener("mousedown",function(){
    document.getElementsByClassName("slider")[1].addEventListener("mousemove",changeColor);
  });
  document.getElementsByClassName("slider")[2].addEventListener("mousedown",function(){
    document.getElementsByClassName("slider")[2].addEventListener("mousemove",changeColor);
  });
  document.getElementsByClassName("slider")[0].addEventListener("change",changeColor);
  document.getElementsByClassName("slider")[1].addEventListener("change",changeColor);
  document.getElementsByClassName("slider")[2].addEventListener("change",changeColor);

})()

function changeColor(){
  var Hue = document.getElementById('Hue');
  var Saturation = document.getElementById('Saturation');
  var Lightness = document.getElementById('Lightness');
  var picker = document.getElementsByClassName('pickerHSL')[0];
  picker.style.backgroundColor = "hsl("+Hue.value.toString()+","+Saturation.value.toString()+"%,"+Lightness.value.toString()+"%)";
}
function shape_display(){
  var db = [['0,20 60,20 60,50 0,50 0,20'],
  ['50,10 80,60 20,60 50,10'],
  ['0,30 30,0 60,30 60,60 0,60 0,30'],
  ['0,20 30,0 60,20 60,40 30,60 0,40 0,20'],
  ['0,20 30,0 60,20 60,40 30,60 0,40 0,20'],
  ];
  i=0;
  var figBoard = [];
  var shapeBoard = document.getElementById('shelves');
  figBoard.push(document.createElement('div'));
  figBoard[i].id = db.length.toString();
  shapeBoard.appendChild(figBoard[i]);
  figsvg = SVG(db.length.toString()).size(100,100);
  var fig = figsvg.ellipse(60 ,60);
  fig.fill('none').move(20, 20);
  fig.stroke({ color: '#f06', width: 4, linecap: 'round', linejoin: 'round' });

  for(i=0;i<db.length;i++){
    figBoard.push(document.createElement('div'));
    figBoard[i+1].id = i.toString();
    shapeBoard.appendChild(figBoard[i+1]);
    figsvg = SVG(i.toString()).size(100,100);
    console.log(db[0]);
    var fig = figsvg.polyline(db[i][0]);
    fig.fill('none').move(20, 20);
    fig.stroke({ color: '#f06', width: 4, linecap: 'round', linejoin: 'round' });
  }
}
function colorBoard(){
    a = ['#000000','#000080','#aaffc3','#800000','#e6beff','#fabebe','#d2f53c','#911eb4','#f58231','#ffe119','#3cb44b','#e6194b'];
    b = document.getElementsByClassName('clselector')[0];
    l = b.getElementsByTagName('li');
    for(i=0;i<a.length;i++){
      l[i].style.backgroundColor = a[i];
    }
}
