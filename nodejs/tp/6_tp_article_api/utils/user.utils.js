const { User } = require("../model/User");
const { generateId } = require("./utils");

const createUser = async ({ email, password }) => {
  const users = await User.find();

  return new User({
    id: generateId(users),
    email,
    password
  });
};

module.exports = {
  createUser,
};
