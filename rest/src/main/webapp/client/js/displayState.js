/**
 * Created with IntelliJ IDEA.
 * User: Josep
 * Date: 9/11/13
 * Time: 3:48
 * To change this template use File | Settings | File Templates.
 */
var FRAME_WIDTH = 200;
var FRAME_HEIGHT = 30;
var FRAME_BORDER = 5;

function createStatusLevelBar(basex, basey, attributeName,value)
{
    // Draw the name of the attribute
    var text = new createjs.Text(attributeName, "20px Arial", "#ffbb55");
    text.x = basex;
    text.y = basey;
    text.textBaseline = "alphabetic";
    stage.addChild(text);

    // Draw the bar
    frame = new createjs.Shape();
    // Draw the external frame
    var framey = basey + 15;//text.getMeasuredHeight();
    frame.graphics.beginFill("yellow").drawRect(basex,framey,FRAME_WIDTH, FRAME_HEIGHT);
    // Draw the internal empty space
    frame.graphics.beginFill("brown").drawRect(basex+FRAME_BORDER, framey+FRAME_BORDER,
        FRAME_WIDTH - 2*FRAME_BORDER, FRAME_HEIGHT-2*FRAME_BORDER);
    // Draw the filled bar
    frame.graphics.beginFill("green").drawRect(basex+FRAME_BORDER, framey+FRAME_BORDER,
        (FRAME_WIDTH - 2*FRAME_BORDER)*value/100, FRAME_HEIGHT-2*FRAME_BORDER);

    stage.addChild(frame);
    stage.update();
}

function updateState(data, textStatus, jqXHR)
{
    //alert("updateState");

    var state = data;

    createStatusLevelBar(30, 200, "Total Calories", state.totalCalories);
    createStatusLevelBar(30, 280, "Ideal Calories", state.idealCalories);
    createStatusLevelBar(30, 360, "Happiness", state.happiness);
    var posy = 440;
    for(var i = 0; i < state.statusVitamineList.length; i++)
    {
        var vitamine = state.statusVitamineList[i];
        createStatusLevelBar(30, posy, vitamine.vitamine.name, vitamine.amount);
        posy+=80;
    }

}

function handleError(  jqXHR,  textStatus,  errorThrown)
{
   window.location="index.htm";
}

function queryState( )
{
    //alert("queryState");

    // Use the Id provided to the page
    var id = getParameterByName("id");

    // Use AJAX to query the Status from the server
    var state;
    $.ajax({
        type: "GET",
        dataType: "json",
        url: "/api/status/getStatus/"+id,
        contentType: "application/json; charset=utf-8",
        // Pass the received state to the updateState function
        success: updateState,
        error: handleError
    });

}