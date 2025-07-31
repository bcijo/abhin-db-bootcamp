
// node js-basics.js 

// string literals 
// console.log("Sonu");
// console.log('Sonu');
// console.log(`Sonu`);

// variable declaration 

// java / C, C++ 
// int num = 10;
// String name = "Sonu";

// JavaScript 

num = 10; // don't use this 
var num2 = 20; // don't use this also 
const num3 = 30; // use this preferrably, for constants 

// let num4 = 40; // also this for 'variables'

// console.log(num3);
// num3 = 35; // error 
// console.log(num3);

// let num4;
// console.log(num4); // ?? 
// console.log(typeof num4);
// num4 = 45;
// console.log(num4);
// console.log(typeof num4);
// num4 = "abc";
// console.log(num4);
// console.log(typeof num4);
// num4 = false;
// console.log(num4);
// console.log(typeof num4);
// num4 = [10, 20, 30 ];
// console.log(num4);
// console.log(typeof num4);

// Arrays and objects in JS 

// arrrays 
let myArray = [10, 20.50, "abc", false, [25, "xyz"]];
console.log(myArray);

// objects 
let employee = {
    id: 101,
    name: "Sonu Singh",
    salary: 90000.50,
    isIndian: true,
    address: {
        street: "Abids road",
        city: 'Hyderabad',
        pin: 500001
    },
    phones: [9876543210, 7894561230],
    email: "sonu.singh@abc.com"
};
console.log(employee);
console.log("---");
console.log(employee.name);


