const { Article } = require("../../mongoose/models/Article");
const { generateId } = require("./utils");

const createArticle = async ({ title, content, author }) => {
  const articles = await Article.find();

  return new Article({
    id: generateId(articles),
    title,
    content,
    author,
  });
};

module.exports = createArticle;

