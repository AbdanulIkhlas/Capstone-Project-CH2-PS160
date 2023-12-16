const sequelize = require("sequelize");
const my_db = require("../utils/connect_db");



const Product_Detail = my_db.define("product_detail", {
    id: {
        type: sequelize.INTEGER,
        primaryKey: true,
        allowNull: false,
        autoIncrement: true,
    },
    bid_price: {
        type: sequelize.INTEGER,
        allowNull: true,
    },
    count: {
        type: sequelize.INTEGER,
        allowNull: true,
    },
    total_price: {
        type: sequelize.INTEGER,
        allowNull: true,
    },

})

module.exports = Product_Detail