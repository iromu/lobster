$(function() {  
  $("#submit").click(function() {  
		var name = $("#nombre").val();

		if(name!="")
		{
			var dataString = 'name='+ name;  
			
			$.ajax({  
			  type: "POST",  
			  url: "data/dummy.htm",  
			  data: dataString,  
			  success: function()
			  {  
				alert("epep");
			  }  
			}); 
		}
		else
		{
			$("#mensaje").html("¡Pon nombre a tu langosta!");
		}
		
		return false;
  });

    $("#view").click(function() {
        $.ajax({
            type: "GET",
            url: "/api/lobsters",
            success: function()
            {
                alert("epep");
            }
        });

        return false;
    });
});