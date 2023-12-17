const express = require('express');
const carts = require("../model/cart_model");
const transactions = require("../model/transaction_model");
const buyers = require("../model/buyer_model");
const products = require('../model/products_model');


const getAllCart = async (req, res) => {

    try {
        const { id_cart_params } = req.params;
        const cart = await carts.findAll(
            {
                include: [
                    {
                        model: transactions,
                        where: {
                            sellerId: id_cart_params
                        },
                        attributes: ['id', 'count', 'total_price', 'productId', 'sellerId'],
                        include: [
                            {
                                model: products,
                                attributes: ['id', 'product_name', 'product_durability', 'image']
                            }
                        ]
                    },
                ]
            });
        if (cart === undefined) {
            res.status(400).json({
                status: "failed",
                message: "carts does not exist"
            });

        }
        res.status(200).json({
            status: "success",
            message: "Successfully fetched all cart data",
            carts: cart
        });

    } catch (error) {
        console.log(`Error: ${error.message}`);
        res.status(500).json({
            status: "error",
            message: "Internal server error"
        });
    }
};


module.exports = {
    getAllCart,
}