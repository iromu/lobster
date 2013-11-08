var player;
var light;
var food;
var isDay;
var foodMenu;
var bg;

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
	// light = new createjs.bitmap("img/light_on.png");
	// light.scalex = 0.3;
	// light.scaley = 0.3;
	light = new createjs.Shape();
	light.graphics.beginFill("white").drawCircle(5, 5, 10);
	
	food = new createjs.Shape();
	food.graphics.beginFill("white").drawCircle(5, 5, 10);
	
	food.x = stage.canvas.width - 10;
	
	light.addEventListener("click", handleLight);
	food.addEventListener("click", handleFood);
	
	stage.addChild(light);
	stage.addChild(food);
}

function createPlayer()
{
	player = new function() {
		this.eye1 = new createjs.Shape();
		this.eye2 = new createjs.Shape();
		this.eye1in = new createjs.Shape();
		this.eye2in = new createjs.Shape();
		this.head = new createjs.Shape();
		this.mouth = new createjs.Shape();
		this.body = new createjs.Shape();
		this.leg1 = new createjs.Shape();
		this.leg2 = new createjs.Shape();
		this.state = "patrol";
		this.direction = "right";
		this.sleepDirection = "open";
		this.init = function ()
		{
			basePosX = stage.canvas.width * 0.5;
			basePosY = stage.canvas.height * 0.75;
			
			this.eye1.graphics.beginFill("white").drawCircle(0, 0, 10);
			this.eye1.x = basePosX - 15;
			this.eye1.y = basePosY - 40;
			
			this.eye1in.graphics.beginFill("black").drawEllipse(-5, -5, 10, 10);
			this.eye1in.x = basePosX - 15;
			this.eye1in.y = basePosY - 40;
			
			this.eye2.graphics.beginFill("white").drawCircle(0, 0, 10);
			this.eye2.x = basePosX + 15;
			this.eye2.y = basePosY - 40;
			
			this.eye2in.graphics.beginFill("black").drawEllipse(-5, -5, 10, 10);
			this.eye2in.x = basePosX + 15;
			this.eye2in.y = basePosY - 40;
			
			this.body.graphics.beginFill("red").drawCircle(0, 0, 40);
			this.body.x = basePosX;
			this.body.y = basePosY;
			
			this.mouth.graphics.beginFill("white").drawEllipse(0, 0, 20,10);
			this.mouth.x = basePosX - 10;
			this.mouth.y = basePosY;
			
			this.body.shadow = new createjs.Shadow("#000000", 0, 10, 10);
			
			stage.addChild(this.body);
			stage.addChild(this.head);
			stage.addChild(this.mouth);
			stage.addChild(this.eye1);
			stage.addChild(this.eye1in);
			stage.addChild(this.eye2);
			stage.addChild(this.eye2in);
			stage.addChild(this.leg1);
			stage.addChild(this.leg2);
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
}

function createBackground()
{
	bg = new createjs.Bitmap("img/background_01.png");
	bg.addEventListener("click", handleBackground);
	bg.scaleX = stage.canvas.width/bg.image.width;
	bg.scaleY = stage.canvas.height/bg.image.height;
	stage.addChild(bg);
	
	setBackground(true);
}

function setBackground(day)
{
	//bg.graphics.clear();
	
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