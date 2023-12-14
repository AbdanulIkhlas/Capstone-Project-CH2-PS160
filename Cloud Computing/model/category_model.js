const sequelize = require("sequelize");
const my_db = require("../utils/connect_db");

const Category = my_db.define("categories", {
    id: {
        type: sequelize.INTEGER,
        primaryKey: true,
        allowNull: false,
        autoIncrement: true,
    },
    category_name: {
        type: sequelize.STRING,
        allowNull: false,
    },
});

module.exports = Category;