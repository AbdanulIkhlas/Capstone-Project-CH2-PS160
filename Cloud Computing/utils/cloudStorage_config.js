require('dotenv').config();
const express = require("express");
const { Storage } = require("@google-cloud/storage");
const Multer = require("multer");

const multer = Multer({
    storage: Multer.memoryStorage(),
    limits: {
        fileSize: 5 * 1024 * 1024, //dibawah 5MB

    }
});

let projectId = '';
let keyFileName = '';
const storage = new Storage({
    projectId,
    keyFileName,
});



module.exports = {
    multer,
    storage
}
