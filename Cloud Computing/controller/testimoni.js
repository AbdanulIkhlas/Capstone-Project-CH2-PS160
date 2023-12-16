const express = require('express');
const testimonies = require('../model/testimoni_model');

const getAllTestimoni = async (req, res) => {
    try {
        const testimoni = await testimonies.findAll();
        res.status(200).json({
            status: "success",
            message: "successfully fetch all testimoni!",
            testimonies: testimoni
        })
    } catch (error) {
        console.log(`Error: ${error.message}`);
        res.status(500).json({
            status: "error",
            message: "Internal server error"
        });
    }
};

const postTestimoni = async (req, res) => {
    try {
        const { rating, description, transactionId } = req.body;

        const newTestimoni = await testimonies.create({
            rating: rating,
            description: description,
            transactionId: transactionId
        });
        res.status(201).json({
            status: "success",
            message: "successfull add new testimoni!",
            newTestimoni
        })
    } catch (error) {
        console.log(`Error : ${error.message}`);
    }
};

const deleteTesti = async (req, res) => {
    try {
        const { testiId } = req.params;
        const targetTesti = await testimonies.destroy({
            where: {
                id: testiId
            }
        });

        if (targetTesti === 0) {
            res.status(400).json({
                status: 'failed',
                message: `Testimoni with id ${testiId} is not exist!`
            });
        }
        res.status(200).json({
            status: "success",
            message: "Successfully delete testimoni"
        })
    } catch (error) {
        res.status(500).json({
            status: "Error",
            message: error.message
        })
    }
}

module.exports = {
    getAllTestimoni,
    postTestimoni,
    deleteTesti
}
