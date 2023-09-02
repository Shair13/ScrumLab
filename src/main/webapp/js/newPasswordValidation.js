const formChangePass = document.querySelector('form');
const pass1 = document.querySelector('#pass1');
const pass2 = document.querySelector("#pass2");
const errorMessage = document.querySelector('div#error-message');

formChangePass.addEventListener('submit', onFormSubmission);

function onFormSubmission(event) {

    const errors = [];

    if (pass1.value.length === 0 || pass1.value !== pass2.value) {
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