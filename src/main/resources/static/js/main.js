function myFunction() {

    var c1 = $("#name1").val();
    var c2= $("#pass1").val();
    var search = {
	   'name':c1,
	   'password':c2
			};

	   $.ajax({
	        type: 'POST',
	        dataType: 'json',
	        url: 'http://127.0.0.1:8080/adduser',
	       	data: search,
                     success: function (data) {
                     console.log("SUCCESS : ", data);
			       			},
			        error: function (e) {
						 console.log("ERROR : ", e);
			        }
	    });
}
