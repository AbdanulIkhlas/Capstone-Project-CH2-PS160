const sequelize = require("sequelize");
const my_db = require("../utils/connect_db");

const Testimoni = my_db.define("testimonies", {
    id: {
        type: sequelize.INTEGER,
        primaryKey: true,
        allowNull: false,
        autoIncrement: true,
    },
    rating: {
        type: sequelize.FLOAT,
        allowNull: false,
    },
    description: {
        type: sequelize.STRING,
        allowNull: false,
    }
});

module.exports = Testimoni;