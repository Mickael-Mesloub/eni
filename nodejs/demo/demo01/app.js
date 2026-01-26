require('dotenv').config()

// Importer le module express
const express = require('express');

// Instancier un serveur
const app = express();

// Récupérer le host défini dans le .env
const host = process.env.HOST;

// Récupérer le port défini dans le .env
const port =  process.env.PORT;

app.get('/chocolat/:id', async (request, response) => {
    const id = request.params.id;

	return response.json({
        message: 'Vive le chocolat !',
        id
    });
});

// Démarrer le serveur et afficher un message en console
app.listen(port, () => {
	console.log(`App running on ${host}:${port}`);
});