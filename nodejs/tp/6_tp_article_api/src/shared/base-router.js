const express = require("express");
const baseRouter = express.Router();

// get root
baseRouter.get("/", (request, response) => {
  return response.json({
    code: 200,
    message: "Bienvenue sur le TP Article API !",
  });
});

module.exports = baseRouter;
