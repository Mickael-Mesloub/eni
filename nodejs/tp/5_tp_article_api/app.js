require('dotenv').config()
const express = require('express');
const { router } = require('./routes/router.js');
const mongoose = require('mongoose');

const app = express();

app.use(express.json());

const {HOST, PORT, MONGO_URI, DB_NAME } = process.env;

app.use("/", router);

mongoose.connect((`${MONGO_URI}/${DB_NAME}`));

mongoose.connection.once('open', () => {
    console.log("MongoDB Connected!");
})

mongoose.connection.on('error', () => {
    console.log("MongoDB connection error!");
})

app.listen(PORT, () => {
    console.log(`App running on ${HOST}:${PORT}`);
});