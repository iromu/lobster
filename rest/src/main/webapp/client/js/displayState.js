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

var totalCaloriesStatusLevelBar = null;
var fatStatusLevelBar = null;
var happinessStatusLevelBar = null;
var vitaminAStatusLevelBar = null;
var vitaminBStatusLevelBar = null;
var vitaminCStatusLevelBar = null;
var vitaminDStatusLevelBar = null;
var calciumStatusLevelBar = null;

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

    stage.addChild(frame);

    return frame;
}

function updateStatusLevelBarHorz(barCtrl, func,basex, basey, attributeName,value)
{
    if (barCtrl === null)
    {
        barCtrl = func(basex, basey, attributeName, value);
    }
    // Draw the external frame
    var framey = basey + 10;//text.getMeasuredHeight();
    barCtrl.graphics.beginFill("yellow").drawRect(basex,framey,FRAME_WIDTH, FRAME_HEIGHT);
    // Draw the internal empty space
    barCtrl.graphics.beginFill("brown").drawRect(basex+FRAME_BORDER, framey+FRAME_BORDER,
        FRAME_WIDTH - 2*FRAME_BORDER, FRAME_HEIGHT-2*FRAME_BORDER);
    // Draw the filled bar
    barCtrl.graphics.beginFill("green").drawRect(basex+FRAME_BORDER, framey+FRAME_BORDER,
        (FRAME_WIDTH - 2*FRAME_BORDER)*value/100, FRAME_HEIGHT-2*FRAME_BORDER);
    stage.update();
}

function updateStatusLevelBarVert ()
{

}

function updateState(data, textStatus, jqXHR)
{
    //alert("updateState");

    var state = data;

    updateStatusLevelBarHorz(totalCaloriesStatusLevelBar,createStatusLevelBar, 30, 160, "Total Calories", state.totalCalories)
    updateStatusLevelBarHorz(fatStatusLevelBar, createStatusLevelBar, 30, 220, "Fat level", state.fatLevel);
    updateStatusLevelBarHorz(happinessStatusLevelBar, createStatusLevelBar, 30, 280, "Happiness", state.happiness);
    updateStatusLevelBarHorz(vitaminAStatusLevelBar,createStatusLevelBar, 30, 360, "Vit A", state.statusVitamineList[0].amount)
    updateStatusLevelBarHorz(vitaminBStatusLevelBar, createStatusLevelBar, 30, 420, "Vit B", state.statusVitamineList[1].amount);
    updateStatusLevelBarHorz(vitaminCStatusLevelBar, createStatusLevelBar, 30, 480, "Vit C", state.statusVitamineList[2].amount);
    updateStatusLevelBarHorz(vitaminDStatusLevelBar,createStatusLevelBar, 30, 540, "Vit D", state.statusVitamineList[3].amount)
    updateStatusLevelBarHorz(calciumStatusLevelBar, createStatusLevelBar, 30, 600, "Calcium", state.statusVitamineList[4].amount);


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