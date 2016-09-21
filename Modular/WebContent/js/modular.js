/**
 * 
 */

function mudarestado(el){
    if(document.getElementById(el).style.display == "none")
        document.getElementById(el).style.display = 'block';
    else
        document.getElementById(el).style.display = 'none';
}