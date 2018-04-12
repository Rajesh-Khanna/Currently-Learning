var Diagrams = function(){
  /*
  Variales
  */
  this.s_note = [0,0,0];
  /*
  Methods
  */

  this.chord_diagram = function(frats=24,start_frat=0,strings='XXXXXX',notes=[],fraction,offset){
  	if(frats<4)
  		frats=4;
    fraction =0.75;
  	h = 80*fraction;
  	w = 50*fraction;
  	var x= offset*6*50 + 50+50*(1-fraction);
  	var y= 80+h*2*(1-fraction);
  	// neck bar
  	strokeWeight(15*fraction);
  	textSize(15*fraction);
  	line(x,y,w*5+x,y);
  	strokeWeight(4*fraction);
  	text(start_frat.toString(),x-20*fraction,y);

  	// strings
  	Yy = y;
  	Xx = x;
  	for(var i=0;i<6;i++){
    line(Xx,Yy,Xx,Yy+h*(frats));
    if(strings[i]=='X' || strings[i] == 'O'){
  		text(strings[i],Xx,Yy-20*fraction);
    }else {
    	Yy = y+h*(parseInt(strings[i])-1)+h/2;
  		fill(255);
  		ellipse(Xx, Yy, w/2, w/2);
  		fill(0);
    }
  		Xx+=w;
      Yy = y;
  	}
  	// frats
  	Yy+=h;
  	pos = start_frat+1;
  	for(i=0;i<frats;i++){
  		text(pos.toString(),x-20*fraction,Yy);
  		pos+=1;
  		line(x,Yy,x+5*w,Yy);
  		Yy+=h;
  	}
  }
  this.neckDiagram = function(frats=24,fraction,offset,sh){
  	if(frats<4)
  		frats=4;
  	h = 80*fraction;
  	w = 50*fraction;
  	var x= 50+50*(1-fraction);
  	var y= 50+h*2*(1-fraction);
  	// neck bar
  	strokeWeight(15*fraction);
  	textSize(15*fraction);
  	line(x,y,w*5+x,y);
  	strokeWeight(4*fraction);
  	text('0',x-20*fraction,y);

  	strings = 'XXXXXX';
  	Yy = y;
  	Xx = x;
  	for(var i=0;i<6;i++){
    line(Xx,Yy,Xx,Yy+h*(frats));
  		text(strings[i],Xx,Yy-20*fraction);
  		Xx+=w;
  	}
  	// frats
  	Yy+=h;
  	pos = 1;
  	for(i=0;i<frats;i++){
  		text(pos.toString(),x-20*fraction,Yy);
  		pos+=1;
  		line(x,Yy,x+5*w,Yy);
  		Yy+=h;
  	}
  	// add 6-notes
  		Xx=50*Math.floor((mouseX+25)/50);
  		Yy=80*Math.floor((25-sh+mouseY)/80)+10;
  		if(Xx>=50 && Yy>=50 && Xx<=300 && Yy<=2000){
        fill(255);
        ellipse(Xx, Yy, w/2, w/2);
  			this.s_note[0] = 7-Xx/50;
  			this.s_note[1] = (Yy-10)/80;
  			this.s_note[2] = 0;
        fill(0);
  			text(this.s_note[0],Xx-w/2,Yy);
  			text(this.s_note[1],Xx-w/2,Yy+20);
  	}

  var get_chord = function() {
    return s_note;
  }
}
};
