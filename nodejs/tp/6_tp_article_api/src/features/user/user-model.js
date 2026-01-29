const mongoose = require("mongoose");
const bcrypt = require("bcrypt");
const jwt = require('jsonwebtoken');

const userSchema = new mongoose.Schema({
  id: Number,
  email: String,
  password: String,
});

// Hash password - if it has been modified - before saving user in db
userSchema.pre("save", async function() {
  if (!this.isModified("password")) return;

  const salt = await bcrypt.genSalt(10);
  this.password = await bcrypt.hash(this.password, salt);
});

// Compare password from input with hashed password from db
userSchema.methods.comparePasswords = async function (passwordInput) {
  return bcrypt.compare(passwordInput, this.password);
};


userSchema.methods.createJWT = function() {
  return jwt.sign(
    {
      id: this.id,
      email: this.email,
    },
    process.env.SECRET_KEY,
    { expiresIn: "2 hours" }
  );
};
const User = mongoose.model("User", userSchema, "users");

module.exports = { User };
