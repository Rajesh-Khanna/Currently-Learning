class Clock{
	constuctor(){
		this.dat = new Date();
	}
	/*
	Returns time in sec upto 2 significant fig
	*/
	sec(){
		this.dat = new Date();
		return this.dat.getSeconds();
	}
	/*
	Returs time till 00.000 
	*/
	sec_ml(){
		this.dat = new Date();
		return this.dat.getSeconds()+this.dat.getMilliseconds()/1000;
	}
	/*
	returns the time wrt T
	###########
	input
	Beats per second -> int
	##########
	*/
	freqBPM(BPMf){
		var timePeriod = 60/BPMf;
		return Math.floor(this.sec_ml()/timePeriod);
	}
	analogClock(e,time){
		dat = new Date();
		if(time != dat.getSeconds()){
			e+=1;
			time = dat.getSeconds();
		}
		if(e == 60){
			e=0;
			time=0;
			run = false;
		}
		this.dat = new Date();
		fill('#a70be0');
		strokeWeight(0);
		ellipse(700,100,115,115);
		fill(255);
		ellipse(700,100,105,105);
		fill('#ff00ff');
		strokeWeight(0);
		angleMode(DEGREES);
		arc(700, 100, 100, 100, -90 ,(e+this.dat.getMilliseconds()/1000)*6-90);
		fill(255);
		ellipse(700,100,90,90);
		fill(0)
		strokeWeight(4);
		textAlign(CENTER,CENTER);
		textSize(60);
		text(e.toString(),700,100);
		return [e,time];
	}
}