const express = require('express');
const product_details = require("../model/product_detail_model");
const products = require("../model/products_model");

const getProductDetailById = async (req, res) => {
    try {
        const { productId } = req.params;
        const productDetail = await products.findByPk(productId, {
            include: [
                {
                    model: products,
                    attributes: ['product_name', 'price', 'stock', 'description', 'image']
                }
            ]
        });

        if (!productDetail) {
            return res.status(400).json({
                status: "Error",
                message: `Product with id ${productId} does not exist!`
            });
        }

        res.status(200).json({
            status: "success",
            message: "Successfully fetched product detail",
            product: productDetail
        });
    } catch (error) {
        console.log(`Error: ${error.message}`);
        res.status(500).json({
            status: "error",
            message: "Internal server error"
        });

    }
};

const newBidProduct = async (req, res) => {
    try {
        const { bid_price, count } = req.body;
        const { id_product } = req.params;
        const product = await products.findOne({
            where: {
                id: id_product
            }
        });

        if (!product) {
            return res.status(400).json({
                status: "Error",
                message: `Product with id ${id_product} does not exist!`
            });
        }

        if (product.id == id_product) {
            const newBidRequest = await product_details.create({
                bid_price,
                count,
                id_product,
            })
            res.status(201).json({
                status: "success",
                message: "Successfully add bid request",
                product: newBidRequest
            });
        }

    } catch (error) {
        console.log(`Error: ${error.message}`);
        res.status(500).json({
            status: "error",
            message: "Internal server error"
        });
    }
};

module.exports = {
    getProductDetailById,
    newBidProduct
}