const mongoose = require('mongoose');

const Article = mongoose.model('Article', {
    id: Number,
    title : String,
    content: String,
    author: String
}, "articles");

module.exports = {
    Article
}