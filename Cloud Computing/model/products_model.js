const sequelize = require("sequelize");
const my_db = require("../utils/connect_db");

const Product = my_db.define("products", {
    id: {
        type: sequelize.INTEGER,
        primaryKey: true,
        allowNull: false,
        autoIncrement: true,
    },
    product_name: {
        type: sequelize.STRING,
        allowNull: false,
    },
    price: {
        type: sequelize.INTEGER,
        allowNull: false,
    },
    stock: {
        type: sequelize.INTEGER,
        allowNull: false,
    },
    description: {
        type: sequelize.STRING,
        allowNull: false,
    },
    product_durability: {
        type: sequelize.STRING,
        allowNull: false,
    },
    image: {
        type: sequelize.STRING,
        allowNull: false
    }
});

module.exports = Product;