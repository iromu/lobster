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

    // Draw the bar
    frame = new createjs.Shape();
    // Draw the external frame
    frame.graphics.beginFill("yellow").drawRect(basex,basey,FRAME_WIDTH, FRAME_HEIGHT);
    // Draw the internal empty space
    frame.graphics.beginFill("brown").drawRect(basex+FRAME_BORDER, basey+FRAME_BORDER,
        FRAME_WIDTH - 2*FRAME_BORDER, FRAME_HEIGHT-2*FRAME_BORDER);
    // Draw the filled bar
    frame.graphics.beginFill("green").drawRect(basex+FRAME_BORDER, basey+FRAME_BORDER,
        (FRAME_WIDTH - 2*FRAME_BORDER)*value/100, FRAME_HEIGHT-2*FRAME_BORDER);

    stage.addChild(frame);
    stage.update();
}

function updateState(data, textStatus, jqXHR)
{
    alert("updateState");

    var state = data;

    createStatusLevelBar(30, 400, "Total Calories", state.totalCalories);
    createStatusLevelBar(30, 450, "Ideal Calories", state.idealCalories);

}

function handleError(  jqXHR,  textStatus,  errorThrown)
{
    alert("queryState - Failed: " + textStatus + ":" + errorThrown);
}

function queryState( )
{
    alert("queryState");

    // TMP: For now we will always query the 1st element
    var id = 1;

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