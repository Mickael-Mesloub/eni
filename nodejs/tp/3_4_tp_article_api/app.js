require('dotenv').config()
const express = require('express');
const { router } = require('./routes/router.js');
const mongoose = require('mongoose');

const app = express();

app.use(express.json());

const {HOST, PORT, MONGO_URI, DB_NAME } = process.env;

app.use("/", router);

mongoose.connect((`mongodb://127.0.0.1:27017/db_article`));

mongoose.connection.once('open', () => {
    console.log("MongoDB Connected!");
})

mongoose.connection.on('error', () => {
    console.log("MongoDB connection error!");
})

app.listen(3000, () => {
    console.log(`App running on http://localhost:3000`);
});