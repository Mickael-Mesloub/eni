require('dotenv').config()

const express = require('express');

const app = express();

const host = process.env.HOST;
const port =  process.env.PORT;

app.get('/articles', (request, response) => {
    return response.json({
        message: 'Voici la liste des articles !',
    });
});

app.get('/articles/:id', (request, response) => {
    const id = request.params.id;

    return response.json({
        message: `Voici l'article ${id} !`,
        id
    });
});

app.post('/save-article', (request, response) => {
    return response.json({
        message: 'Nouvel article ajouté !',
    });
});

app.delete('/article/:id', (request, response) => {
    const id = request.params.id;

    return response.json({
        message: `Article avec l'id ${id} supprimé !`,
        id
    });
});

app.listen(port, () => {
    console.log(`App running on ${host}:${port}`);
});