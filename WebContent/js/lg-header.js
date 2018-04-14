/*
由Mr.Yang编写 2017.12.28*/


/*banner nav tab*/
function tab(obj){
    var target = document.getElementById(obj);
    var lis = target.getElementsByTagName("li");
    for(var i = 1 ; i < lis.length ; i++){
        lis[i].onmouseover = function(){
            this.className="current";
        }
        lis[i].onmouseout =function () {
            for(var j = 0 ; j < lis.length ; j++){
                lis[j].className = "";
            }
        }
    }
}
function  companytab(obj){
    var target = document.getElementById(obj);
    var lis = target.getElementsByTagName("li");
    for(var i = 0 ;  i < lis.length ; i++){
        if(i !== 1){
            lis[i].onmouseover = function(){
                this.className="current";
            }
            lis[i].onmouseout =function () {
                for(var j = 0 ; j < lis.length ; j++){
                    lis[j].className = "";
                }
            }
        }

    }
};

window.onload=function(){
	submitForm();
}
//切换城市的时候相应的触发事件
function submitForm(){
	//获取form表单对象
	    var form = document.getElementById("myform");
	    form.submit();//form表单提交
	}

