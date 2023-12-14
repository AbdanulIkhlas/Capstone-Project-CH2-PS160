const sequelize = require("sequelize");
const my_db = require("../utils/connect_db");

const Transaction = my_db.define("transactions", {
    id: {
        type: sequelize.INTEGER,
        primaryKey: true,
        allowNull: false,
        autoIncrement: true,
    },
    count: {
        type: sequelize.STRING,
        allowNull: false,
    },
    total_price: {
        type: sequelize.INTEGER,
        allowNull: false,
    },
});

module.exports = Transaction;