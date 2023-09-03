(function () {
const anchors = document.querySelectorAll('a#deleteButton');
const modalBody = document.querySelector('div#deleteMessageBody');
const modalFooter = document.querySelector('div#deleteMessageFooter a');
const planId = document.querySelectorAll('#planId');
const planName = document.querySelectorAll('#planName');
const link = modalFooter.getAttribute('href')
console.log(modalFooter)
anchors.forEach(function (element, index) {
    element.addEventListener('click', function (event) {
        event.preventDefault();
        console.log(planName[0].innerText)
        modalBody.innerText = `Czy na pewno chcesz usunąć ${planName[index].innerText}?`;
        console.log(link + planId[index].innerText)
        modalFooter.setAttribute('href', link + planId[index].innerText);
    })
})
})();