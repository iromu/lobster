$(function() {  
  $("#submit").click(function() {  
		var name = $("#nombre").val();

		if(name!="")
		{
			var dataString = {"name" : name};
			
			$("#submit").hide();
			$("#view").hide();
			
			$.ajax({  
			  type: "POST",  
			  url: "/api/lobster/new", 
			  contentType: "application/json; charset=utf-8",			  
			  data: JSON.stringify(dataString),  
			  datatype: 'json',  
			  success: function(data)
			  {
				window.location.href = "lobster.htm?id="+data;
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
                    carro += "<a href='lobster.htm?id=" + element.id +"'>" + element.id + " - " + element.name+"</a><br>";
				});
				
				$("#mensaje").html(carro);
				
                //[{"name":"eppepee","id":1,"password":null,"email":null,"status":null}]
            }
        });
}