/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var opcionesItems=[];
var opcionesItemsSelected=[];
var opcionesUser=[];

window.addEventListener("load",function(arg)
    {
        var itms = document.getElementById("numerosS").value.split(",");
        opcionesItemsSelected = document.getElementById("numerosSe").value.split(",");
        
        for (var i = 0; i < itms.length-1; i++) 
        {
            opcionesItems.push(itms[i]);
        }
        
        var contenedor = document.getElementById("conten");        
        var textoS=opcionesItemsSelected.join("*");
            textoS = "*"+textoS+"*";
            
        for (var i = 0; i < opcionesItems.length; i++) 
        {
            var idItem = document.createElement("div");
            var idItemP = document.createElement("p");
            var texto = document.createTextNode(opcionesItems[i]);
            
            //idItem.appendChild(idItemP);
            idItem.appendChild(texto);
            contenedor.appendChild(idItem);
            
            if (textoS.indexOf("*"+opcionesItems[i]+"*")>=0) 
            {
                idItem.className="optionItemSelected";
            }
            else
            {
                idItem.className="optionItem";
                idItem.addEventListener("click",selectOpcion);
            }
        }
    });

function selectOpcion ()
{
    for (var i = 0; i < window.event.path.length; i++) 
    {
        if(window.event.path[i].className === "optionItem")
        {
            console.log(window.event.path[i].innerHTML);
            if (window.event.path[i].style.backgroundColor==="rgb(166, 143, 129)") 
            {
                window.event.path[i].style.backgroundColor="#735D55";
            }
            else
            {
                window.event.path[i].style.backgroundColor="#A68F81";
            }
        }
    }
}

function confirmar()
{
    opcionesUser=[];
    
    if (document.getElementById("txtNombre").value.length>=10 && 
            document.getElementById("txtNombre").value.length<=30) 
    {
        if (document.getElementById("txtCedula").value.length>=7 &&
                document.getElementById("txtCedula").value.length<=15) 
        {
            if (document.getElementById("txtTelefono").value.length>=7 &&
                document.getElementById("txtTelefono").value.length<=10) 
            {
                var opciones = document.getElementsByClassName("optionItem");

                for (var i = 0; i < opciones.length; i++) 
                {
                    if (opciones[i].style.backgroundColor==="rgb(166, 143, 129)") 
                    {
                        opcionesUser.push(opciones[i].innerHTML);
                    }
                }

                if (opcionesUser.length === 0) 
                {
                    alert("Debe seleccionar al menos un número para participar en la rifa");
                    return false;
                }
                else
                {
                    if (!confirm("¿Esta seguro de participar?\n"+
                        "los números seleccionados son: "+opcionesUser))
                    {
                        return false;
                    }
                    else
                    {
                        document.getElementById("nums").value=opcionesUser;
                    }
                }
            }
            else
            {
                alert("El telefono del usuario debe contener entre 7 y 10 números");
                return false;
            }
        }
        else
        {
            alert("La cédula del usuario debe contener entre 7 y 15 números");
            return false;
        }
    }
    else
    {
        alert("El nombre del usuario debe contener entre 10 y 30 caracteres");
        return false;
    }
}