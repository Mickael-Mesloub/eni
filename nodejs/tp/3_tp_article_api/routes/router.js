const express = require("express");
const { Article } = require("../model/Article");

const router = express.Router();

// get root
router.get("/", (request, response) => {
  return response.json({
    message: "Bienvenue sur le TP Article API !",
  });
});

// get all articles
router.get("/articles", async (request, response) => {
  const articles = await Article.find();

  return response.json({
    status: 200,
    message: "Voici la liste des articles !",
    data: articles,
  });
});

// get article by id
router.get("/articles/:id", async (request, response) => {
  const id = Number(request.params.id);

  if (Number.isNaN(id)) {
    return response.json({
      status: 400,
      message:
        "Erreur : Format d'id incorrect. Il ne doit comporter que des chiffres.",
    });
  }

  const article = await Article.findOne({ id });

  if (!article) {
    return response.json({
      status: 404,
      message: `Erreur : Article avec l'id ${id} introuvable`,
    });
  }

  return response.json({
    status: 200,
    message: `Voici l'article ${id} !`,
    data: article,
  });
});

// create article
router.post("/save-article", async (request, response) => {
  const { title, content, author } = request.body;
  let id;

  // TODO: data validation

  const articles = await Article.find();

// Validation - article already exists with title
  const articleFoundWithTitle = await Article.findOne({ title });

  if (articleFoundWithTitle) {
    return response.json({
      status: 701,
      message: `Erreur : Un article avec le titre "${title}" existe déjà`,
    });
  }

  // ------------------------------------------ \\

  if (articles.length === 0) {
    id = 1;
  } else {
    id = Math.max(...articles.map((a) => a.id)) + 1;
  }

  const newArticle = new Article({
    id,
    title,
    content,
    author,
  });

  await newArticle.save();

  return response.json({
    status: 201,
    message: `Le nouvel article avec l'id ${newArticle.id} a été ajouté !`,
    data: newArticle,
  });
});

// delete article by id
router.delete("/article/:id", async (request, response) => {
  const id = Number(request.params.id);

  if (Number.isNaN(id)) {
    return response.json({
      status: 400,
      message:
        "Erreur : Format d'id incorrect. Il ne doit comporter que des chiffres.",
    });
  }

  const articleFound = await Article.findOne({ id });

  if (!articleFound) {
    return response.json({
      status: 404,
      message: `Erreur : Article avec l'id ${id} introuvable`,
    });
  }

  await articleFound.deleteOne();

  return response.json({
    status: 200,
    message: `Article avec l'id ${id} supprimé !`,
  });
});

module.exports = {
  router,
};
