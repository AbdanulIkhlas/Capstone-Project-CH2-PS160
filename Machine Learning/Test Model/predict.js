// Akses tf secara global
// const loadModel = async (modelName) => {
//     const model = await tf.loadLayersModel(modelName);
//     // const model = await tf.loadLayersModel(`https://domain/models/${modelName}.h5}`);
//     return model;
//   }
// HTML Elements
const productInput = document.getElementById('product-input');
const predictButton = document.getElementById('predict-button');
const resultArea = document.getElementById('result-area');



// Predict next 7 days
const predictNext7Days = (model, inputData) => {

  // Predictions array
  const predictions = [];
  
  // First prediction
  let currentPred = model.predict(inputData);

  // Store first prediction
  predictions.push(currentPred);

  for(let i = 0; i < 7; i++) {

    let input = [];
    input.push(currentPred);

    currentPred = model.predict(input);

    predictions.push(currentPred);

  }

  return predictions;

}

// Main predict function
const predict = async (product) => {

    //   console.log(inputData)
    const model = await tf.loadLayersModel('model.json');
    
  const inputData = product;
  const predictions = predictNext7Days(model, inputData);
  
  // Display predictions
  resultArea.innerText = `Predicted Prices: ${predictions}`;
  
}

// On predict button click 
predictButton.addEventListener('click', async () => {

    const product = productInput.value;
    console.log(product)
  await predict(product); 

});