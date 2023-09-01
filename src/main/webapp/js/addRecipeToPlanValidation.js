const form = document.querySelector('form#addForm');
const mealNameInput = document.querySelector('input#name');
const mealNumberInput = document.querySelector('input#number');
const errorMessage = document.querySelector('div#error-message');
const submitButton = document.querySelector('#submitButton')

submitButton.addEventListener('click', onFormSubmission);

function onFormSubmission(event) {
    const errors = [];

    if (mealNameInput.value.length < 3) {
        console.log("za krotka nazwa")
        errors.push("Nazwa posiłku jest za krótka");
    }
    if (!isFinite(parseFloat(mealNumberInput.value))) {
        errors.push("Numer posiłku nie jest liczbą");
    }

    if (errors.length === 0) {
        errorMessage.classList.add("d-none");
        form.submit()
    } else {
        errorMessage.innerText = errors.join(", ");
        errorMessage.classList.remove("d-none");
        event.preventDefault();
    }
}