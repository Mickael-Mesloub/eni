const express = require("express");
const { Article } = require("../mongoose/models/Article");
const createArticle = require("../shared/utils/article.utils");
const { checkJwtMiddleware } = require("../shared/middlewares/jwt");

const articleRouter = express.Router();

// get all articles
articleRouter.get("/", async (request, response) => {
  const articles = await Article.find();

  return response.json({
    code: 200,
    message: "Voici la liste des articles !",
    data: articles,
  });
});

// get article by id
articleRouter.get("/:id", async (request, response) => {
  const id = Number(request.params.id);

  if (Number.isNaN(id)) {
    return response.json({
      code: 400,
      message:
        "Erreur : Format d'id incorrect. Il ne doit comporter que des chiffres.",
    });
  }

  const article = await Article.findOne({ id });

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
articleRouter.post(
  "/save-article",
  checkJwtMiddleware,
  async (request, response) => {
    const { id, title, content, author } = request.body;

    try {
      // Fields validation
      if (!title || !content || !author) {
        return response.json({
          code: 710,
          message: "Champs manquants",
        });
      }

      // Check if article already exists with title
      const existingArticle = await Article.findOne({ title, id: { $ne: id } });

      if (existingArticle) {
        return response.json({
          code: 701,
          message: `Un article avec le titre "${title}" existe déjà`,
        });
      }

      // Create article if no id provided
      if (!id) {
        const newArticle = await createArticle({ title, content, author });

        await newArticle.save();

        return response.json({
          code: 200,
          message: "Nouvel article créé avec succès !",
          data: newArticle,
        });
      }

      // Update existing article if id provided
      const articleFoundWithId = await Article.findOne({ id });

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
  },
);

// delete article by id
articleRouter.delete(
  "/:id",
  checkJwtMiddleware,
  async (request, response) => {
    const id = Number(request.params.id);

    if (Number.isNaN(id)) {
      return response.json({
        code: 400,
        message: "Erreur : Id invalide. Il ne doit comporter que des chiffres.",
      });
    }

    const articleFound = await Article.findOne({ id });

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
      data: articleFound,
    });
  },
);

module.exports = articleRouter;
