const express = require('express');
const app = express();
const userRoutes = require('./routes/user_routes');
const association = require("./utils/assoc_db");
require('dotenv').config();

const PORT = process.env.PORT;

app.use(express.json());
app.use(userRoutes);
app.use((req, res, next) => {
    res.header('Access-Control-Allow-Origin', '*');
    res.header('Access-Control-Allow-Headers', 'Origin, X-Requested-With, Content-Type, Accept, Authorization');
    if (req.method === 'OPTIONS') {
        res.header('Access-Control-Allow-Methods', 'PUT, POST, PATCH, DELETE, GET');
        return res.status(200).json({});
    }
    next();
})

// app.listen(PORT, () => {
//     console.log(`Server running in port ${PORT}`);
// })

association()
    .then(() => {
        app.listen(PORT, () => {
            console.log(`Server running in port ${PORT}`);
        });

    })

    .catch((error) => {
        console.log(error.message);
    });