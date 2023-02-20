let imgFeature = document.querySelector('.img-feature')
let listImg = document.querySelector('.list-image img')
let prevBtn = document.querySelector('.prev')
let nextBtn = document.querySelector('.next')

listImg.forEach(imgElement => {
    imgElement.addEventListener('click', e => {
        imgFeature.src = e.target.getAttribute('src')
    })
})
