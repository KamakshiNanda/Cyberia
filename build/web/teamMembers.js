/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var f=0,event,th=0,p=0;
                function addTM()//add team members for techtonic hunt based on the level.
                {  
                    event=document.getElementById("event").value;
                    if(event==="TH")
                        {
                            th=1;
                            if(document.getElementById("rb1").checked)
                            {
                                event="THS";
                            }
                            else if(document.getElementById("rb2").checked)
                            {
                                event="THC";
                            }
                            else{
                            f=0;
                            document.getElementById("tm0").style.display="none";
                            document.getElementById("tm1").style.display="none";
                            document.getElementById("tm2").style.display="none";
                            document.getElementById("tm3").style.display="none";
                            document.getElementById("tm4").style.display="none";
                            document.getElementById("tm5").style.display="none";
                            document.getElementById("tmn1").required=false;
                            document.getElementById("tmn0").required=false;
                            document.getElementById("tmn2").required=false;
                            document.getElementById("tmn3").required=false;
                            setTimeout(function(){ document.getElementById("main").style.marginTop="15px"; },30);
                            }
                            addTF();
                       }
                }
                function addTMP()//add team members for pubg based on the group selected.
                { 
                    document.getElementById("pubgdiv").style.display="block";
                    document.getElementById("ps").required=true;
                    p=1;
                    if(document.getElementById("ps").checked)
                    {
                        if(f>=1)
                        {
                            document.getElementById("tm0").style.display="none";
                            document.getElementById("tm1").style.display="none";
                            document.getElementById("tm3").style.display="none";
                            document.getElementById("tm2").style.display="none";
                            document.getElementById("tm4").style.display="none";
                            document.getElementById("tm5").style.display="none";
                            document.getElementById("tmn1").required=false;
                            document.getElementById("tmn0").required=false;
                            document.getElementById("tmn2").required=false;
                            document.getElementById("tmn3").required=false;
                            setTimeout(function(){ document.getElementById("main").style.marginTop="15px"; },30);
                        }
                    }
                    else if(document.getElementById("pd").checked)
                    {
                        if(f>=2)
                        {
                            document.getElementById("tm0").style.display="none";
                            document.getElementById("tm3").style.display="none";
                            document.getElementById("tm2").style.display="none";
                            document.getElementById("tmn0").required=false;
                            document.getElementById("tmn3").required=false;
                            document.getElementById("tmn2").required=false;
                        }
                        document.getElementById("tm5").style.display="none";
                        document.getElementById("tm4").style.display="none";
                        document.getElementById("tmn1").required=true;
                        document.getElementById("tm1").style.display="block";
                        setTimeout(function(){ document.getElementById("main").style.marginTop="10px"; },30);
                        f=1;
                    }
                    else if(document.getElementById("psq").checked)
                    {
                        if(f===4)
                        {
                            document.getElementById("tm0").style.display="none";
                            document.getElementById("tmn0").required=false;
                        }
                        document.getElementById("tm5").style.display="none";
                        document.getElementById("tm4").style.display="none";
                        document.getElementById("tm1").style.display="block";
                        document.getElementById("tm2").style.display="block";
                        document.getElementById("tm3").style.display="block";
                        document.getElementById("tmn1").required=true;
                        document.getElementById("tmn2").required=true;
                        document.getElementById("tmn3").required=true;
                        setTimeout(function(){ document.getElementById("main").style.marginTop="0.5%"; },30);
                        f=3;
                    }
                }
		function addTF()//add text fields for all other events.
		{   
                    document.getElementById("ps").required=false;
                    if(th===1)
                    {
                        th=0;
                    }
                    else
                    {
                        event=document.getElementById("event").value;
                    }
                    if(event==="TH")
                    {
                        addTM();
                    }
                    if(event==="VWP")
                    {
                        addTMP();
                    }
                    else if(p===1)
                    {
                       document.getElementById("ps").required=false;
                       document.getElementById("pubgdiv").style.display="none";
					   document.getElementById("ps").checked=false;
					   document.getElementById("pd").checked=false;
					   document.getElementById("psq").checked=false;
                       p=0;
                    }
                    if(event==="TKT")
                    {
                        if(f>=2)
                        {
                            document.getElementById("tm3").style.display="none";
                            document.getElementById("tm2").style.display="none";
                            document.getElementById("tm0").style.display="none";
                            document.getElementById("tmn3").required=false;
                            document.getElementById("tmn2").required=false;
                            document.getElementById("tmn0").required=false;
                        }
                        document.getElementById("tmn1").required=false;
                        document.getElementById("tm1").style.display="none";
                        document.getElementById("tm4").style.display="block";
                        document.getElementById("tm5").style.display="block";
                        setTimeout(function(){ document.getElementById("main").style.marginTop="10px"; },30);
                        f=2;
                    }
                    else if(event==="CG"||event==="CH"||event==="BTV")
                    {
                        if(f>=2)
                        {
                            document.getElementById("tm0").style.display="none";
                            document.getElementById("tm3").style.display="none";
                            document.getElementById("tm2").style.display="none";
                            document.getElementById("tmn3").required=false;
                            document.getElementById("tmn2").required=false;
                            document.getElementById("tmn0").required=false;
                        }
                        document.getElementById("tm5").style.display="none";
                        document.getElementById("tm4").style.display="none";
                        document.getElementById("tm1").style.display="block";
                        document.getElementById("tmn1").required=true;
                        setTimeout(function(){ document.getElementById("main").style.marginTop="10px"; },30);
                        f=1;
                        //alert("got c");
                    }
                    else if(event==="THS"||event==="EX")
                    {
                        if(f===3||f===4)
                        {
                            document.getElementById("tm0").style.display="none";
                            document.getElementById("tm3").style.display="none";
                            document.getElementById("tmn0").required=false;
                            document.getElementById("tmn3").required=false;
                        }
                        document.getElementById("tm5").style.display="none";
                        document.getElementById("tm4").style.display="none";
                        document.getElementById("tmn1").required=true;
                        document.getElementById("tmn2").required=true;
                        document.getElementById("tm2").style.display="block";
                        document.getElementById("tm1").style.display="block";
                        setTimeout(function(){ document.getElementById("main").style.marginTop="10px"; },30);
                        f=2;
                    }
                    else if(event==="HA"||event==="THC")
                    {
                        if(f===4)
                        {
                            document.getElementById("tm0").style.display="none";
                        }
                        document.getElementById("tm5").style.display="none";
                        document.getElementById("tm4").style.display="none";
                        document.getElementById("tm1").style.display="block";
                        document.getElementById("tm2").style.display="block";
                        document.getElementById("tm3").style.display="block";
                        if(event==="THC")
                        {
                            document.getElementById("tmn1").required=true;
                            document.getElementById("tmn2").required=true;
                            document.getElementById("tmn3").required=true;
                        }
                        else if(event==="HA")
                        {
                            document.getElementById("tmn1").required=false;
                            document.getElementById("tmn2").required=false;
                            document.getElementById("tmn3").required=false;
                        }
                        setTimeout(function(){ document.getElementById("main").style.marginTop="0.5%"; },30);
                        f=3;
			//alert("got hc");
                    }
                    else if(event==="VWC"||event==="VWCG")
                    {
                        document.getElementById("tm5").style.display="none";
                        document.getElementById("tm4").style.display="none";
                        document.getElementById("tm1").style.display="block";
                        document.getElementById("tm2").style.display="block";
                        document.getElementById("tm3").style.display="block";
                        document.getElementById("tm0").style.display="block";
                        document.getElementById("tmn1").required=true;
                        document.getElementById("tmn2").required=true;
                        document.getElementById("tmn3").required=true;
                        document.getElementById("tmn0").required=true;
                        setTimeout(function(){ document.getElementById("main").style.marginTop="0.5%"; },30);
                        f=4;
			//alert("got hc");
                    }
                    else if(f===1 && event!=="VWP")
                       	{
                            f=0;
                            document.getElementById("tm1").style.display="none";
                            document.getElementById("tm4").style.display="none";
                            document.getElementById("tm5").style.display="none";
                            document.getElementById("tmn1").required=false;
                            setTimeout(function(){ document.getElementById("main").style.marginTop="15px"; },30);
                        }
                    else if(f===3||f===2 && event!="VWP")
                        {
                            f=0;
                            document.getElementById("tm1").style.display="none";
                            document.getElementById("tm2").style.display="none";
                            document.getElementById("tm3").style.display="none";
                            document.getElementById("tmn1").required=false;
                            document.getElementById("tmn2").required=false;
                            document.getElementById("tmn3").required=false;
                            document.getElementById("tm4").style.display="none";
                            document.getElementById("tm5").style.display="none";
                            setTimeout(function(){ document.getElementById("main").style.marginTop="15px"; },30);
                       }
                       else if(f===4)
                       {
                            f=0;
                            document.getElementById("tm0").style.display="none";
                            document.getElementById("tm1").style.display="none";
                            document.getElementById("tm2").style.display="none";
                            document.getElementById("tm3").style.display="none";
                            document.getElementById("tmn0").required=false;
                            document.getElementById("tmn1").required=false;
                            document.getElementById("tmn2").required=false;
                            document.getElementById("tmn3").required=false;
                            document.getElementById("tm4").style.display="none";
                            document.getElementById("tm5").style.display="none";
                            setTimeout(function(){ document.getElementById("main").style.marginTop="15px"; },30);
                       }
                    	//alert("add");
		}

