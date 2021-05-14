$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
{
$("#alertSuccess").hide();
}
$("#alertError").hide();
});

//SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	
	// Form validation-------------------
	var status = validateItemForm();
	if (status != true)
	{
	$("#alertError").text(status);
	$("#alertError").show();
	return;
	}
	
	// If valid------------------------
	var type = ($("#userID").val() == "") ? "POST" : "PUT";
	$.ajax(
	{
	url : "UserAPI",
	type : type,
	data : $("#formItem").serialize(),
	dataType : "text",
	complete : function(response, status)
	{
	onItemSaveComplete(response.responseText, status);
	}
	});
});


//UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
	$("#userID").val($(this).data("itemid"));
	$("#userName").val($(this).closest("tr").find('td:eq(0)').text());
	$("#userType").val($(this).closest("tr").find('td:eq(1)').text());
	$("#Password").val($(this).closest("tr").find('td:eq(2)').text());
	$("#Email").val($(this).closest("tr").find('td:eq(3)').text());
	$("#Phone").val($(this).closest("tr").find('td:eq(4)').text());
	$("#Address").val($(this).closest("tr").find('td:eq(5)').text());
});

//UPDATE==========================================
$(document).on("click", ".btnRemove", function(event)
{
	$.ajax(
	{
		url : "UserAPI",
		type : "DELETE",
		data : "userID=" + $(this).data("itemid"),
		dataType : "text",
		complete : function(response, status)
		{
			onItemDeleteComplete(response.responseText, status);
		}
	});
});


//CLIENT-MODEL================================================================
function validateItemForm()
{
		// CODE
		if ($("#userName").val().trim() == "")
		{
			return "Insert User Name.";
		}
		
		// NAME
		if ($("#userType").val().trim() == "")
		{
			return "Insert userType.";
		}
		
		// PRICE-------------------------------
		if ($("#Password").val().trim() == "")
		{
			return "Insert Password.";
		}
		
		if ($("#Email").val().trim() == "")
		{
			return "Insert Email.";
		}
		
		// DESCRIPTION------------------------
		if ($("#Phone").val().trim() == "")
		{
			return "Insert  Phone.";
		}
		
				
		if ($("#Address").val().trim() == "")
		{
			return "Insert Address.";
		}
		return true;
}



function onItemSaveComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
		} else if (status == "error")
		{
			$("#alertError").text("Error while saving.");
			$("#alertError").show();
		} else
		{
			$("#alertError").text("Unknown error while saving..");
			$("#alertError").show();
	}
		$("#userID").val("");
		$("#formItem")[0].reset();
}



function onItemDeleteComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
		} else if (status == "error")
		{
			$("#alertError").text("Error while deleting.");
			$("#alertError").show();
		} else
		{
			$("#alertError").text("Unknown error while deleting..");
			$("#alertError").show();
	}
}



