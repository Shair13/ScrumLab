const form = document.querySelector('form');
const emailInput = document.querySelector('input#email');
const nameInput = document.querySelector("input#name");
const surnameInput = document.querySelector('input#surname');
const passwordInput = document.querySelector('input#password');
const repeatPasswordInput = document.querySelector('input#repassword');
const errorMessage = document.querySelector('div#error-message');


form.addEventListener('submit', onFormSubmission);

function onFormSubmission(event) {

    const errors = [];

    if (!emailInput.value.includes('@')) {
        errors.push('Email musi posiadać znak @');
    }
    if (nameInput.value.length <= 2) {
        errors.push("Twoje imię jest za krótkie");
    }
    if (surnameInput.value.length <= 2){
        errors.push("Twoje nazwisko jest za krótkie");
    }
    if (passwordInput.value.length===0 || passwordInput.value!==repeatPasswordInput.value){
        errors.push("Hasła nie są takie same lub puste");

    }

    if (errors.length === 0) {
        errorMessage.classList.add("d-none");
    } else {
        errorMessage.innerText = errors.join(", ");
        errorMessage.classList.remove("d-none");
        event.preventDefault();
    }
}