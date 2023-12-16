const express = require('express');
const transactionsData = require("../model/transaction_data_model");
const cloudinary = require("../utils/cloudinary_config");
const upload = require("../middleware/image_upload");
const transactions = require('../model/transaction_model');
const carts = require("../model/cart_model");
const products = require("../model/products_model");

const getAllTransactionData = async (req, res) => {
    try {
        const { buyerId } = req.params;
        const transactionData = await transactionsData.findAll({
            where: {
                buyerId: buyerId
            },
            include: [
                {
                    model: carts,
                    attributes: ['transactionId'],
                    include: [
                        {
                            model: transactions,
                            attributes: ['productId', 'count', 'total_price'],
                            include: [
                                {
                                    model: products,
                                    attributes: ['product_name']
                                }
                            ]
                        }
                    ]
                }
            ]
        });
        if (transactionData === undefined) {
            res.status(400).json({
                status: "failed",
                message: 'Transaction data not exist!'
            });
        }
        res.status(200).json({
            status: "success",
            message: "Successfully fetched all transaction data",
            transactions: transactionData
        });

    } catch (error) {
        console.log(`Error: ${error.message}`);
        res.status(500).json({
            status: "error",
            message: "Internal server error"
        });
    }
};

const postTransactionData = async (req, res) => {
    try {
        const status = 'Pending';
        const { transactionId, cartId } = req.body;
        const { buyerId } = req.params;
        let imageUrl;
        const transaction = await transactions.findByPk(transactionId);
        if (!transaction) {
            return res.status(400).json({
                status: "Error",
                message: `Transaction with id ${transactionId} does not exist!`
            });
        }
        if (req.file && transaction.id == transactionId) {
            const files = req.file.path;
            const uploadFile = await cloudinary.uploader.upload(files, {
                folder: 'payment_proof'
            });
            imageUrl = uploadFile.secure_url;

            const newTransactionData = await transactionsData.create({
                payment_proof: imageUrl,
                status: status,
                transactionId: transactionId,
                cartId: cartId,
                buyerId: buyerId
            });

            res.status(201).json({
                status: "success",
                message: "successfull add new transaction Data",
                newTransactionData
            })
        }
    } catch (error) {
        console.log(`Error : ${error.message}`);
        res.status(500).json({ message: 'Internal server error' });
    }
};

const verifyParment = async (req, res) => {
    try {
        const { transactionId } = req.params;
        const { statusUpdate } = req.body; // Misalkan Anda menerima `newStatus` dari permintaan
        console.log(`status : ${statusUpdate}`);
        const transactionData = await transactionsData.findByPk(transactionId);
        if (!transactionData) {
            return res.status(400).json({
                status: "Error",
                message: `Transaction with id ${transactionId} does not exist!`
            });
        }
        const updatedStatus = {
            status: statusUpdate
        }

        // Melakukan update status pada transaksi
        const result = await transactionsData.update(updatedStatus, {
            where: {
                id: transactionId
            }
        } // Kondisi untuk menemukan transaksi yang ingin diubah
        );

        res.status(200).json({
            status: "Success",
            message: `Transaction with id ${transactionId} status updated to ${statusUpdate}`,
            transactionData
        });
    } catch (error) {
        console.log(`Error : ${error.message}`);
        res.status(500).json({ message: 'Internal server error' });
    }
};


module.exports = {
    getAllTransactionData,
    postTransactionData,
    verifyParment
}