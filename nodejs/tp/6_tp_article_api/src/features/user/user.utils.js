const { User } = require("../../features/user/user-model");
const { generateId } = require("../../shared/shared.utils");

const createUser = async ({ email, password }) => {
  const users = await User.find();

  return new User({
    id: generateId(users),
    email,
    password,
  });
};

module.exports = createUser;
