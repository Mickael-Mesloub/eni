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
    const article = articles.find(a => String(a.id) === String(id));

    if (!article) {
        return response.json({error: `Article avec l'id ${id} introuvable`});
    }

    return response.json({
        message: `Voici l'article ${id} !`,
        article
    });
});

router.post('/save-article', (request, response) => {
    const newArticle = request.body;

    // TODO: add more validation
    for(const [key, value] of Object.entries(newArticle)) {
        if(String(value).trim() === "") {
            return response.json({error: `Le champ ${key} est obligatoire`})
        } else if(key === "title" && value.length < 2) {
            return response.json({ error: `Le champ ${key} doit contenir 2 caractères minimum`,})
        }
    }

    let articleFoundIndex = articles.findIndex(a => String(a.id) === String(newArticle.id));

    if (articleFoundIndex === -1) {
        articles.push(newArticle);

        return response.json({
            message: 'Nouvel article ajouté !',
            newArticle,
            articles
        });
    } else {
        articles[articleFoundIndex] = newArticle;

        return response.json({
            message: `Article avec l'id ${newArticle.id} modifié avec succès !`,
            newArticle,
            articles
        })
    }

});

router.delete('/article/:id', (request, response) => {
    const id = request.params.id;
    const articleFound = articles.find(a => String(a.id) === String(id));

    if(!articleFound) {
        return response.json({error: `Article avec l'id ${id} introuvable`});
    }

    articles = articles.filter(a => String(a.id) !== String(id));
    
    return response.json({
        message: `Article avec l'id ${id} supprimé !`,
        id,
        articles
    });
});

module.exports = {
    router
}