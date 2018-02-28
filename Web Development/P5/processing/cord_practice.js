class cord_practice{
	constructor(checkbox,controls,cord_data,cord_selector){
		if(document.getElementById('list_songs'))
			document.getElementById('controls').removeChild(document.getElementById('list_songs'));
		for (i=0;i<cord_data.length;i++){
			//this.checkbox.push(createCheckbox((i+1).toString()+' .'+this.cord_data[i][0], false));
			//checkbox[i].parent(cord_selector);
			//this.checkbox[i].class('min1');
			//checkbox[i].changed(min_change);
		}
		console.log(Object.keys(cord_selector));
		cord_selector.parent(controls);

		document.getElementById('mode').innerHTML = 'practice songs';

}	

	min_change(){
		var m = this.elt.children[1].innerHTML;
		var i=parseInt(m.split(" ")[0]);
		m=m.split(".")[1];
		if(this.checked()){
			practice_cords.push(cord_data[i-1]);
			cd = cord_data[i-1];
		}
		else{
			index = practice_cords.findIndex(x => x[0]==m);
			practice_cords.splice(index,1);
			}
	}	


}