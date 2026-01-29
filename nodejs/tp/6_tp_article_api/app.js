require('dotenv').config()
const express = require('express');
const mongoose = require('mongoose');
const authRouter = require('./routes/auth-routes.js');
const baseRouter = require('./routes/base-router.js');
const articleRouter = require('./routes/article-routes.js');

const app = express();

app.use(express.json());

const {HOST, PORT, MONGO_URI, DB_NAME } = process.env;

app.use("/", baseRouter);
app.use("/", authRouter);
app.use("/", articleRouter);

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