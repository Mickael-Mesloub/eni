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

// Importer mongoose
const mongoose = require('mongoose');

// Definir le model Film lié à la BDD
// 1er param = pas pour nous un autre cours
// 2eme param = les champs pour nous métier
// 3eme param = nom de la collection/table
const Movie = mongoose.model('Movie', {
    title : String
}, "movies");

app.get('/save-movie', async (request, response) => {
    let movieToSave = new Movie();

    // findById pour faire une mise à jour, et ne pas oublier await
    // movieToSave = await Movie.findById(movieToSave?.id);

    movieToSave.title = "Test de film";

    // S'il n'y a pas d'id, qu'il est null ou inexistant,
    // mongoose va créer le document avec un id généré automatiquement
    await movieToSave.save();

    return response.json({
        message: 'Movie added successfully',
        movieToSave,
    });
})

mongoose.connect(("mongodb://127.0.0.1:27017/db_demo"));

mongoose.connection.once('open', () => {
    console.log("MongoDB Connected!");
})

mongoose.connection.on('error', () => {
    console.log("MongoDB connection error!");
})

app.get('/movies', async(request, response) => {
    const movies = await Movie.find();

    return response.json({
        message: 'Movies found!',
        movies
    })
})

// Démarrer le serveur et afficher un message en console
app.listen(port, () => {
	console.log(`App running on ${host}:${port}`);
});