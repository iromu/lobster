function handleLight()
{
	isDay = !isDay;
	
	if(isDay)
	{
		player.wakeUp();
		light.image.src = "img/light_on.png";
	}
	else
	{
		player.sleep();
		light.image.src = "img/light_off.png";
	}
	
	setBackground(isDay);
}

function handleBackground()
{
	if(player.state != "sleep")
	{
		if(foodMenu != undefined)
		{
			hideFoodMenu();
		}
	}
}

function handleFood()
{
	showFoodMenu();
}

function showFoodMenu()
{
	if(player.state != "sleep")
	{
		player.state = "idle";
	
		foodMenu.visible = true;
		foodMenu.x = stage.canvas.width*0.75;

		foodMenu.scaleX = stage.canvas.width/foodMenu.image.width*0.25;
		foodMenu.scaleY = stage.canvas.height/foodMenu.image.height;
		
		foodMenu2.x = stage.canvas.width*0.765;
		foodMenu2.y = stage.canvas.height*0.86;
		foodMenu2.scaleX = stage.canvas.width/foodMenu2.image.width*0.11;
		foodMenu2.scaleY = stage.canvas.height/foodMenu2.image.height*0.10;
		foodMenu2.visible = true;
			
		foodMenu3.x = stage.canvas.width*0.875;
		foodMenu3.y = stage.canvas.height*0.86;
		foodMenu3.scaleX = stage.canvas.width/foodMenu3.image.width*0.11;
		foodMenu3.scaleY = stage.canvas.height/foodMenu3.image.height*0.10;
		foodMenu3.visible = true;

		requestFoodItems();
		
		stage.update();
	}
}

function hideFoodMenu()
{
	foodMenu.visible = false;
	foodMenu2.visible = false;
	foodMenu3.visible = false;
	
	for(i=0; i < foodElements.length; i++)
	{
		stage.removeChild(foodElements[i]);
	}
	
	player.state = "patrol";
}

function handleFoodPrev()
{
	if(foodCurrentPage>1)
	{
		foodCurrentPage--;
		
		for(i=0; i < foodElements.length; i++)
		{
			foodElements[i].visible = (Math.ceil((i+1)/6) == foodCurrentPage);
		}
		
		checkArrows();
	}
}

function checkArrows()
{
	foodMenu3.visible = foodElements.length/6 > foodCurrentPage;
	foodMenu2.visible = foodCurrentPage > 1;
}

function handleFoodNext()
{
	if(foodElements.length/6 > foodCurrentPage)
	{
		foodCurrentPage++;
		
		for(i=0; i < foodElements.length; i++)
		{
			foodElements[i].visible = (Math.ceil((i+1)/6) == foodCurrentPage);
		}
		
		checkArrows();
	} 
}

function requestFoodItems()
{
	$.ajax({
        type: "GET",
        url: "/api/food/getFood",
        dataType: "json",
        success: function(data)
		{
			foodElements = new Array();
			
			foodCurrentPage = 1;
			
			i = 0;
			
			$.each(list, function(index, element)
			{
				var sTitle = element.name;
				var sImg = "img/food/"+element.id+".png";
				
				element = new createjs.Bitmap(sImg);
				
				j = i % 6;
				
				if(j % 2 == 0)
				{
					element.x = stage.canvas.width*0.78;
					element.y = stage.canvas.height * (0.10 + 0.25*j*0.5);
				}
				else
				{
					element.x = stage.canvas.width*0.88;
					element.y = stage.canvas.height * (0.10 + 0.25*(j-1)*0.5);
				}
				
				element.scaleX = 0.5;
				element.scaleY = 0.5;
				
				if(i > 5)
				{
					element.visible = false;
				}
				
				stage.addChild(element);
				
				foodElements[i] = element;
				i++;
			});
			
			checkArrows();
		},
		error: function() {
			alert("An error occurred while processing XML file.");
		}
    });
}
 




