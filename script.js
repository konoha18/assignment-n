const form = document.getElementById('form');
const email = document.getElementById('email');



form.addEventListener('submit', e => {
	e.preventDefault();

	const fullName = document.getElementById('fullName').value;
	const mobile = document.getElementById('mobile').value;

	console.log(fullName);
	console.log(mobile);
	
	
	checkInputs();	
});

function checkInputs() {
	// trim to remove the whitespaces
	const nameValue = fullName.value.trim();
	const emailValue = email.value.trim();
	const mobileValue = mobile.value.trim();

	if(nameValue === '') {
		setErrorFor(fullName, 'Name cannot be blank');
	}else if(!isName(nameValue)){
        setErrorFor(fullName, 'Use only alphabets')
    }
    // else if(!isTwoWords(nameValue)){
    //     setErrorFor(fullName, 'Minimum two words')
    // }
    else {
		setSuccessFor(fullName);
	}
	
	if(emailValue === '') {
		setErrorFor(email, 'Email cannot be blank');
	} else if (!isEmail(emailValue)) {
		setErrorFor(email, 'Not a valid email');
	} else {
		setSuccessFor(email);
		
	}
	
	if(mobileValue === '') {
		setErrorFor(mobile, 'Number cannot be blank');
	}
    else if(!isNum(mobileValue)){
        setErrorFor(mobile,'Use 10 digit numbers only')
    }
    // else if(!isNumber(mobileValue)){
    //     setErrorFor(mobile,'Only use numbers');
    // }
     else {
		setSuccessFor(mobile);
	}
}

function setErrorFor(input, message) {
	const formControl = input.parentElement;
	const small = formControl.querySelector('small');
	formControl.className = 'form-control error';
	small.innerText = message;
}

function setSuccessFor(input) {
	const formControl = input.parentElement;
	formControl.className = 'form-control success';
}
	
function isEmail(email) {
	return /[^@]+@[^@]+\.[a-zA-Z]{2,6}/.test(email);
}

function isName(fullName){
    return /[a-zA-Z][a-zA-Z ]+[a-zA-Z]$/.test(fullName);
}

// function isTwoWords(fullName){
//     return /^([a-zA-Z.\\s]{4,})$/.test(fullName);
// }
// function isNumber(mobile){
//     return /^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$/.test(mobile);
// }
function isNum(mobile){
    return /^\d{10}$/.test(mobile);
}

function handleSubmit(){
	
	const fullName = document.getElementById('fullName').value;
	const mobile = document.getElementById('mobile').value;

	localStorage.setItem("NAME",fullName);
	localStorage.setItem("MOBILE",mobile);

	// checkInputs();
	window.location.replace("success.html")

	return;
}