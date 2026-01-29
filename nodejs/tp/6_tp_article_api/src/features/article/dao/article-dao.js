class ArticleDAO {
    async findAllArticles(){};
    async findArticleById(id){};
    async findArticleByTitle(title){};
    async findArticleByIdAndTitle(id, title){};
    async saveArticle(){};
    async deleteArticleById(){};
}

module.exports = ArticleDAO;