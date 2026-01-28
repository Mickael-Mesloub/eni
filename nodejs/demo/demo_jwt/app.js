// Importer le module express
const express = require('express');
const jwt = require('jsonwebtoken');

// Instancier un serveur express
const app = express()

app.use(express.json());

// Demo find all
app.get('/create-token', async (request, response) => {

    // Créer un token
    const token = jwt.sign({ jsp: "jsp" }, "ma_key", {expiresIn: '2 hours'});

    return response.json({
        code: "CD-220",
        message: "Token généré",
        data: token
    });
})

// Lancer le server
app.listen(3000, () => {
    console.log("Le serveur a demarré");
});