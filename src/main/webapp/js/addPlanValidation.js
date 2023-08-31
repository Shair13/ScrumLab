const form = document.querySelector('form');
const planNameInput = document.querySelector('#planName');
const planDescriptionTextera = document.querySelector("#planDescription");
const errorMessage = document.querySelector('div#error-message');

form.addEventListener('submit', onFormSubmission);

function onFormSubmission(event) {

    const errors = [];

    if (planNameInput.value.length < 4) {
        errors.push("Nazwa planu jest za krótka");
    }
    if (planDescriptionTextera.value.length < 10){
        errors.push("Opis planu jest za krótki");
    }

    if (errors.length === 0) {
        errorMessage.classList.add("d-none");
    } else {
        errorMessage.innerText = errors.join(", ");
        errorMessage.classList.remove("d-none");
        event.preventDefault();
    }
}