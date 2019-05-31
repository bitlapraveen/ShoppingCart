document.addEventListener('init', function(event) {
	var page = event.target;

	if (page.id === 'home1') {
		page.querySelector('#push-button').onclick = function() {
			gotoCheckOutPage();
		};
	} else if (page.id === 'home2') {
		page.querySelector('ons-toolbar .center').innerHTML = page.data.title;
	}
});

var oranges = 0;
var apples = 0;

function gotoCheckOutPage() {
	var cartCount = $('#cartCount').text();
	if (cartCount > 0) {
		document.querySelector('#myNavigator').pushPage('home2.html', {
			data : {
				title : 'View Bag & Check Out Here'
			}
		});
		applyDiscounts();
	} else {
		 ons.notification.alert('Please add items to cart before move to Checkout Page.');
	}
}
function increaseCount(itemType) {

	var orangeCount = parseInt($('#orangeItemCount').text());
	var applesCount = parseInt($('#appleItemCount').text());
	if (itemType === 'ORANGE') {
		oranges = ++orangeCount;
		var  orangesText = ' ' + oranges + ' ';
		$('#orangeItemCount').text(orangesText);
	}
	if (itemType === 'APPLE') {
		var  appleText = ' '+apples+' ';
		$('#appleItemCount').text(appleText);
	}
	cartCountUpdate(apples, oranges);
}
function decreaseCount(itemType) {
	var orangeCount = parseInt($('#orangeItemCount').text());
	var applesCount = parseInt($('#appleItemCount').text());
	if (itemType === 'ORANGE') {
		if (orangeCount > 0) {
			oranges = --orangeCount;
			var  orangesText = ' ' + oranges + ' ';
			$('#orangeItemCount').text(orangesText);
		}
	}
	if (itemType === 'APPLE') {
		if (applesCount > 0) {
			apples = --applesCount;
			var  appleText = ' '+apples+' ';
			$('#appleItemCount').text(appleText);
		}
	}
	cartCountUpdate(apples, oranges);
}

function cartCountUpdate(apples, oranges) {
	var cartCount = apples + oranges;
	$('#cartCount').text(cartCount);
}

var showTemplateDialog = function() {
	  var dialog = document.getElementById('my-dialog');

	  if (dialog) {
	    dialog.show();
	  } else {
	    ons.createElement('dialog.html', { append: true })
	      .then(function(dialog) {
	        dialog.show();
	      });
	  }
};

var hideDialog = function(id) {
	  document.getElementById(id).hide();
	  $('#appleItemCount').text('0');
	  $('#orangeItemCount').text('0');
	  $('#cartCount').text('0');
	  $('#pageBackButton').click();
};
	