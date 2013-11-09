var player;
var light;
var food;
var isDay;
var foodMenu;
var foodMenu2;
var foodMenu3;
var bg;
var foodElements;
var foodCurrentPage;

function init()
{
	//Create a stage by getting a reference to the canvas
	stage = new createjs.Stage("demoCanvas");
	
	resize();
	createBackground();
	createPlayer();
	createUI();

	createjs.Ticker.addEventListener("tick", handleTick);
}

function onResize()
{
	// browser viewport size
	var w = window.innerWidth;
	var h = window.innerHeight;

	// stage dimensions
	var ow = 640; // your stage width
	var oh = 480; // your stage height

	if (keepAspectRatio)
	{
		// keep aspect ratio
		var scale = Math.min(w / ow, h / oh);
		stage.scaleX = scale;
		stage.scaleY = scale;

		// adjust canvas size
		stage.canvas.width = ow * scale;
		stage.canvas.height = oh * scale;
	}
	else
	{
		// scale to exact fit
		stage.scaleX = w / ow;
		stage.scaleY = h / oh;

		// adjust canvas size
		stage.canvas.width = ow * stage.scaleX;
		stage.canvas.height = oh * stage.scaleY;
	}

	 // update the stage
	stage.update();
}

function createUI()
{
	light = new createjs.Bitmap("img/light_on.png");
	light.scaleX = 0.3;
	light.scaleY = 0.3;
	light.y = 10;
	
	food = new createjs.Bitmap("img/fork.png");
	food.scaleX = 0.3;
	food.scaleY = 0.3;
	food.y = 10;
	food.x = stage.canvas.width - 70;
	
	light.addEventListener("click", handleLight);
	food.addEventListener("click", handleFood);
	
	foodMenu = new createjs.Bitmap("img/popup_menu.png");
	foodMenu2 = new createjs.Bitmap("img/button_01.png");
	foodMenu3 = new createjs.Bitmap("img/button_02.png");
	foodElements = new Array();
	hideFoodMenu();
	
	foodMenu2.addEventListener("click", handleFoodPrev);
	foodMenu3.addEventListener("click", handleFoodNext);
	
	stage.addChild(light);
	stage.addChild(food);
	stage.addChild(foodMenu);
	stage.addChild(foodMenu2);
	stage.addChild(foodMenu3);
}

function createPlayer()
{
	player = new function() {
		this.state = "patrol";
		this.direction = "right";
		this.sleepDirection = "open";
		
		this.data = {
			images: ["img/guy.png"],
			frames: {width:110, height:186},
			animations: {right:[16,21,"right",0.1], idle:[0,1,"idle",0.1], left:[31,36,"left",0.1], eat:[2,5,"eat",0.1]}
		};
		
		this.spriteSheet = new createjs.SpriteSheet(this.data);
		this.animation = new createjs.Sprite(this.spriteSheet, "right");
		
		this.init = function ()
		{
			basePosX = stage.canvas.width * 0.5;
			basePosY = stage.canvas.height * 0.6;
			
			this.animation.x = basePosX;
			this.animation.y = basePosY;
			
			//this.animation.shadow = new createjs.Shadow("#000000", 0, 10, 10);
			
			stage.addChild(this.animation);
		};
		
		this.move = function ()
		{
			switch(this.state)
			{
				case "patrol":
					movePatrolling(this);
					break;
				case "sleep":
					moveSleeping(this);
					break;
				case "idle":
					moveIdle(this);
					break;
			}
		};
		
		this.sleep = function ()
		{
			this.eye1in.scaleY = 0.3;
			this.eye2in.scaleY = 0.3;
			this.state = "sleep";
		};
		
		this.wakeUp = function ()
		{
			this.eye1in.scaleY = 1;
			this.eye2in.scaleY = 1;
			player.mouth.scaleY = 1;
			this.state = "patrol";
		};
	}
	
	player.init();
	
	stage.update();
}

function createBackground()
{
	bg = new createjs.Bitmap("img/background_01.png");
	bg.addEventListener("click", handleBackground);
	bg.scaleX = stage.canvas.width/bg.image.width;
	bg.scaleY = stage.canvas.height/bg.image.height;
	stage.addChild(bg);
	
	isDay = true;
}

function setBackground(day)
{
	if(day)
	{
		bg.image.src = "img/background_01.png";
	}
	else
	{
		bg.image.src = "img/background_02.png";
	}
	
	isDay = day;
}

function resize()
{
	stage.canvas.width = window.innerWidth - 5;
	stage.canvas.height = window.innerHeight - 5;
}

function handleTick()
{
	player.move();
	
	stage.update();	
}