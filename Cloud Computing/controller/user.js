const express = require('express');
const buyers = require('../model/buyer_model');
const sellers = require('../model/seller_model');
const router = express.Router();
const app = express();
const cloudStorageConfig = require("../utils/cloudStorage_config");
const { Bucket } = require('@google-cloud/storage');

const { promisify } = require('util');
const cloudinary = require("../utils/cloudinary_config");
const upload = require("../middleware/image_upload");


//get all pembeli data
const getAllBuyers = async (req, res) => {
    try {
        const buyer = await buyers.findAll();
        res.status(200).json({
            status: "success",
            message: "Sucessfull fetch all data buyer",
            buyers: buyer,
        })
    } catch (error) {
        console.log(`error : ${error.message}`);

    }
};

//get all penjual data
const getAllSeller = async (req, res) => {
    try {
        const seller = await sellers.findAll();
        res.status(200).json({
            status: "success",
            message: "Sucessfull fetch all data seller",
            sellers: seller,
        })
    } catch (error) {
        console.log(`error : ${error.message}`);

    }
};

//get user data by id
const getBuyerById = async (req, res) => {
    try {
        const { buyerId } = req.params;
        const buyer = await buyers.findByPk(buyerId);

        if (!buyer) {
            return res.status(400).json({
                status: "Error",
                message: `User with id ${buyerId} does not exist!`
            });
        }

        res.status(200).json({
            status: "success",
            message: "Successful fetch data buyer",
            buyer: buyer,
        });
    } catch (error) {
        console.log(`Error: ${error.message}`);
        res.status(500).json({
            status: "error",
            message: "Internal server error"
        });
    }
};

const getSellerById = async (req, res) => {
    try {
        const { sellerId } = req.params;
        const seller = await sellers.findByPk(sellerId);

        if (!seller) {
            return res.status(400).json({
                status: "Error",
                message: `User with id ${sellerId} does not exist!`
            });
        }

        res.status(200).json({
            status: "success",
            message: "Successful fetch data seller",
            seller: seller,
        });
    } catch (error) {
        console.log(`Error: ${error.message}`);
        res.status(500).json({
            status: "error",
            message: "Internal server error"
        });
    }
};

//Post buyer data
const postBuyer = async (req, res) => {
    try {
        let imageUrl;
        if (req.file) {
            const files = req.file.path;
            const { email, password, nama, alamat, kordinat, no_hp } = req.body;

            const uploadFile = await cloudinary.uploader.upload(files, {
                folder: 'user_profile_image'
            });

            //didapat image URL
            imageUrl = uploadFile.secure_url;
            const newBuyer = await buyers.create({
                email: email,
                password: password,
                name: nama,
                alamat: alamat,
                kordinat: kordinat,
                no_hp: no_hp,
                image: imageUrl
            });

            res.status(201).json({
                status: "success",
                message: "sucessfull register",
                newBuyer
            });
        }
    } catch (error) {
        console.log(`Error : ${error.message}`);
        res.status(500).json({ message: 'Internal server error' });
    }
};

//post seller data
const postSeller = async (req, res) => {
    try {
        let imageUrl;
        if (req.file) {
            const files = req.file.path;
            const { email, password, nama, alamat, kordinat, no_hp, no_rekening } = req.body;
            const uploadFile = await cloudinary.uploader.upload(files,
                {
                    folder: 'user_profile_image'
                });

            //didapat image URL
            imageUrl = uploadFile.secure_url;
            const newSeller = await sellers.create({
                email: email,
                password: password,
                name: nama,
                alamat: alamat,
                kordinat: kordinat,
                no_hp: no_hp,
                no_rekening: no_rekening,
                image: imageUrl
            });
            // sellers.push(newSeller);
            res.status(201).json({
                status: "success",
                message: "sucessfull register Seller",
                newSeller
            })
        }
    } catch (error) {
        console.log(`Error : ${error.message}`);
    }
};

const loginHandler = async (req, res) => {
    try {
        const { email, password } = req.body;

        const buyerUser = await buyers.findOne({
            where: {
                email: email
            }
        });
        const sellerUser = await sellers.findOne({
            where: {
                email: email
            }
        });
        if (buyerUser && buyerUser.password === password) {
            res.status(200).json({
                status: "success",
                message: "Login Buyer Success!",
                buyerUser
            });
        } else if (sellerUser && sellerUser.password === password) {
            res.status(200).json({
                status: "success",
                message: "Login Seller Success!",
                sellerUser: sellerUser
            });
        } else {
            const err = new Error("Wrong email or password");
            err.statusCode = 400;
            throw err;
        }
    } catch (error) {
        res.status(error.statusCode || 500).json({
            status: "error",
            message: error.message
        })
    }
};

