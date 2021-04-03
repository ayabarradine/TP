/**
 * 
 */

$(document).ready(function() {
	let $listeProduits = $("#listProduits");
	let $listeClients = $("#listClients");
	let $selectProduits = $("#selectProduits");

    $.get("http://localhost:8080/api/clients",function(resp){
		resp.forEach( p => appendToListClient(p));
    });

	$.get("http://localhost:8080/api/produits",function(resp){
		resp.forEach( l => {
			appendToListProduit(l);
			appendToSelects(l);
		});
	});

	$listeClients.on("click", "li button", function() {
    	let elemid = $(this).parent().attr('id');
    	
    	$.ajax({
		    type: "DELETE",
		    url: "http://localhost:8080/api/clients/" + elemid.replace('client-',''),
		    dataType: "json",
		    success: function(data){
		    	$("#"+elemid).remove();
		    }
		});	
    });

    $listeProduits.on("click", "li button", function() {
        	let elemid = $(this).parent().attr('id');
            let idProduit = elemid.replace('produit-','');

        	$.ajax({
    		    type: "DELETE",
    		    url: "http://localhost:8080/api/produits/" + idProduit,
    		    dataType: "json",
    		    success: function(data){
    		    	$("#"+elemid).remove();
    		    	$(`#selectProduits option[value="${idProduit}"]`).remove();
    		    },
                error: function (xhr, ajaxOptions, thrownError) {
                    console.log(xhr);
                    alert("Impossible de supprimer le produit");
                }
    		});
        });
    
    $('#addbtnClient').click(function(){
		let prenom = $('#prenom-input').val();
        let nom = $('#nom-input').val();
		let age = $('#age-input').val();
		let idProduit = $selectProduits.val();
		
		$.ajax({
		    type: "POST",
		    url: "http://localhost:8080/api/clients",
		    data: JSON.stringify({ "prenom": prenom,"nom": nom, "age" : age }),
		    contentType: "application/json; charset=utf-8",
		    dataType: "json",
		    success: function(data){
		        addProduitToClient(data.id, idProduit);
		    }
		});
		
		$('#prenom-input').val('');
    $('#nom-input').val('');
		$('#age-input').val('');
		return false;
	});

	$('#addbtnProduit').click(function(){
		let produit = {
			nomprod: $('#nomprod-input').val(),
			prix: $('#prix-input').val(),
            numserie: $('#numserie-input').val(),
			createur: {
				nomcreateur: $('#nomcreateur-createur-input').val(),
				reference: $('#reference-createur-input').val(),
        		type: $('#type-createur-input').val()
			}
		};
		
		console.log('ERR',produit)

		$.ajax({
			type: "POST",
			url: "http://localhost:8080/api/produits",
			data: JSON.stringify(produit),
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			success: function(data){
				console.log('ERR',data)
				appendToListProduit(data);
				appendToSelects(data);
			}
		});

		$('#nomprod-input').val('');
		$('#prix-input').val('');
		$('#nomcreateur-createur-input').val('');
		$('#reference-createur-input').val('');
        $('#type-createur-input').val('');
		return false;
	})


	function appendToListProduit(produit) {
		$listeProduits.append(`<li id="produit-${produit.id}" class="list-group-item">${produit.nomprod} - ${produit.prix} - ${produit.createur.nomcreateur} ${produit.createur.reference} ${produit.createur.type}<button class="btn btn-danger btn-xs" data-title="Delete" data-toggle="modal" data-target="#delete" >X</button> </li>`);
	}

	/* Ajoute un produit dans le select du produit*/
	function appendToSelects(produit) {
		$selectProduits.append(`<option value="${produit.id}">${produit.nomprod}</option>`)
	}

	/* Ajoute un client dans la liste des clients*/
	function appendToListClient(client) {
	    let liToAppend = `<li id="client-${client.id}" class="list-group-item">${client.prenom} ${client.nom}${client.age}`;
	    lient.produits.forEach( produit => liToAppend+= ` - ${produit.nomprod}`);
		liToAppend+= ` <button class="btn btn-danger btn-xs" data-title="Delete" data-toggle="modal" data-target="#delete" >X</button></li>`;

		$listeClients.append(liToAppend);
	}

	/* Ajout un produit à un client*/
	function addProduitToClient(idClient, idProduit) {
	    $.ajax({
    			type: "POST",
    			url: "http://localhost:8080/api/clients/"+idClient+"/produits",
    			data: JSON.stringify({"idProduit" : idProduit}),
    			contentType: "application/json; charset=utf-8",
    			dataType: "json",
    			success: function(data){
    				appendToListClient(data);
    			}
    	});
	}
});
