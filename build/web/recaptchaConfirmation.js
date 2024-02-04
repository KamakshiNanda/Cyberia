/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function checkCap() 
{
    var response = grecaptcha.getResponse();
    //console.log(response);
    if(response.length == 0) {
        document.getElementById('g-recaptcha-error').innerHTML = '<span style="color:red;">Recaptcha is required.</span>';
        return false;
    }
    sendSignInf(response);
    console.log(response);
}
function sendSignInf(response)
{
    if(window.XMLHttpRequest)
    {
        request=new XMLHttpRequest();
    }
    else if(window.ActiveXObject)
    {
        request=new ActiveXObject("Microsoft.XMLHTTP");
        //alert("ActiveXObject object created");
    }
    try 
    {
        request.onreadystatechange=getSignInf;
        request.open("POST","RegistrationHandler",true);
        request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        request.send("response="+response);
    }
    catch(e)
    {
        alert("Unable to connect to server."+e);
    }
}
function getSignInf()
{
    console.log("getsignin called"+request.readyState);
    if(request.readyState==4)
    {
        var text=request.responseText;
        var obj = JSON.parse(text); 
        console.log(text);
        if(obj.success==true)
        {
            document.getElementById('g-recaptcha-error').innerHTML = '<span style="color:white;">correct recaptcha.</span>';
            //console.log("true");
            document.getElementById("sub").click();
            //document.getElementById("displayError").style.display="block";
        }
        else 
        {
            //alert("we are submitting the form"); 
            document.getElementById('g-recaptcha-error').innerHTML = '<span style="color:red;">wrong recaptcha.</span>';
            //console.log("false");
            //document.getElementById("sub").click();
        }
    }   
}