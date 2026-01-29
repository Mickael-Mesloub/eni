const jwt = require("jsonwebtoken");

const { SECRET_KEY } = process.env;

const checkJwtMiddleware = async (request, response, next) => {
    let authorization = request.headers.authorization;

    // Si le token n'est pas envoyé, refuser l'accès
    if(authorization === undefined || authorization === null) {
        return response.json({
            code: 666,
            message: "Pas de token ??! Vous ne passerez pas... ^^!!",
            data: null
        })
    }

   const token = authorization.split(" ")[1]

    try {
        // Vérifier que le token existe, que le SECRET_KEY correspond et qu'il n'est pas expiré
        await jwt.verify(token, SECRET_KEY);
    } catch(e) {
        // Accès refusé
        console.error(e.message);
        return response.json({
            code: 666,
            message: "Token invalide ou expiré !! Vous ne passerez pas... ^^!!!!!!!!!!!!!!",
            data: null
        })
    }

    // Accès autorisé
    return next();
}

module.exports = {checkJwtMiddleware};