const anchors = document.querySelectorAll('a#deleteButton');
const modalBody = document.querySelector('.modal-body');
const modalFooter = document.querySelector('.modal-footer a');
const planId = document.querySelectorAll('#planId');
const planName = document.querySelectorAll('#planName');
const link = modalFooter.getAttribute('href')
anchors.forEach(function (element, index) {
    element.addEventListener('click', function (event) {
        event.preventDefault();
        modalBody.innerText = `Czy na pewno chcesz usunąć ${planName[index].innerText}?`;
        modalFooter.setAttribute('href', link + planId[index].innerText);
    })
})