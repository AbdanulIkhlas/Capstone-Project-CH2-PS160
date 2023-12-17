const sequelize = require("sequelize");
const my_db = require("../utils/connect_db");

const Cart = my_db.define("carts", {
    id: {
        type: sequelize.INTEGER,
        primaryKey: true,
        allowNull: false,
        autoIncrement: true,
    },

})

module.exports = Cart;