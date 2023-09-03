const anchors = document.querySelectorAll('a#deleteButton');
const modalBody = document.querySelector('.modal-body');
const modalFooter = document.querySelector('.modal-footer a');
const recipePlanId = document.querySelectorAll('#recipePlanId');
const mealName = document.querySelectorAll('#mealName');
const link = modalFooter.getAttribute('href')
const planId = document.getElementById('planId');
anchors.forEach(function (element, index) {
    element.addEventListener('click', function (event) {
        event.preventDefault();
        modalBody.innerText = `Czy na pewno chcesz usunąć ${mealName[index].innerText}?`;
        modalFooter.setAttribute('href', link + recipePlanId[index].innerText+"&planId="+planId.innerText);
    })
})