require('dotenv').config()
const express = require('express');
const { router } = require('./routes/router.js')

const app = express();

app.use(express.json());

const host = process.env.HOST;
const port =  process.env.PORT;

app.use("/", router);

app.listen(port, () => {
    console.log(`App running on ${host}:${port}`);
});