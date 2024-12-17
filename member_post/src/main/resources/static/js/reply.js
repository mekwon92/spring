// const replyService = {};
const replyService = (function() {
    const url = "/member_post/reply";
    function write(reply, callback) {
        console.log(reply);
        // reply를 JSON화
        // JSON.stringify(arg) :: obj -> json string
        // JSON.parse(arg) :: json -> obj
        const data = JSON.stringify(reply); //보내야할 데이터
        $.post({
            url,
            data
        })
        .done(function(data) {
          /*  console.log(data);*/
        	if(callback)
            callback(data);
        })
    }

    function list(pno, cri, callback){
		let reformedUrl = url + "/list/" + pno;
		if(cri && cri.lastRno) {
			reformedUrl += "/" + cri.lastRno;

			if(cri.amount){
				reformedUrl += "/" + cri.amount;
			}
			// console.log(reformedUrl);

		}
        $.getJSON(reformedUrl).done(function(data) {
            if(callback)
                callback(data);
        });
		// 위랑 같은 역할하는거임(더 간단)
        // $.ajax({
        //     url: url + "/list/" + pno,
        //     method : 'GET',
        //     dataType : 'JSON',
        //     success : function(data) {
        //         console.log(data);              
        //     }
        // })
    }
    function view(rno, callback){
        $.getJSON(url + "/" + rno).done(function(data){
            if(callback)
                callback(data);
        })
    }
    function modify(reply, callback) {
        const data = JSON.stringify(reply);
        $.ajax(url, {
            method : 'put',
            data
        }).done(function(data) {
            if(callback)
                callback(data);
        })
    }

    function remove(rno, callback) {
        $.ajax(url + "/" + rno, {
            method : 'delete',
        }).done(function(data) {
            if(callback)
                callback(data);
        })
    }
    return {write, list, view, modify, remove}
})();