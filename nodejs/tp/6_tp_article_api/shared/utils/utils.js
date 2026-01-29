const generateId = (array) => {
    return array.length === 0 ? 1 : Math.max(...array.map((a) => a.id)) + 1;
}

module.exports = {
    generateId
}