import {getArticles, newArticle, registration, showNotification} from "./export.js";


window.onload = () => {
    loadArticles()
}

const postsContainer = document.getElementById('postsContainer');
const form = document.getElementById('newArticleForm')
const formButton = document.getElementById('newArticleButton')
const close = document.getElementById('closeNewArticle')

function loadArticles() {
    getArticles().then(data =>
        data.forEach(post => {
            const postElement = document.createElement('div');
            postElement.classList.add('post-container');

            const avatarElement = document.createElement('div');
            avatarElement.classList.add('avatar');
            const avatarImg = document.createElement('img');
            avatarImg.src = '/images/' + post.avatarUrl;
            avatarImg.alt = 'User Avatar';
            avatarElement.appendChild(avatarImg);
            postElement.appendChild(avatarElement);

            const postContent = document.createElement('div');
            postContent.classList.add('post-content');

            const postTitle = document.createElement('h3');
            postTitle.classList.add('post-title');
            postTitle.textContent = post.title;
            postContent.appendChild(postTitle);

            const postMeta = document.createElement('p');
            postMeta.classList.add('post-meta');
            postMeta.textContent = `${post.date} • ${post.author}`;
            postContent.appendChild(postMeta);

            const postText = document.createElement('p');
            postText.classList.add('post-text');
            postText.textContent = post.body;
            postContent.appendChild(postText);
            if (post.imageUrl !== "") {
                const postImage = document.createElement('img');
                postImage.classList.add("post-image")
                postImage.src = '/images/' + post.imageUrl;
                postImage.alt = 'Post Image';
                postContent.appendChild(postImage);
            }


            postElement.appendChild(postContent);

            postsContainer.appendChild(postElement);
        }))
}



