const express = require("express");
const createUser = require("../shared/utils/user.utils");
const { checkJwtMiddleware } = require("../shared/middlewares/jwt");
const { User } = require("../mongoose/models/User");

const authRouter = express.Router();

authRouter.post("/register", async (request, response) => {
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

authRouter.post("/auth", async (request, response) => {
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

authRouter.get("/my-profile", checkJwtMiddleware, async (request, response) => {
  return response.json({
    code: "CD-220",
    message: "BIENVENUE SUR MON PROFIL !!",
  });
});

module.exports = authRouter;
