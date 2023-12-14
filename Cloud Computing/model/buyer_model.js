const sequelize = require("sequelize");
const my_db = require("../utils/connect_db");

const Buyer = my_db.define("buyers", {
    id: {
        type: sequelize.INTEGER,
        primaryKey: true,
        allowNull: false,
        autoIncrement: true,
    },
    email: {
        type: sequelize.STRING,
        allowNull: false,
    },
    password: {
        type: sequelize.STRING,
        allowNull: false,
    },
    name: {
        type: sequelize.STRING,
        allowNull: false,
    },
    alamat: {
        type: sequelize.STRING,
        allowNull: false,
    },
    kordinat: {
        type: sequelize.STRING,
        allowNull: false,
    },
    no_hp: {
        type: sequelize.STRING,
        allowNull: false,
    },
    image : {
        type: sequelize.STRING,
        allowNull : true,
    }

});

module.exports = Buyer;