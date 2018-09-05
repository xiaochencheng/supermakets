/*点击上一页下一页连接触发的事件，提交的url是userlist form 里面的action*/
/*num是跳转至第几页*/
function page_nav(frm,num){
		frm.pageIndex.value = num;
		frm.submit();
}

/*跳转至多少页触发的事件*/
function jump_to(frm,num){
    //alert(num);
	//验证用户的输入
	var regexp=/^[1-9]\d*$/;
	var totalPageCount = document.getElementById("totalPageCount").value;
	//alert(totalPageCount);
	if(!regexp.test(num)){
		alert("请输入大于0的正整数！");
		return false;
	}else if((num-totalPageCount) > 0){
		alert("请输入小于总页数的页码");
		return false;
	}else{
		page_nav(frm,num);
	}
}