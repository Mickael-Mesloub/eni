const { logger } = require('./logger');

module.exports = {

    buildService : (code, message, data = null) => {
        logger.info(`Code : ${code} - Message : ${message}`);

        return {
            code: code,
            message: message,
            data: data
        };
    }
}