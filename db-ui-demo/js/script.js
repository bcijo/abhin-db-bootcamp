function sayHi() {
   const button = document.querySelector("button");
   let buttonclicked = 0;
   const name = "Abhin";
   button.addEventListener("click", function() {
     buttonclicked++;
     document.querySelector("h1").textContent = `Hello ${name}, you clicked the button ${buttonclicked} times!`;
     if (buttonclicked >= 5) {
       buttonclicked= 0;
       document.querySelectorAll("h1")[1].textContent = `Hello ${name}, you have reset the click count!`;
     }
    });
}

function getName() {
    const button = document.getElementById("greetBtn");
    const input = document.getElementById("nameInput"); 
    const heading = document.querySelector("h1");

    button.addEventListener("click", function () {
    const name = input.value;
    heading.textContent = `Hello, ${name}!`;
    });
}

function simpleCalculator() {
    const input1 = document.getElementById("input1");
    const input2 = document.getElementById("input2");
    const addButton = document.getElementById("addBtn");
    const subButton = document.getElementById("subtractBtn");
    const mulButton = document.getElementById("multiplyBtn");
    const divButton = document.getElementById("divideBtn");
    
    addButton.addEventListener("click", function () {
        let sum = Number(input1.value) + Number(input2.value);
        document.querySelector("h1").textContent = `Sum: ${sum}`;
    });

    subButton.addEventListener("click", function () {
        let difference = Number(input1.value) - Number(input2.value);
        document.querySelector("h1").textContent = `Difference: ${difference}`;
    });

    mulButton.addEventListener("click", function () {
        let product = Number(input1.value) * Number(input2.value);
        document.querySelector("h1").textContent = `Product: ${product}`;
    });

    divButton.addEventListener("click", function () {
        let quotient = Number(input1.value) / Number(input2.value);
        document.querySelector("h1").textContent = `Quotient: ${quotient}`;
    });
}

simpleCalculator();
// sayHi();
// getName();
console.log("Script loaded successfully!");

