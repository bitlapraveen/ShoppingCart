var baseAppUrl = window.location.origin;

function applyDiscounts() {
	var orangeCount = parseInt($('#orangeItemCount').text().trim());
	var applesCount = parseInt($('#appleItemCount').text().trim());
	
	var data=[{"itemType":"ORANGE" ,"itemCount": orangeCount},
				{"itemType":"APPLE" ,"itemCount": applesCount}];
	$.ajax({
		type: "POST",
		url : baseAppUrl+"/addItems",
		data: JSON.stringify(data),
		dataType: "text",
		contentType: 'application/json',
	}).then(function(data) {
		
		data = JSON.parse(data);
		var data1 = eval(data.checkoutItems);
		
		var infiniteList = document.getElementById('infinite-list');
		infiniteList.delegate = {
				
			createItemContent : function(i) {
				
				var itemName = data1[i].itemName;
				var totalItemsAdded = data1[i].totalItems;
				var itemsBought = data1[i].caclulatedBoughtItems;
				var freeItems = data1[i].calculateFreeItems;
				var itemPrice = parseFloat(data1[i].totalPrice);
				var itemDiscount = data1[i].itemDiscountDescription;
				
			return ons.createElement('<ons-list-item>'+
				    '<ons-row>'+
					  '<ons-col>'+
							'<ons-row>'+
								'<ons-col>'+
									'<p style="margin:0px;">Item Name: &nbsp;&nbsp;</p>'+
								'</ons-col>'+
								'<ons-col>'+
									'<p style="margin:0px;">'+ itemName+'</p>'+
								'</ons-col>'+
							'</ons-row>'+
							'<ons-row>'+
								'<ons-col>'+
									'<p style="margin:0px;">Item Added: &nbsp;&nbsp;</p>'+
								'</ons-col>'+
								'<ons-col>'+
									'<p style="margin:0px;">'+ totalItemsAdded+'</p>'+
								'</ons-col>'+
							'</ons-row>'+
							
					  '</ons-col>'+
					  '<ons-col>'+
						'<div>'+
							'<ons-row style="color:red;">'+
								'<p>'+ itemDiscount+'</p>'+
							'</ons-row>'+
							'<ons-row >'+
								'<ons-col>'+
									'<p style="margin:0px;">Bought Items: &nbsp;&nbsp;</p>'+
								'</ons-col>'+
								'<ons-col>'+
									'<p style="margin:0px;">'+ itemsBought+'</p>'+
								'</ons-col>'+
							'</ons-row>'+
							'<ons-row >'+
								'<ons-col>'+
									'<p style="margin:0px;">Free Items: &nbsp;&nbsp;</p>'+
								'</ons-col>'+
								'<ons-col>'+
									'<p style="margin:0px;">'+ freeItems+'</p>'+
								'</ons-col>'+
							'</ons-row>'+
							'<ons-row >'+
								'<ons-col>'+
									'<p style="margin:0px;">Items Price: &nbsp;&nbsp;</p>'+
								'</ons-col>'+
								'<ons-col>'+
									'<p style="margin:0px;"> $'+ itemPrice+'</p>'+
								'</ons-col>'+
							'</ons-row>'+
							'</div>'+
						'</ons-col>'+
					'</ons-row>'+
				  '</ons-list-item>');
			},
			countItems : function() {
				return data1.length; 
			},
		}; 
		
		var totalPrice = data.checkoutPrice ;
		$('#totalPrice').text(totalPrice);

	});
}
