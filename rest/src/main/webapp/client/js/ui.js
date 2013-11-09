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
		//foodMenu = new createjs.Shape();
		//foodMenu.graphics.beginFill("#fff").drawRect(stage.canvas.width*0.68,0,stage.canvas.width*0.33,stage.canvas.height);
		
		foodMenu = new createjs.Bitmap("img/popup_menu.png");
		//foodMenu.snapToPixel =false;
		foodMenu.scaleX = stage.canvas.width/foodMenu.image.width*0.25;
		foodMenu.scaleY = stage.canvas.height/foodMenu.image.height;
		console.trace(stage.canvas.height+" "+bg.image.height);
		
		foodMenu.x = stage.canvas.width*0.75;
		
		stage.addChild(foodMenu);
		
		requestFoodItems();
	}
}

function hideFoodMenu()
{
	stage.removeChild(foodMenu);
	foodMenu = undefined;
}

function requestFoodItems()
{
	$.ajax({
        type: "GET",
        crossDomain: false,  //edit du 25/01 : cette propriété doit être passée à false. 
        url: "data/food.xml",
        dataType: "xml",
        success: processFood
    });
}

this.processFood = function(data)
{
    alert("pepe");         
}
 




