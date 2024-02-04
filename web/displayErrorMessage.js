/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var request;

function checkEvent(){
    if(document.getElementById("event").value==="0")
    {
         alert("Please select an event.");
         //displayRecaptcha();
            return false;
    }
}
/*function displayRecaptcha(){
         var g=document.getElementsByTagName("g-recaptcha-response");
         alert(g[0]);
}*/
function sendSignInfo()
{
    document.getElementById("displayError").style.display="none";
    var fname=document.getElementById("first_name").value;
    var mname=document.getElementById("middle_name").value;
    var lname=document.getElementById("last_name").value;
    var mobno=document.getElementById("mobno").value;
    var ea=document.getElementById("email").value;
    var eve=document.getElementById("event").value;
    var ins=document.getElementById("institute").value;
    
    //get level
    if(document.getElementById("rb1").checked===true)
	var level=document.getElementById("rb1").value;
    else if(document.getElementById("rb2").checked===true)
	var level=document.getElementById("rb2").value;
    //get pubg type
    if(document.getElementById("ps").checked===true)
	var pubg=document.getElementById("ps").value;
    else if(document.getElementById("pd").checked===true)
	var pubg=document.getElementById("pd").value;
    else if(document.getElementById("psq").checked===true)
	var pubg=document.getElementById("psq").value;
    if(window.XMLHttpRequest)
    {
        request=new XMLHttpRequest();
        //alert("XMLHTTPRequest object created");
    }
    else if(window.ActiveXObject)
    {
        request=new ActiveXObject("Microsoft.XMLHTTP");
        alert("ActiveXObject object created");
    }
    try 
    {
        request.onreadystatechange=getSignInfo;
        request.open("POST","RegistrationHandler",true);
        request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        //request.send("ea="+ea); 
        request.send("fname="+fname+"&mname="+mname+"&lname="+lname+"&mob="+mobno+"&ea="+ea+"&lev="+level+"&eve="+eve+"&institute="+ins+"&pubg="+pubg);
        //alert(request.readyState); 
    }
    catch(e)
    {
        alert("Unable to connect to server."+e);
    }
}
function getSignInfo()
{
    if(request.readyState==4)
    {
        var text=request.responseText;
        //alert("we got the text"+text);
        if(text=="User is already registered for the same event.")
        {
            document.getElementById("displayError").innerHTML=text;
            document.getElementById("displayError").style.display="block";
        }
        else 
        {
            //alert("we are submitting the form");
                /*if(checkCap())
                {
                    document.getElementById("sub").click();
                } */
            
            checkCap();
        }
    }   
}

