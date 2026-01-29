const express = require("express");
const { Article } = require("../model/Article");
const { createArticle } = require("../utils/article.utils");
const { User } = require("../model/User");
const { createUser } = require("../utils/user.utils");
const { checkJwtMiddleware } = require("../middleware/jwt");

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
router.post("/save-article", checkJwtMiddleware, async (request, response) => {
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
});

// delete article by id
router.delete("/article/:id", checkJwtMiddleware, async (request, response) => {
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
    data: articleFound
  });
});

router.post("/register", async (request, response) => {
  const { email, password } = request.body;

  // Fields validation
  if (!email || !password) {
    return response.json({
      code: 787,
      message: "L'email et le mot de passe sont obligatoires",
    });
  }

  const userExistsWithEmail = await User.findOne({ email });

  if (userExistsWithEmail) {
    return response.json({
      code: 799,
      message: `Un utilisateur avec l'email "${email}" existe déjà`,
    });
  }

  const newUser = await createUser({ email, password });

  await newUser.save();

  return response.json({
    code: 290,
    message: "Votre compte a été créé avec succès !",
    data: {
      id: newUser.id,
      email: newUser.email,
    },
  });
});

router.post("/auth", async (request, response) => {
  const { email, password } = request.body;

  // Fields validation
  if (!email || !password) {
    return response.json({
      code: 787,
      message: "L'email et le mot de passe sont obligatoires",
    });
  }

  try {
    // Check if user exists in db with email provided
    const userFound = await User.findOne({ email });

    // If user not found, error
    if (!userFound) {
      return response.json({
        code: 297,
        message: `Email ou mot de passe incorrect`,
        data: null,
      });
    }

    // Check if password provided match with hashed password in db
    const passwordsMatch = await userFound.comparePasswords(password);

    // Error if passwords don't match
    if (!passwordsMatch) {
      return response.json({
        code: 297,
        message: `Email ou mot de passe incorrect`,
        data: null,
      });
    }

    // Generate jwt token with user id and email
    const token = userFound.createJWT();

    // Success! User is logged in!
    return response.json({
      code: 202,
      message: "Authentifié(e) avec succès !",
      data: token,
    });
  } catch (e) {
    console.error(e);

    return response.json({
      code: 298,
      message: "Erreur de login",
      data: null,
    });
  }
});

router.get("/my-profile", checkJwtMiddleware, async (request, response) => {
  return response.json({
    code: "CD-220",
    message: "BIENVENUE SUR MON PROFIL !!",
  });
});

module.exports = {
  router,
};
