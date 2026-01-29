require("dotenv").config();
const mongoose = require("mongoose");

const { MONGO_URI, DB_NAME } = process.env;

const connectToDb = () => {
  mongoose.connect(`${MONGO_URI}/${DB_NAME}`);

  mongoose.connection.once("open", () => {
    console.log("MongoDB Connected!");
  });

  mongoose.connection.on("error", () => {
    console.log("MongoDB connection error!");
  });
};

module.exports = connectToDb;