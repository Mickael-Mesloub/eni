const jwt = require("jsonwebtoken");

const { SECRET_KEY } = process.env;

const checkJwtMiddleware = async (request, response, next) => {
    const token = request.headers.authorization.split(" ")[1];

    // Si le token n'est pas envoyé, refuser l'accès
    if(token === undefined || token === null) {
        return response.json({message: "Vous ne passerez pas... ^^!!!!!!!!!!!!!!"})
    }

    try {
        // Vérifier que le token existe, que le SECRET_KEY correspond et qu'il n'est pas expiré
        await jwt.verify(token, SECRET_KEY);
    } catch(e) {
        // Accès refusé
        console.error(e.message);
        return response.json({message: "Vous ne passerez pas... ^^!!!!!!!!!!!!!!"})
    }

    // Accès autorisé
    return next();
}

module.exports = {checkJwtMiddleware};