function handleLight()
{
	isDay = !isDay;
	
	if(isDay)
	{
		light.image.src = "img/light_on.png";
		player.wakeUp();
	}
	else
	{
		light.image.src = "img/light_off.png";
		player.sleep();	
	}
	setBackground(isDay);
	
	stage.update();
}

function handleBackground()
{
	if(player.state != "sleep")
	{
		if(foodMenu != undefined)
		{
			hideFoodMenu();
		}
		
		if(gameMenu != undefined)
		{
			hideGameMenu();
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
		player.animation.gotoAndPlay("idle");
	
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
		stage.removeChild(foodElements[i].bitmap);
	}
	
	player.state = "patrol";
	
	if(player.direction == "left")
	{
		player.animation.gotoAndPlay("left");
	}
	else
	{
		player.animation.gotoAndPlay("right");
	}
}

function showGameMenu()
{
	if(player.state != "sleep")
	{
		player.state = "idle";
		player.animation.gotoAndPlay("idle");
	
		gameMenu.visible = true;
		gameMenu.x = stage.canvas.width*0.75;

		gameMenu.scaleX = stage.canvas.width/gameMenu.image.width*0.25;
		gameMenu.scaleY = stage.canvas.height/gameMenu.image.height;
		
		gameMenu2.x = stage.canvas.width*0.765;
		gameMenu2.y = stage.canvas.height*0.86;
		gameMenu2.scaleX = stage.canvas.width/gameMenu2.image.width*0.11;
		gameMenu2.scaleY = stage.canvas.height/gameMenu2.image.height*0.10;
		gameMenu2.visible = true;
			
		gameMenu3.x = stage.canvas.width*0.875;
		gameMenu3.y = stage.canvas.height*0.86;
		gameMenu3.scaleX = stage.canvas.width/gameMenu3.image.width*0.11;
		gameMenu3.scaleY = stage.canvas.height/gameMenu3.image.height*0.10;
		gameMenu3.visible = true;

		requestGameItems();
		
		stage.update();
	}
}

function hideGameMenu()
{
	gameMenu.visible = false;
	gameMenu2.visible = false;
	gameMenu3.visible = false;
	
	for(i=0; i < gameElements.length; i++)
	{
		stage.removeChild(gameElements[i].bitmap);
	}
	
	player.state = "patrol";
	
	if(player.direction == "left")
	{
		player.animation.gotoAndPlay("left");
	}
	else
	{
		player.animation.gotoAndPlay("right");
	}
}

function handleGamePrev()
{
	if(gameCurrentPage>1)
	{
		gameCurrentPage--;
		
		for(i=0; i < gameElements.length; i++)
		{
			gameElements[i].bitmap.visible = (Math.ceil((i+1)/6) == gameCurrentPage);
		}
		
		checkGameArrows();
	}
}

function handleGame()
{
	showGameMenu();
}

function checkGameArrows()
{
	gameMenu3.visible = gameElements.length/6 > gameCurrentPage;
	gameMenu2.visible = gameCurrentPage > 1;
}

function handleGameNext()
{
	if(gameElements.length/6 > gameCurrentPage)
	{
		gameCurrentPage++;
		
		for(i=0; i < gameElements.length; i++)
		{
			gameElements[i].bitmap.visible = (Math.ceil((i+1)/6) == gameCurrentPage);
		}
		
		checkGameArrows();
	} 
}

function handleFoodPrev()
{
	if(foodCurrentPage>1)
	{
		foodCurrentPage--;
		
		for(i=0; i < foodElements.length; i++)
		{
			foodElements[i].bitmap.visible = (Math.ceil((i+1)/6) == foodCurrentPage);
		}
		
		checkFoodArrows();
	}
}

function checkFoodArrows()
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
			foodElements[i].bitmap.visible = (Math.ceil((i+1)/6) == foodCurrentPage);
		}
		
		checkFoodArrows();
	} 
}

function eat(element) {
	
	player.state = "eat";
	player.animation.gotoAndPlay("eat");

    // Use the Id provided to the page
    var id = getParameterByName("id");

    $.ajax({
        type: "POST",
        url: "/api/lobster/" + id + "/givefood/" + element.id,
        dataType: "json",
        success: updateState,
        error: handleError
    });

    //queryState();
}

function play(element)
{
	player.state = "play";
	player.animation.gotoAndPlay("play");

    // Use the Id provided to the page
    var id = getParameterByName("id");

    $.ajax({
        type: "POST",
        url: "/api/lobster/" + id + "/doActivity/" + element.id,
        dataType: "json",
        success: function (data, textStatus, jqXHR)
		{
			this.state = "playGame"+element.id;
			player.animation.gotoAndPlay("playGame"+element.id);
        },
        error: function (jqXHR, textStatus, errorThrown)
		{
			alert(errorThrown);
        }
    });

    queryState();
}

