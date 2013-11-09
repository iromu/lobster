function movePatrolling(player)
{
	if(player.direction == "right")
	{
		player.animation.x += 4;
	
		if (player.animation.x > stage.canvas.width * 0.55) 
		{ 
			player.direction = "left";
			player.animation.gotoAndPlay("left");
		}
	}
	else
	{
		player.animation.x -= 4;
	
		if (player.animation.x < stage.canvas.width * 0.44) 
		{ 
			player.direction = "right";
			player.animation.gotoAndPlay("right");
		}
	}
}

function moveIdle(player)
{
	
}

function moveSleeping(player)
{
	
}