const express = require('express');
const app = express();
const userRoutes = require('./routes/user_routes');
const association = require("./utils/assoc_db");
require('dotenv').config();

const PORT = process.env.PORT;

app.use(express.json());
app.use(userRoutes);

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