function requestFoodItems()
{
	$.ajax({
        type: "GET",
        url: "/api/food/list",
        dataType: "json",
        success: function(data)
		{
			foodElements = new Array();
			
			foodCurrentPage = 1;
			
			i = 0;
			
			$.each(data, function(index, item)
			{
				var sTitle = item.name;
				var sImg = "img/food/"+item.id+".png";
				
				var element = new function()
				{
					this.id = item.id;
					this.name = item.name;
					this.bitmap = new createjs.Bitmap(sImg);
				}
				
				j = i % 6;
				
				if(j % 2 == 0)
				{
					element.bitmap.x = stage.canvas.width*0.78;
					element.bitmap.y = stage.canvas.height * (0.10 + 0.25*j*0.5);
				}
				else
				{
					element.bitmap.x = stage.canvas.width*0.88;
					element.bitmap.y = stage.canvas.height * (0.10 + 0.25*(j-1)*0.5);
				}
				
				element.posX = element.bitmap.x;
				element.posY = element.bitmap.y;
				
				element.bitmap.scaleX = 0.5;
				element.bitmap.scaleY = 0.5;
				
				if(i > 5)
				{
					element.bitmap.visible = false;
				}
				
				element.bitmap.on("mousedown", function(evt) {
					this.parent.addChild(this);
					this.offset = {x:this.x-evt.stageX, y:this.y-evt.stageY};
				});
				element.bitmap.on("pressup", function(evt) {
					distance = Math.sqrt(Math.pow(player.animation.x-this.x, 2) + Math.pow(player.animation.y-this.y, 2));
					
					if(distance < 150)
					{
                        element.bitmap.alpha = 0;
						eat(element);
                        createjs.Tween.get(element.bitmap).to({alpha: 1}, 3000).set({visible: true});
					}

					this.x = element.posX;
					this.y = element.posY;
				});
				element.bitmap.on("pressmove", function(evt) {
					this.x = evt.stageX+ this.offset.x;
					this.y = evt.stageY+ this.offset.y;
					update = true;
				});
				
				stage.addChild(element.bitmap);
				
				foodElements[i] = element;
				i++;
			});
			
			checkFoodArrows();
		},
		error: function() {
			alert("An error occurred while processing XML file.");
		}
    });
}
 
function requestGameItems()
{
	$.ajax({
        type: "GET",
        url: "/api/activity/list",
        dataType: "json",
        success: function(data)
		{
			gameElements = new Array();
			
			gameCurrentPage = 1;
			
			i = 0;
			
			$.each(data, function(index, item)
			{
				var sTitle = item.name;
				var sImg = "img/game/"+item.id+".png";
				
				var element = new function()
				{
					this.id = item.id;
					this.name = item.name;
					this.bitmap = new createjs.Bitmap(sImg);
				}
				
				j = i % 6;
				
				if(j % 2 == 0)
				{
					element.bitmap.x = stage.canvas.width*0.78;
					element.bitmap.y = stage.canvas.height * (0.10 + 0.25*j*0.5);
				}
				else
				{
					element.bitmap.x = stage.canvas.width*0.88;
					element.bitmap.y = stage.canvas.height * (0.10 + 0.25*(j-1)*0.5);
				}
				
				element.posX = element.bitmap.x;
				element.posY = element.bitmap.y;
				
				element.bitmap.scaleX = 0.5;
				element.bitmap.scaleY = 0.5;
				
				if(i > 5)
				{
					element.bitmap.visible = false;
				}
				
				element.bitmap.on("mousedown", function(evt) {
					this.parent.addChild(this);
					this.offset = {x:this.x-evt.stageX, y:this.y-evt.stageY};
				});
				element.bitmap.on("pressup", function(evt) {
					distance = Math.sqrt(Math.pow(player.animation.x-this.x, 2) + Math.pow(player.animation.y-this.y, 2));
					
					if(distance < 150)
					{
						play(element);
					}

					this.x = element.posX;
					this.y = element.posY;
				});
				element.bitmap.on("pressmove", function(evt) {
					this.x = evt.stageX+ this.offset.x;
					this.y = evt.stageY+ this.offset.y;
					update = true;
				});
				
				stage.addChild(element.bitmap);
				
				gameElements[i] = element;
				i++;
			});
			
			checkGameArrows();
		},
		error: function() {
			alert("An error occurred while processing XML file.");
		}
    });
}



