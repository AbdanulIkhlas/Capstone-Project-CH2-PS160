require("dotenv").config();
const Sequelize = require("sequelize");

const DB_Name = process.env.DB_NAME;
const DB_USERNAME = process.env.DB_USERNAME;
const DB_PASSWORD = process.env.DB_PASSWORD;

// connect to database
const my_db = new Sequelize(DB_Name, DB_USERNAME, DB_PASSWORD, {
    host: process.env.DB_HOST,
    dialect: "mysql",
});

module.exports = my_db;
