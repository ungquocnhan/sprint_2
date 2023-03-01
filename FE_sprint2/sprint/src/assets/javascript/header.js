// let dropdown = document.querySelector(".dropdown");
// dropdown.addEventListener("click", function() {
//   this.querySelector(".dropdown-content").classList.toggle("show");
// });
const dropdownElementList = document.querySelectorAll('.dropdown-toggle')
const dropdownList = [...dropdownElementList].map(dropdownToggleEl => new bootstrap.Dropdown(dropdownToggleEl))
