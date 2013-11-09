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
	if(foodMenu != undefined)
	{
		hideFoodMenu();
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
}

function requestFoodItems()
{
	$.ajax({
        type: "GET",
        crossDomain: false,  //edit du 25/01 : cette propriété doit être passée à false. 
        url: "data/food.xml",
        dataType: "xml",
        success: function(data)
		{
			foodElements = new Array();
			
			i = 0;
			$(data).find('food').each(function()
			{
				var sTitle = $(this).find('title').text();
				var sImg = $(this).find('img').text();
				
				element = new createjs.Bitmap(sImg);
				element.x = stage.canvas.width*0.75;
				
				stage.addChild(element);
				
				foodElements[i] = element;
				i++;
			});
		},
		error: function() {
			alert("An error occurred while processing XML file.");
		}
    });
}
 




