function demo() {
	alert("Hiiiii");
}

$(document).ready(function() {
	$('.js-delete').on('click', function() {
		var row = $(this).closest('tr');
		var buttonId = $(this).attr("id");

		$.ajax({
	        type: "DELETE",
	        contentType: "text/plain",
	        url: "/certificate_request/delete",
	        data: buttonId,
	        success: function (data) {
	        	alert('Request successfully rejected');
	        	row.remove();
	        },
	        error: function (e) {
	        	alert('Error has occured');
	        }
	    });
	});
	
	$('.js-approve').on('click', function() {
		var row = $(this).closest('tr');
		var uid = row.find('#uid').text();
		var email = row.find('#email').text();
		var buttonId = $(this).attr("id");

		$.ajax({
	        type: "POST",
	        contentType: "application/json",
	        url: "/certificate",
	        data: JSON.stringify({ id: uid, email: email}),
	        success: function (data) {
	        	alert('Request successfully accepted');
	        	row.remove();
	        },
	        error: function (e) {
	        	row.remove();
	        }
	    });
	});
	
	$('.js-make-cert-request').on('click', function(event) {
		event.preventDefault();
		const $button = $(this);
		const commonName = $('#common_name').val();
		const country = $('#country').val();
		const email = $('#email').val();
		const givenName = $('#givenName').val();
		const organization = $('#organization').val();
		const organization_unit = $('#organization_unit').val();
		const surname = $('#surname').val();
		
	    $.ajax({
	        type: "POST",
	        contentType: "application/json",
	        url: "/certificate_request/send_request", //create certification request
	        data: JSON.stringify({
	        	commonName: commonName,
	        	country: country,
	        	email: email,
	        	givenName: givenName,
	        	organization: organization,
	        	organizationUnit: organization_unit,
	        	surname: surname,
	        	uid: $('#uid').val()
	        }),
	        success: function (data) {
	        	alert("successfully created request");
	        	$button.closest('form').find("input[type=text], input[type=email]").val("");
	        },
	        error: function (e) {
	        	alert('The error has occured');
	        }
	    });
		
	});

	$('.js-revoke').on('click', function() {
		var row = $(this).closest('tr');
		var subjectAlias = row.find('#email').text();

		$.ajax({
			type: "PUT",
			contentType: "application/json",
			url: "/certificate",
			data: JSON.stringify({ subjectAlias: subjectAlias, revocationReason: ""}),
			success: function (data) {
				alert('Request successfully revoked');
			},
			error: function (e) {
				alert('Error has occured');
			}
		});
	});
});
