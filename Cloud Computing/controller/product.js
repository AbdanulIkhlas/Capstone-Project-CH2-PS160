const express = require('express');
const products = require('../model/products_model');
const { promisify } = require('util');
const cloudinary = require("../utils/cloudinary_config");
const upload = require("../middleware/image_upload");
const categories = require('../model/category_model');
const sellers = require('../model/seller_model');



const getAllProducts = async (req, res) => {
    try {
        const product = await products.findAll({
            include: [
                {
                    model: categories,
                    attributes: ['id', 'category_name']
                },
                {
                    model: sellers,
                    attributes: ['id', 'name']
                }
            ]
        });
        res.status(200).json({
            status: "success",
            message: "Successfully fetched all product data",
            products: product
        });
    } catch (error) {
        console.log(`Error: ${error.message}`);
        res.status(500).json({
            status: "error",
            message: "Internal server error"
        });
    }
};


const getProductById = async (req, res) => {
    try {
        const { productId } = req.params;
        const product = await products.findByPk(productId, {
            include: [
                {
                    model: categories,
                    attributes: ['id', 'category_name']
                },
                {
                    model: sellers,
                    attributes: ['id', 'name']
                }
            ]
        });

        if (!product) {
            return res.status(400).json({
                status: "Error",
                message: `Product with id ${productId} does not exist!`
            });
        }

        res.status(201).json({
            status: "success",
            message: "Successfully fetched product data",
            product: product
        });
    } catch (error) {
        console.log(`Error: ${error.message}`);
        res.status(500).json({
            status: "error",
            message: "Internal server error"
        });
    }
};


const postProduct = async (req, res) => {
    try {
        let imageUrl;
        if (req.file) {
            const files = req.file.path;

            const { product_name, price, stock, description, product_durability, sellerId, categoryId } = req.body;
            const uploadFile = await cloudinary.uploader.upload(files, {
                folder: 'product_image'
            });
            imageUrl = uploadFile.secure_url;
            console.log(`product ${product_name}`);
            const newProduct = await products.create({
                product_name: product_name,
                price: price,
                stock: stock,
                description: description,
                product_durability: product_durability,
                image: imageUrl,
                sellerId: sellerId,
                categoryId: categoryId,
            })
            res.status(201).json({
                status: "success",
                message: "successfull add new product!",
                newProduct
            })
        }
    } catch (error) {
        console.log(`Error: ${error.message}`);
        res.status(500).json({ message: 'Internal server error' });
    }
};

const deleteProduct = async (req, res) => {
    try {
        const { productId } = req.params;
        const targetProduct = await products.destroy({
            where: {
                id: productId
            }
        })

        if (targetProduct === 0) {
            res.status(400).json({
                status: 'failed',
                message: `Product with id ${productId} is not exist!`
            })
        }
        res.status(200).json({
            status: "Success",
            message: "Successfully delete Product"
        })

    } catch (error) {
        res.status(500).json({
            status: "Error",
            message: error.message
        })
    }
};

const editProduct = async (req, res) => {
    try {
        const { id } = req.params;

        let imageUrl;
        if (req.file) {
            const files = req.file.path;
            const uploadFile = await cloudinary.uploader.upload(files, {
                folder: 'product_image'
            });

            // Dapatkan URL gambar baru
            imageUrl = uploadFile.secure_url;
        }
        const { product_name, price, stock, description, product_durability, sellerId, categoryId } = req.body;
        const updateProduct = {
            product_name,
            price,
            stock,
            description,
            product_durability,
            sellerId,
            categoryId
        }

        if (imageUrl) {
            updateProduct.image = imageUrl;
        }

        const result = await products.update(updateProduct, {
            where: {
                id: id
            }
        });
        if (result[0] === 0) {
            return res.status(404).json({
                status: 'failed',
                message: 'Product not found or no changes applied',
            });
        }
        const updatedProduct = await products.findByPk(id);
        res.status(200).json({
            status: 'success',
            message: 'Product updated successfully',
            updatedProduct
        });


    } catch (error) {
        console.log(`Error: ${error.message}`);
        res.status(500).json({ message: 'Internal server error' });
    }
}

module.exports = {
    getAllProducts,
    getProductById,
    postProduct,
    deleteProduct,
    editProduct
}

