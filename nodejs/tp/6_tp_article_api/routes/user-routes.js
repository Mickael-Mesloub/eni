const express = require("express");
const { checkJwtMiddleware } = require("../shared/middlewares/jwt");

const userRouter = express.Router();

userRouter.get("/my-profile", checkJwtMiddleware, async (request, response) => {
  return response.json({
    code: "CD-220",
    message: "BIENVENUE SUR MON PROFIL !!",
  });
});

module.exports = userRouter;