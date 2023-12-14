const buyer = require("../model/buyer_model");
const seller = require("../model/seller_model");
const my_db = require("./connect_db");

const category = require("../model/category_model");
const product = require("../model/products_model");
const testimoni = require("../model/testimoni_model");
const transaction = require('../model/transaction_model');



const categories = [
    { category_name: "Sayur Hijau" },
    { category_name: "Umbi-Umbian" },
    { category_name: "Rempah" },
    { category_name: "Kacang-Kacangan" },
    { category_name: "Alat Tani" },
    { category_name: "Bibit" },
]

category.hasMany(product); // setiap kategori dapat memiliki banyak produk
product.belongsTo(seller, { foreignKey: { name: 'sellerId', allowNull: false } }); // setiap produk terhubung dg 1 seller
product.belongsTo(category, { foreignKey: { name: 'categoryId', allowNull: false } });

// Relasi one-to-one antara Penjual dan Transaksi
buyer.hasOne(transaction, { foreignKey: { name: 'buyerId', allowNull: false } });
transaction.belongsTo(buyer, { foreignKey: { name: 'buyerId', allowNull: false } });

// Relasi many-to-one antara Produk dan Transaksi
product.hasMany(transaction, { foreignKey: { name: 'productId', allowNull: false } });
transaction.belongsTo(product, { foreignKey: { name: 'productId', allowNull: false } });

// Relasi one-to-one antara Pembeli dan Transaksi
seller.hasOne(transaction, { foreignKey: { name: 'sellerId', allowNull: false } });
transaction.belongsTo(seller, { foreignKey: { name: 'sellerId', allowNull: false } });

// Relasi one-to-one antara Transaksi dan Testimoni
testimoni.belongsTo(transaction, { foreignKey: { name: 'transactionId', allowNull: false } });


const association = async () => {
    try {
        await my_db.sync({ force: false });
        buyer.bulkCreate(buyer);
        seller.bulkCreate(seller);
        testimoni.bulkCreate(testimoni);
        transaction.bulkCreate(transaction);
        // category.bulkCreate(categories);
        product.bulkCreate(product);
    } catch (error) {
        console.log(`Error Create : ${error.message}`);

    }
};

module.exports = association;