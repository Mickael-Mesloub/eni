require("dotenv").config();
const express = require("express");
const authRouter = require("./features/auth/auth-routes.js");
const baseRouter = require("./shared/base-router.js");
const articleRouter = require("./features/article/article-routes.js");
const userRouter = require("./features/user/user-routes.js");
const connectToDb = require("./mongoose/mongoose-config.js");

const app = express();

app.use(express.json());

const { HOST, PORT } = process.env;

app.use("/", baseRouter);
app.use("/auth", authRouter);
app.use("/articles", articleRouter);
app.use("/user", userRouter);

connectToDb();

app.listen(PORT, () => {
  console.log(`App running on ${HOST}:${PORT}`);
});
