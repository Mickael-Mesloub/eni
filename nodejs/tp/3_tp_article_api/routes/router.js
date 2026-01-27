const express = require('express');
const { Article } = require('../model/Article');

// Simulation de données en mémoire
// let articles = [
//     { id: 1, title: 'Premier article', content: 'Contenu du premier article', author: 'Isaac' },
//     { id: 2, title: 'Deuxième article', content: 'Contenu du deuxième article', author: 'Sanchez' },
//     { id: 3, title: 'Troisième article', content: 'Contenu du troisième article', author: 'Toto' }
// ];

const router = express.Router();

router.get('/', (request, response) => {
    return response.json({
        message: 'Bienvenue sur le TP Article API !',
    });
});

router.get('/articles', async (request, response) => {
    const articles = await Article.find();

    return response.json({
        message: 'Voici la liste des articles !',
        articles
    });
});

router.get('/articles/:id', async (request, response) => {
    const id = Number(request.params.id);
    const article =  await Article.findOne({id});    

    if (!article) {
        return response.json({error: `Article avec l'id ${id} introuvable`});
    }

    return response.json({
        message: `Voici l'article ${id} !`,
        article
    });
});

router.post('/save-article', async (request, response) => {
    const { title, content, author } = request.body;
    const articles = await Article.find();
    let id;

    if(articles.length === 0) {
        id = 1;
    } else {
        id = Math.max(...articles.map(a => a.id)) + 1;
    }
    

    const newArticle = new Article({
        id,
        title,
        content,
        author
    });

    await newArticle.save();

    return response.json({
        message: `Le nouvel article avec l'id ${newArticle.id} a été ajouté !`,
        newArticle
    });
    

});

router.delete('/article/:id', async (request, response) => {
    const id = Number(request.params.id);
    const articleFound = await Article.findOne({id});

    await articleFound.deleteOne();
    
    return response.json({
        message: `Article avec l'id ${id} supprimé !`,
        articleFound
    });
});

module.exports = {
    router
}