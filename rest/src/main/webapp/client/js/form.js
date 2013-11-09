$(function() {  
  $("#submit").click(function() {  
		var name = $("#nombre").val();

		if(name!="")
		{
			var dataString = {"name" : name};
			
			$.ajax({  
			  type: "POST",  
			  url: "/api/lobster/new", 
			  contentType: "application/json; charset=utf-8",			  
			  data: JSON.stringify(dataString),  
			  datatype: 'json',  
			  success: function()
			  {  
				requestUsers();
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
        requestUsers();
        return false;
    });
});

function requestUsers()
{
	$.ajax({
            type: "GET",
			dataType: "json",
            url: "/api/lobster/list",
            success: function(list)
            {
				carro = "";
				$.each(list, function(index, element) {
					carro += element.id + " - " + element.name+"<br>";
				});
				
				$("#mensaje").html(carro);
				
                //[{"name":"eppepee","id":1,"password":null,"email":null,"status":null}]
            }
        });
}