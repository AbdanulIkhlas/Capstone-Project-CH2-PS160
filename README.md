# Capstone Team CH2-PS160 [P A T A N I]

<!-- PROJECT LOGO -->
<br/>
<p align="center">
  <a href="https://github.com/AbdanulIkhlas/Capstone-Project-CH2-PS160">
    <img src="https://github.com/AbdanulIkhlas/Capstone-Project-CH2-PS160/blob/main/A%20Documentation/LogoPatani.png" alt="Logo" width="300">
  </a>
  
  <h2 align="center">Bangkit Capstone Project 2023 Batch 2: P A T A N I (E-commerce Agriculture Application)</h2>
  <h2 align="center">~ Revolutionizing Agriculture ~</h2>

  <p align="center">
    ·
    <a href="https://github.com/AbdanulIkhlas/Capstone-Project-CH2-PS160/issues">Report Bug</a>
  </p>

  
  <p align="center">
    <img src="https://github.com/AbdanulIkhlas/Capstone-Project-CH2-PS160/blob/main/A%20Documentation/Patani1.png" alt="Banner" width="160">
    <img src="https://github.com/AbdanulIkhlas/Capstone-Project-CH2-PS160/blob/main/A%20Documentation/Patani2.png" alt="Banner" width="166">
    <img src="https://github.com/AbdanulIkhlas/Capstone-Project-CH2-PS160/blob/main/A%20Documentation/Patani3.png" alt="Banner" width="160">
    <img src="https://github.com/AbdanulIkhlas/Capstone-Project-CH2-PS160/blob/main/A%20Documentation/Patani4.png" alt="Banner" width="152">
    <img src="https://github.com/AbdanulIkhlas/Capstone-Project-CH2-PS160/blob/main/A%20Documentation/Patani5.png" alt="Banner" width="167">
  </p>
</p>
<br/>

<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary><h2 style="display: inline-block">Table of Contents</h2></summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
    </li>
    <li>
      <a href="#documentation">Documentation</a>
      <ul>
        <li>
          <a href="#machine-learning">Machine Learning</a>
          <ul>
            <li><a href="#library">Library</a></li>
          </ul>
        </li>
        <li>
          <a href="#cloud-computing">Cloud Computing</a>
        </li>
        <li>
          <a href="#mobile-development">Mobile Development</a>
          <ul>
            <li><a href="#prerequisites">Prerequisites</a></li>
            <li><a href="#installation">Installation</a></li>
            <li><a href="#usage">How to use</a></li>
          </ul>
        </li>
      </ul>
    </li> 
    <li><a href="#our-team">Our Team</a></li>
  </ol>
</details>
<br/>
<br/>


## About The Project
<div style="text-align: justify">
  PATANI is a special agricultural buying and selling application that sells farm products such as vegetables, farm tools, and vegetable seeds. This application aims to facilitate accessibility between farmers and their buyers by utilizing technology. In addition, this application can be a forum that helps build an ecosystem of urban farmers. The main features are price quotes and price recommendations that are best from price predictions based on previous transactions. By using this application, it will certainly make it easier for farmers and buyers to transact, can offer prices so that they can be mutually beneficial, and are not confused in determining prices because there is already information on price recommendations for each product.
</div>
<br/>

## Documentation
### Machine Learning
<div style="text-align: justify">
  <p>
    Machine Learning in the PATANI application is a price prediction for the next week where the results of the prediction are used for price recommendations for farmers and buyers. Price recommendations for farmers aim to be information that can help farmers in determining prices, while price recommendations for buyers can be used as a benchmark in submitting price offers.
    <br/><br/>
    For the accuracy of price predictions, we use <span><a href="https://github.com/AbdanulIkhlas/Capstone-Project-CH2-PS160/tree/main/Machine%20Learning/Dataset">datasets</a></span> that we collect from various sources such as BPS, from Google, and prices of agricultural products in other e-commerce.
    <br/><br/>
    For modeling, we used <span><a href="https://www.tensorflow.org/?hl=id">Tensorflow</a></span> and <span><a href="https://medium.com/bina-nusantara-it-division/lstm-long-short-term-memory-d29779e2ebf8">Long Short Term Memory (LSTM)</a></span> forcasting model to predict the price of the product on the next day. To measure the accuracy, we used <span><a href="https://www.aindhae.com/2019/10/cara-menghitung-root-mean-squared-error.html">Root Mean Square Error (RMSE)</a></span> and the average RMSE we got from our model was <0.1. For the deployment stage, we use <span><a href="https://www.tensorflow.org/tfx/guide/serving?hl=id">Tensorflow Serving</a></span> which is deployed to Google Cloud.
  </p>
</div>

#### Library
* [Pandas](https://pandas.pydata.org/)
* [Numpy](https://numpy.org/)
* [Tensorflow](https://www.tensorflow.org/)
* [Zipfile](https://docs.python.org/3/library/zipfile.html)


<br/><br/>

### Cloud Computing
<div style="text-align: justify">
  Build infrastructure on Google Cloud Platform (GCP). Creating a database to store data using Cloud SQL and creating APIs (backend) using Node Js and Express to connect the database with Mobile Development (Frontend), deployment of APIs to cloud Run, deployment of models provided by machine learning to cloud runs, and creating APIs for models to be used in Mobile Development as an implementation of machine learning. and monitoring on GCP.
</div>

<br/><br/>

### Mobile Development
<div style="text-align: justify">
  We carried out the process of creating static views using Jetpack Compose. Upon completion, we performed API integration with CRUD operations from the implementation of Cloud Computing APIs. With the provided API, we adjusted and implemented it within the application using Retrofit. After everything was completed, we integrated Machine Learning into the application using a model that had been exported in JSON format.
</div>


## Our Team
* Muhammad Abdanul Ikhlas (M297BSY1103) - muhabdanulikhlas0983@gmail.com - ML
* Hisyam Agus Setiawan (M179BSY1396) - hisyamagus12@gmail.com - ML
* Zulia Amalia (M183BSX1518) - zuliaamalia108@gmail.com - ML
* Komang Yuda Saputra (C297BSY3618) - yudasaputra082@gmail.com - CC
* Bella Febriany Nawangsari (C297BSX3626) - bellafebrianynws@gmail.com - CC
* Muhamad Firdaus (A629BSY2144) - muhfrds345@gmail.com - MD
* Muhammad Nur Faiz (A123BSY2557) - mnurfaiz26@gmail.com - MD

