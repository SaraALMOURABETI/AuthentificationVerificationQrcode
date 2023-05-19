var message_valeur=document.querySelector(".information").children[1];
var  Nom,Prenom,NomSociete,email,Numero= " " ;
var valeur;
//CECI NOUS PERMET DE SELECTIONNER LE 2 EME PARAGRAHPE DANS LA DIV AYANT LA CLASS INFORMATION
window.onload=()=>{
    valeur="Aucune valeur";
    message_valeur.innerHTML=valeur;
}
document.forms[0].onchange=()=>{
    console.log("change");
    console.log(Prenom)
}
var qr = new QRious({
    element: document.querySelector('.qrious'),
    size: 250,
    value: valeur
  });
function change(element) {
switch (element.name) {
    case "Nom":
        Nom=element.value;
      break;
       case "Prenom":
              Prenom=element.value;
            break;
    case "NomSociete":
        NomSociete=element.value
     break;
    case "Numero":
        Numero=element.value;    
    break;
    case "email":
        email=element.value;
    break;

    
}

valeur=Nom+'-'+Prenom+'-'+NomSociete+'-'+Numero+'-'+email+'-';
qr.value=valeur;
message_valeur.innerHTML=qr.value;

  
   
}



  