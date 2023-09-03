const anchors = document.querySelectorAll('a#deleteButton');
const modalBody = document.querySelector('.modal-body');
const modalFooter = document.querySelector('.modal-footer a');
const recipeId = document.querySelectorAll('#recipeId');
const recipeName = document.querySelectorAll('#recipeName');
const link = modalFooter.getAttribute('href')

anchors.forEach(function (element, index) {
    element.addEventListener('click', function (event) {
        event.preventDefault();
        modalBody.innerText = `Czy na pewno chcesz usunąć ${recipeName[index].innerText}?`;
        modalFooter.setAttribute('href', link + recipeId[index].innerText);
    })
})