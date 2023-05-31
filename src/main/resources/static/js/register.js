import {registration, showNotification} from "./export.js";

const registerForm = document.getElementById('formRegister');
const registerBtn = document.getElementById('registerBtn');

registerBtn.addEventListener('click', function (event) {
    event.preventDefault();
    let pass1 = registerForm.password.value;
    let pass2 = registerForm.passwordRepeat.value;
    if (pass1 === pass2) {
        registration(registerForm)
            .then(response => {
                if (response.ok) {
                    window.location.href = "/";
                }
            })
    } else {
        showNotification('Пароли не совпадают', 2000);
    }
})