const express = require('express');

const router = express.Router();

router.get('/articles', (request, response) => {
    return response.json({
        message: 'Voici la liste des articles !',
    });
});

router.get('/articles/:id', (request, response) => {
    const id = request.params.id;

    return response.json({
        message: `Voici l'article ${id} !`,
        id
    });
});

router.post('/save-article', (request, response) => {
    return response.json({
        message: 'Nouvel article ajouté !',
    });
});

router.delete('/article/:id', (request, response) => {
    const id = request.params.id;

    return response.json({
        message: `Article avec l'id ${id} supprimé !`,
        id
    });
});

module.exports = {
    router: router
}