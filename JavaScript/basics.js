/* single variable pattern
* */
function func() {
    let a = 1,
        b = 2,
        sum = a + b,
        myobject = {},
        i,
        j;
    // function body
}

/* DOM reference and local variable together
* */
function updateElement() {
    let el = document.getElementById("Result"),
        style = el.style;
    //do something with el and style
}

/* looping through HTMLCollections (DOM reference arrays) - ex:
* */
document.getElementsByName();
document.getElementsByClassName();
document.getElementsByTagName();
document.images;
document.links;
document.forms;
// cache the length of array to avoid unnecessary DOM operations
for (let i = 0, max = myarray.length; i < max; i++) {
    // do something with myarray[i]
}
// micro-optimized versions
let i, myarray = [];
for (i = myarray.length; i--;) {
    // do something with myarray[i]
}
let myarray = [],
    i = myarray.length;
while (i--) {
    // do something with myarray[i]
}

/* enumeration - for-in loops
* */
let man = {
    hands: 2,
    legs: 2,
    heads: 1
};
// somewhere else, a method was added to all objects
if (typeof Object.prototype.clone === "undefined") {
    Object.prototype.clone = function () {};
}
// filter the clone() method by using hasOwnProperty()
for (let i in man) {
    if (man.hasOwnProperty(i)) {
        console.log(i, ":", man[i]);
    }
}

/* augmenting built-in prototypes (don't)
* */
if (typeof  Object.prototype.myMethod !== "function") {
    Object.prototype.myMethod = function () {
      // implementation
    };
}

/* switch pattern
* */
let inspect_me = 0,
    result = '';

switch (inspect_me) {
    case 0:
        result = "zero";
        break;
    case 1:
        result = "one";
        break;
    default:
        result = "unknown";
}

/* avoid typecasting false == 0 (true)
* */
let zero = 0;
if (zero === false) {
    // doesn't execute
}

/* convert string to number
* */
let number = "06";
let numberStr = parseInt(number, 10);
numberStr = +"06";

console.log(numberStr);

