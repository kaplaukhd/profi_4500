const url = 'http://194.67.68.95:8555/v1/api'

export function getArticles() {
    return fetch(url + '/articles', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        }
    }).then(response => response.json())
}

export function newArticle(formData) {
    return fetch(url + '/articles', {
        method: 'POST', headers: {
            'Content-Type': 'multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW',
            'Accept': 'application/json'
        }, body: JSON.stringify({
            title: formData.title,
            body: formData.body,
            file: formData.file
        })
    })
}


export function showNotification(message, duration) {
    const notificationElement = document.getElementById('notification');
    notificationElement.innerText = message;
    notificationElement.style.display = 'block';

    setTimeout(() => {
        notificationElement.style.display = 'none';
    }, duration);
}

export function registration(form) {
    return fetch(url + '/register', {
        method: 'POST', headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        }, body: JSON.stringify( {
            firstName: form.firstName.value,
            lastName: form.lastName.value,
            email: form.email.value,
            password: form.password.value
        })
    })
}

export function info() {
    return fetch(url + '/user/info', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        }
    }).then(response => response.json())
}


export function changePasswordAction(password) {
   return  fetch(url + '/user/password', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }, body: JSON.stringify({
            password: password
        })
    })
}


export function changeUserInfo(name, lastName, email) {
   return fetch(url + '/user/change', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }, body: JSON.stringify({
            firstName: name,
            lastName: lastName,
        })
    })
}