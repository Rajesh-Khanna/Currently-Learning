var Clock = function(){
    this.startTime = 0;
    this.clockTime = 0;
    this.minut = 1;
    this.siz = 1;
    this.xx = 700;
    this.yy = 500;
    this.clockRun = true;
      this.start = function(){
        dat = new Date();
        this.startTime = dat.getTime();
      }

      this.time_count = function(scale){
        dat = new Date();
        switch (scale) {
          case 0:
              return dat.getTime()-this.startTime;
          case 1:
              return Math.floor(dat.getTime()-this.startTime)/1000;
            }
      }
      this.getml = function(){
        dat = new Date();
        return dat.getTime();
      }

    this.start_analog_clock = function(minut,siz,xx,yy){
      this.clockRun = true;
      this.minut = minut;
      this.siz = siz;
      dat = new Date();
      this.clockTime = dat.getTime();
      this.xx = xx;
      console.log(this.xx);
      this.yy = yy;
      }

      this.analog_clock = function(){
      //console.log(this.clockTime);
      if(this.clockRun){
        dat = new Date();
        fill('#a70be0');
        strokeWeight(0);
        ellipse(this.xx,this.yy,115*this.siz,115*this.siz);
        fill(255);
        ellipse(this.xx,this.yy,105*this.siz,105*this.siz);
        fill('#ff00ff');
        strokeWeight(0);
        angleMode(DEGREES);
        var tm = (dat.getTime() - this.clockTime)/1000;
        //console.log(dat.getTime());
        arc(this.xx, this.yy, 100*this.siz, 100*this.siz, -90 ,(tm*6/this.minut-90));
        if(tm*6/this.minut >= 360){
            this.stop_clock();
            return false;
        }
        fill(255);
        ellipse(this.xx,this.yy,85*this.siz,85*this.siz);
        fill(0)
        strokeWeight(4);
        textAlign(CENTER,CENTER);
        textSize(50*this.siz);
        text(Math.round(tm).toString(),this.xx,this.yy);
      }
      return true;
    }
      this.stop_clock = function(){
      this.clockRun = false;
      this.siz = 1;
      this.minut = 1;
      this.clockTime = 0;
    }
};
