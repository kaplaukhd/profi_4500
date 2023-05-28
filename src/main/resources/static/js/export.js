const url = 'http://localhost:8555/v1/api'
export function getArticles() {
    return fetch(url + '/articles', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        }
    }).then(response => response.json())
}