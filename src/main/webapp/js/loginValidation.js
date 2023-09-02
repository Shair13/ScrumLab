const formLogin = document.querySelector('form');
const email = document.querySelector('#email');
const password = document.querySelector("#password");
const errorMessage = document.querySelector('div#error-message');

formLogin.addEventListener('submit', onFormSubmission);

function onFormSubmission(event) {

    const errors = [];

    // if (!email.value.includes('@')) {
    //     errors.push('Email musi posiadać znak @');
    // }
    if (password.value === ""){
        errors.push("Podaj hasło");
    }

    if (errors.length === 0) {
        errorMessage.classList.add("d-none");
    } else {
        errorMessage.innerText = errors.join(", ");
        errorMessage.classList.remove("d-none");
        event.preventDefault();
    }
}