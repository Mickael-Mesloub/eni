const express = require("express");
const {Article} = require("../model/Article");
const {createArticle, updateArticle} = require("../utils/article.utils");

const router = express.Router();

// get root
router.get("/", (request, response) => {
    return response.json({
        code: 200,
        message: "Bienvenue sur le TP Article API !",
    });
});

// get all articles
router.get("/articles", async (request, response) => {
    const articles = await Article.find();

    return response.json({
        code: 200,
        message: "Voici la liste des articles !",
        data: articles,
    });
});

// get article by id
router.get("/articles/:id", async (request, response) => {
    const id = Number(request.params.id);

    if (Number.isNaN(id)) {
        return response.json({
            code: 400,
            message:
                "Erreur : Format d'id incorrect. Il ne doit comporter que des chiffres.",
        });
    }

    const article = await Article.findOne({id});

    if (!article) {
        return response.json({
            code: 702,
            message: `Erreur : Impossible de récupérer un article avec l'id ${id}`,
        });
    }

    return response.json({
        code: 200,
        message: `Voici l'article ${id} !`,
        data: article,
    });
});

// Save article => Create or update article 
router.post("/save-article", async (request, response) => {
    const {id, title, content, author} = request.body;

    try {
        // Fields validation
        if (!title || !content || !author) {
            return response.json({
                code: 710,
                message: "Champs manquants",
            });
        }

        // Check if article already exists with title
        const existingArticle = await Article.findOne({title});

        if (existingArticle && existingArticle.id !== id) {
            return response.json({
                code: 701,
                message: `Un article avec le titre "${title}" existe déjà`,
            });
        }

        // Create article if no id provided
        if (!id) {
            const newArticle = await createArticle({title, content, author});

            await newArticle.save();

            return response.json({
                code: 200,
                message: "Nouvel article créé avec succès !",
                data: newArticle,
            });
        }

        // Update existing article if id provided
        const articleFoundWithId = await Article.findOne({id});

        if (!articleFoundWithId) {
            return response.json({
                code: 702,
                message: `Erreur : Article avec id ${id} introuvable`,
            });
        }

        articleFoundWithId.title = title;
        articleFoundWithId.content = content;
        articleFoundWithId.author = author;

        await articleFoundWithId.save();

        return response.json({
            code: 200,
            message: `Article avec l'id ${id} mis à jour avec succès`,
            data: articleFoundWithId,
        });
    } catch (error) {
        console.error("Erreur save-article :", error.message);

        return response.json({
            code: 500,
            message: "Erreur interne du serveur",
        });
    }
});

// delete article by id
router.delete("/article/:id", async (request, response) => {
    const id = Number(request.params.id);

    if (Number.isNaN(id)) {
        return response.json({
            code: 400,
            message: "Erreur : Id invalide. Il ne doit comporter que des chiffres.",
        });
    }

    const articleFound = await Article.findOne({id});

    if (!articleFound) {
        return response.json({
            code: 404,
            message: `Erreur : Article avec l'id ${id} introuvable`,
        });
    }

    await articleFound.deleteOne();

    return response.json({
        code: 200,
        message: `Article avec l'id ${id} supprimé !`,
    });
});

module.exports = {
    router,
};
