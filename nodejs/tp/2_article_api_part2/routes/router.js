const express = require('express');

// Simulation de données en mémoire
let articles = [
    { id: 1, title: 'Premier article', content: 'Contenu du premier article', author: 'Isaac' },
    { id: 2, title: 'Deuxième article', content: 'Contenu du deuxième article', author: 'Sanchez' },
    { id: 3, title: 'Troisième article', content: 'Contenu du troisième article', author: 'Toto' }
];

const router = express.Router();

router.get('/articles', (request, response) => {
    return response.json({
        message: 'Voici la liste des articles !',
        articles
    });
});

router.get('/articles/:id', (request, response) => {
    const id = request.params.id;
    const article = articles.find(a => a.id == id);

    console.log(article);
    

    return response.json({
        message: `Voici l'article ${id} !`,
        article
    });
});

router.post('/save-article', (request, response) => {
    const newArticle = request.body;

    articles.push(newArticle);

    return response.json({
        message: 'Nouvel article ajouté !',
        newArticle,
        articles
    });
});

router.delete('/article/:id', (request, response) => {
    const id = request.params.id;
    articles = articles.filter(a => a.id != id);
    
    return response.json({
        message: `Article avec l'id ${id} supprimé !`,
        id,
        articles
    });
});

module.exports = {
    router: router
}