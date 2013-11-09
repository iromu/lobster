function movePatrolling(player)
{
	if(player.direction == "right")
	{
		player.body.x += 2;
		player.head.x += 2;
		player.mouth.x += 2;
		player.eye1.x += 2;
		player.eye1in.x += 2;
		player.eye2.x += 2;
		player.eye2in.x += 2;
		player.leg1.x += 2;
		player.leg2.x += 2;
	
		if (player.body.x > stage.canvas.width * 0.55) 
		{ 
			player.direction = "left";
		}
	}
	else
	{
		player.body.x -= 2;
		player.head.x -= 2;
		player.mouth.x -= 2;
		player.eye1.x -= 2;
		player.eye1in.x -= 2;
		player.eye2.x -= 2;
		player.eye2in.x -= 2;
		player.leg1.x -= 2;
		player.leg2.x -= 2;
	
		if (player.body.x < stage.canvas.width * 0.44) 
		{ 
			player.direction = "right";
		}
	}
}

function moveIdle(player)
{
	
}

function moveSleeping(player)
{
	if(player.sleepDirection == "open")
	{
		player.mouth.scaleY += 0.1;
		
		if(player.mouth.scaleY > 2)
		{
			player.sleepDirection = "close";
		}
	}
	else
	{
		player.mouth.scaleY -= 0.1;
		
		if(player.mouth.scaleY < 1)
		{
			player.sleepDirection = "open";
		}
	}
}