const express = require("express");
const router = express.Router();

const buyer = require("../model/buyer_model");
const seller = require("../model/seller_model");
const upload = require('../middleware/image_upload');

const {
    getAllSeller,
    getAllBuyers,
    getBuyerById,
    getSellerById,
    postBuyer,
    postSeller,
    loginHandler,
    deleteBuyerUser,
    deleteSellerUser,
    updateBuyer,
    updateSeller
} = require("../controller/user");

const {
    getAllProducts,
    getProductById,
    postProduct,
    deleteProduct,
    editProduct
} = require('../controller/product');

const {
    getAllTestimoni,
    postTestimoni,
    deleteTesti
} = require('../controller/testimoni');

const {
    getAllTransaction,
    getAllTransactionById,
    newTransaction,
    deleteTransaction
} = require('../controller/transaction');

const {
    getAllTransactionData,
    postTransactionData,
    verifyParment
} = require('../controller/transaction_data');

const { getAllCart } = require('../controller/cart');

const {
    getProductDetailById,
    newBidProduct
} = require('../controller/product_detail');

router.get("/buyers", getAllBuyers);
router.get("/sellers", getAllSeller);
router.get("/products", getAllProducts);
router.get("/testimonies", getAllTestimoni);
router.get("/transactions", getAllTransaction);
router.get("/transactionsData/:buyerId", getAllTransactionData);
router.get("/carts/:id_cart_params", getAllCart);

router.get("/buyer/:buyerId", getBuyerById);
router.get("/seller/:sellerId", getSellerById);
router.get("/product/:productId", getProductById);
router.get("/transaction/:transactionId", getAllTransactionById);
router.get("/product/detail/:productId", getProductDetailById);

router.post("/registerBuyer", upload.single('file'), postBuyer);
router.post("/registerSeller", upload.single('file'), postSeller);
router.post("/login", loginHandler);
router.post("/newProduct", upload.single('file'), postProduct)
router.post("/newTesti", postTestimoni);
router.post("/newTransaction", newTransaction);
router.post("/newTransactionData/:buyerId", upload.single('file'), postTransactionData);
router.post("/bidRequest/:id_product", newBidProduct)


router.delete("/deleteBuyer/:buyerId", deleteBuyerUser);
router.delete("/deleteSeller/:sellerId", deleteSellerUser);
router.delete("/deleteProduct/:productId", deleteProduct);
router.delete("/deleteTransaction/:transactionId", deleteTransaction);
router.delete("/deleteTesti/:testiId", deleteTesti);

router.put('/update/buyer/:id', updateBuyer);
router.put('/update/seller/:id', updateSeller);
router.put('/update/product/:id', editProduct);
router.put('/verifyPayment/:transactionId', verifyParment);


module.exports = router;
