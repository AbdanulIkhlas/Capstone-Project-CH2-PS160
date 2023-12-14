const sequelize = require("sequelize");
const my_db = require("../utils/connect_db");

const Transaction_Data = my_db.define("transaction_data", {
    id: {
        type: sequelize.INTEGER,
        primaryKey: true,
        allowNull: false,
        autoIncrement: true,
    },
    payment_proof: {
        type: sequelize.STRING,
        allowNull: false
    },
    status: {
        type: sequelize.STRING,
        allowNull: false
    }

});

module.exports = Transaction_Data;