const uploadImageHandler = async (req, res) => {
    const multer = Multer({
        storage: Multer.diskStorage({}),
        limits: {
            fileSize: 5 * 1024 * 1024, //dibawah 5MB
        }
    });
    try {
        const uploadPromise = promisify(multer.single("file"));
        await uploadPromise(req, res);
        const pathImage = req.file.path;
        const result = await cloudinary.uploader.upload(pathImage);
        res.status(200).json({
            status: "success",
            img_url: result.secure_url
        });

    } catch (error) {
        console.log(`Error : ${error.message}`)
    }
};

const deleteBuyerUser = async (req, res) => {
    try {
        const { buyerId } = req.params;
        const targetBuyer = await buyers.destroy({
            where: {
                id: buyerId
            }
        })

        if (targetBuyer === 0) {
            res.status(400).json({
                status: 'failed',
                message: `Buyer with id ${buyerId} is not exist!`
            })
        }
        res.status(200).json({
            status: "Success",
            message: "Successfully delete user"
        })

    } catch (error) {
        res.status(500).json({
            status: "Error",
            message: error.message
        })
    }
};

const deleteSellerUser = async (req, res) => {
    try {
        const { sellerId } = req.params;
        const targetSeller = await sellers.destroy({
            where: {
                id: sellerId
            }
        })
        if (targetSeller === 0) {
            res.status(400).json({
                status: 'failed',
                message: `Seller with id ${sellerId} is not exist!`
            })
        }
        res.status(200).json({
            status: "Success",
            message: "Successfully delete user"
        })

    } catch (error) {
        res.status(500).json({
            status: "Error",
            message: error.message
        })
    }
};

//PUT
const updateBuyer = async (req, res) => {
    try {
        const { id } = req.params; // Ambil ID pembeli yang ingin diubah dari URL

        let imageUrl;
        if (req.file) {
            const files = req.file.path;
            const uploadFile = await cloudinary.uploader.upload(files, {
                folder: 'user_profile_image'
            });

            // Dapatkan URL gambar baru
            imageUrl = uploadFile.secure_url;
        }

        // Buat objek yang berisi perubahan data
        const { email, password, nama, alamat, kordinat, no_hp } = req.body;
        const updatedData = {
            email,
            password,
            name: nama,
            alamat,
            kordinat,
            no_hp,
            image: imageUrl
        };

        if (imageUrl) {
            updatedData.image = imageUrl;
        }

        const result = await buyers.update(updatedData, {
            where: {
                id: id
            }
        });

        // Periksa apakah ada baris yang terpengaruh (diupdate)
        if (result[0] === 0) {
            return res.status(404).json({
                status: 'failed',
                message: 'Buyer not found or no changes applied',
                updatedData: updatedData,
                result
            });
        }

        // Ambil data terbaru setelah update
        const updatedBuyer = await buyers.findByPk(id);

        res.status(200).json({
            status: 'success',
            message: 'Buyer updated successfully',
            updatedBuyer
        });
    } catch (error) {
        console.log(`Error: ${error.message}`);
        res.status(500).json({ message: 'Internal server error' });
    }
};

const updateSeller = async (req, res) => {
    try {
        const { id } = req.params;
        let imageUrl;
        if (req.file) {
            const files = req.file.path;
            const uploadFile = await cloudinary.uploader.upload(files, {
                folder: 'user_profile_image'
            });
            imageUrl = uploadFile.secure_url;
        }
        const { email, password, nama, alamat, kordinat, no_hp, no_rekening } = req.body;
        console.log(`name update : ${nama}`);
        const updatedData = {
            email,
            password,
            name: nama,
            alamat,
            kordinat,
            no_hp,
            no_rekening,
            image: imageUrl
        };
        if (imageUrl) {
            updatedData.image = imageUrl;
        }

        const result = await sellers.update(updatedData, {
            where: {
                id: id
            }
        });

        if (result[0] === 0) {
            return res.status(404).json({
                status: 'failed',
                message: 'Seller not found or no changes applied',
                updatedData: updatedData,
                result
            });
        }

        const updatedSeller = await sellers.findByPk(id);
        res.status(200).json({
            status: 'success',
            message: 'Seller updated successfully',
            updatedSeller
        });
    } catch (error) {
        console.log(`Error: ${error.message}`);
        res.status(500).json({ message: 'Internal server error' });
    }
};




module.exports = {
    getAllSeller,
    getAllBuyers,
    getBuyerById,
    getSellerById,
    postBuyer,
    postSeller,
    loginHandler,
    uploadImageHandler,
    deleteBuyerUser,
    deleteSellerUser,
    updateBuyer,
    updateSeller

};