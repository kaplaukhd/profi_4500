import {changePasswordAction, changeUserInfo, info, showNotification} from "./export.js";

window.onload = () => {
    getInfo()
}

const changeButton = document.getElementById('changePasswordButton')
const changeForm = document.getElementById('changePasswordForm')
const changeUserForm = document.getElementById('changeUserForm')
const changeUserButton = document.getElementById('changeUserButton')

function getInfo() {
    let name = document.getElementById('name')
    let email = document.getElementById('email')
    let posts = document.getElementById('posts')
    info().then(
        data => {
            name.innerHTML = 'Имя: ' + data.fullName;
            email.innerHTML = 'Почта: ' + data.email;
            posts.innerHTML = 'Постов: ' + data.countArticles
        }
    )
}


$('.upload ').on('change', function () {
    let file = this.files[0];
    $(this).closest('.input-file').find('.input-file-text').html(file.name.substring(0, 10) + '...');
});

changeButton.addEventListener('click', function (event) {
    event.preventDefault();
    let password = changeForm.password.value;
    let repeat = changeForm.passwordRepeat.value;
    if (password !== repeat) {
        showNotification('Пароли не совпадают', 2000)
    } else {
        changePasswordAction(password).then(response => {
            if (response.ok) {
                showNotification('Пароль изменен', 2000)
            }
        })
    }

})


changeUserButton.addEventListener('click', function (event) {
    event.preventDefault();
    let name = changeUserForm.firstName.value;
    let lastname = changeUserForm.lastName.value;
    changeUserInfo(name, lastname, email).then(response => {
        if (response.ok) {
            showNotification('Данные изменены', 2000)
            getInfo()
        } else {
            showNotification('Данная почта уже занята')
        }
    })
});
