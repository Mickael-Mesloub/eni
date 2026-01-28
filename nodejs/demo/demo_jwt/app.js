// Importer le module express
const express = require('express');
const jwt = require('jsonwebtoken');
const {checkJwtMiddleware} = require("./middleware/jwt");

// Instancier un serveur express
const app = express()

app.use(express.json());

const SECRET = "ma_key";

app.get('/create-token', async (request, response) => {

    // Créer un token
    const token = jwt.sign({ jsp: "jsp" }, SECRET, {expiresIn: '2 hours'});

    return response.json({
        code: "CD-220",
        message: "Token généré",
        data: token
    });
})

// Appel du middleware avant d'accéder à /my-profile
app.get('/my-profile', checkJwtMiddleware, async (request, response) => {

    return response.json({
        code: "CD-220",
        message: "BIENVENUE SUR MON PROFIL !!",
    });
})

// Lancer le server
app.listen(3000, () => {
    console.log("Le serveur a demarré");
});