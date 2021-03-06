var player;
var light;
var food;
var game;
var isDay;
var foodMenu;
var foodMenu2;
var foodMenu3;
var description;
var text;
var text2;
var gameMenu;
var gameMenu2;
var gameMenu3;
var bg;
var foodElements;
var gameElements;
var foodCurrentPage;
var gameCurrentPage;

var audioPath = "sounds/";
var manifest = [
    {id:"Bite", src:audioPath+"bite.mp3"}
];

function init()
{
	//Create a stage by getting a reference to the canvas
	stage = new createjs.Stage("demoCanvas");
	
	resize();
	createContent();

	createjs.Ticker.addEventListener("tick", handleTick);
    setInterval(queryState, 1000);

    // create a manifest (above)
    //createjs.Sound.addEventListener("loadComplete", handleLoad);
    createjs.Sound.registerManifest(manifest);
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
	
	light.addEventListener("click", handleLight);
	
	stage.addChild(light);
	
	food = new createjs.Bitmap("img/fork.png");
	food.scaleX = 0.3;
	food.scaleY = 0.3;
	food.y = 10;
	food.x = stage.canvas.width - 70;
	food.addEventListener("click", handleFood);
	
	game = new createjs.Bitmap("img/games.png");
	game.scaleX = 0.3;
	game.scaleY = 0.3;
	game.y = 120;
	game.x = stage.canvas.width - 70;
	
	game.addEventListener("click", handleGame);
	
	foodMenu = new createjs.Bitmap("img/popup_menu.png");
	foodMenu2 = new createjs.Bitmap("img/button_01.png");
	foodMenu3 = new createjs.Bitmap("img/button_02.png");
	foodElements = new Array();
	hideFoodMenu();
	
	foodMenu2.addEventListener("click", handleFoodPrev);
	foodMenu3.addEventListener("click", handleFoodNext);
	
	stage.addChild(food);
	stage.addChild(game);
	
	stage.addChild(foodMenu);
	stage.addChild(foodMenu2);
	stage.addChild(foodMenu3);
	
	gameMenu = new createjs.Bitmap("img/popup_menu_pink.png");
	gameMenu2 = new createjs.Bitmap("img/button_01_pink.png");
	gameMenu3 = new createjs.Bitmap("img/button_02_pink.png");
	gameElements = new Array();
	hideGameMenu();
	
	gameMenu2.addEventListener("click", handleGamePrev);
	gameMenu3.addEventListener("click", handleGameNext);
	
	stage.addChild(gameMenu);
	stage.addChild(gameMenu2);
	stage.addChild(gameMenu3);

    queryState();
}

function createPlayer()
{
	player = new function() {
		this.state = "patrol";
		this.direction = "right";
		this.animation = undefined;
		this.name = "unknown";
		loadAnimation(this, "img/guy.png");
		
		this.init = function ()
		{
            basePosX = stage.canvas.width * 0.5;
            basePosY = stage.canvas.height * 0.6;

            this.animation.x = basePosX;
            this.animation.y = basePosY;
		};
		
		this.move = function ()
		{
			switch(this.state)
			{
				case "patrol":
					//if(this.animation.currentAnimation == "right" || this.animation.currentAnimation == "left")
						movePatrolling(this);
					break;
				case "sleep":
					//if(this.animation.currentAnimation == "sleep")
						moveSleeping(this);
					break;
				case "idle":
					//if(this.animation.currentAnimation == "idle")
						moveIdle(this);
					break;
				case "playGame3":
					movePlayGame3(this);
					break;
				case "playGame5":
					movePlayGame5(this);
					break;
			}
		};
		
		this.sleep = function ()
		{
			this.state = "sleep";
			this.animation.gotoAndPlay("sleep");
			stage.update();
		};
		
		this.wakeUp = function ()
		{
			this.state = "patrol";
			this.animation.gotoAndPlay("patrol");
			stage.update();
		};
	}
	
	player.init();
	
	setPlayerName();
	
	stage.update();
}

function loadAnimation(playerTemp, ruta)
{
    playerTemp.playerImg = ruta;

    var oldx, oldy;
	if(playerTemp.animation!=undefined)
	{
        oldx = playerTemp.animation.x;
        oldy = playerTemp.animation.y;
		stage.removeChild(playerTemp.animation);
	}

	data = {
		images: [ruta],
		frames: {width:110, height:186},
		animations: {	right:[16,23,"right",0.4],
						idle:[0,1,"idle",0.1],
						left:[30,37,"left",0.4],
						eat:[2,5,"idle",0.4],
						open:[6,6,"open",0.1],
						sleep:[70,71,"sleep",0.1],
						playGame3:[58,59,"playGame3",0.1],
						playGame5:[60,61,"playGame5",0.1]}
	};

	newAnimation = "";

	switch(playerTemp.state)
	{
		case "patrol":
			newAnimation = playerTemp.direction;
			break;
		case "sleep":
			newAnimation = "sleep";
			break;
		case "idle":
			newAnimation = "idle";
			break;
	}

	playerTemp.spriteSheet = new createjs.SpriteSheet(data);
	playerTemp.animation = new createjs.Sprite(playerTemp.spriteSheet, newAnimation);

    playerTemp.animation.x = oldx;
    playerTemp.animation.y = oldy;
	stage.addChild(playerTemp.animation);
}

function getParameterByName(name)
{
    name = name.replace(/[\[]/, "\\\[").replace(/[\]]/, "\\\]");
    
	var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
    
	results = regex.exec(location.search);
    
	return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

function createContent()
{
	bg = new createjs.Bitmap("img/background_01.png");
	bg.image.onload = function()
	{
		bg.scaleX = stage.canvas.width/bg.image.width;
		bg.scaleY = stage.canvas.height/bg.image.height;
		stage.addChild(bg);
		
		createPlayer();
		createUI();
		
		bg.image.onload = undefined;
        //bg.cache(bg.x, bg.y, bg.image.width, bg.image.height);
	};
	
	bg.addEventListener("click", handleBackground);
	
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
	
    //bg.cache(bg.x, bg.y, bg.image.width, bg.image.height);
	isDay = day;
}

function setPlayerName()
{
	$.ajax({
        type: "GET",
        url: "/api/lobsters/getName/"+getParameterByName("id"),
        success: function(data)
		{
			player.name = data;
			
			playerText = new createjs.Text(player.name, "30px Arial", "#000");
			playerText.x = 0.50 * stage.canvas.width;
			playerText.y = 0.10 * stage.canvas.height;
			playerText.textBaseline = "alphabetic";
			stage.addChild(playerText);
		},
		error: function() {
			alert("An error occurred while processing XML file.");
		}
    });
}

function resize()
{
	stage.canvas.width = window.innerWidth ;
	stage.canvas.height = window.innerHeight - 4.1;
}

function handleTick()
{
    if (player != undefined)
	{
		player.move();
	}
	
	stage.update();
}