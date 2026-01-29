require('dotenv').config()
const express = require('express');
const authRouter = require('./routes/auth-routes.js');
const baseRouter = require('./routes/base-router.js');
const articleRouter = require('./routes/article-routes.js');
const connectToDb = require('./mongoose/mongoose-config.js');
const userRouter = require('./routes/user-routes.js');

const app = express();

app.use(express.json());

const {HOST, PORT } = process.env;

app.use("/", baseRouter);
app.use("/auth", authRouter);
app.use("/articles", articleRouter);
app.use("/user", userRouter);

connectToDb();

app.listen(PORT, () => {
    console.log(`App running on ${HOST}:${PORT}`);
});