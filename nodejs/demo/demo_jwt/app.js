// Importer le module express
const express = require('express');
const jwt = require('jsonwebtoken');

// Instancier un serveur express
const app = express()

app.use(express.json());

app.get('/create-token', async (request, response) => {

    // Créer un token
    const token = jwt.sign({ jsp: "jsp" }, "ma_key", {expiresIn: '2 hours'});

    return response.json({
        code: "CD-220",
        message: "Token généré",
        data: token
    });
})

app.get('/verify-token/:token', async (request, response) => {

    // Récupérer le token envoyé en params
    const token = request.params.token;

    let result = null;

    try {
      result = await jwt.verify(token, "ma_key");

    } catch(e) {
        console.error(e.message);
        return response.json({
            code: "CD-1",
            message: "Erreur token",
            data: null
        });
    }

    return response.json({
        code: "CD-220",
        message: "VERIFICATION DU TOKEN OK",
        data: result
    });
})

// Lancer le server
app.listen(3000, () => {
    console.log("Le serveur a demarré");
});