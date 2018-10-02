// single var pattern
function func() {
    var a = 1,
        b = 2,
        sum = a + b,
        myobject = {},
        i,
        j;

    // function body
}

// DOM reference and local variable together
function updateElement() {
    var el = document.getElementById("Result"),
        style = el.style;

    //do something with el and style
}

// HTMLCollections
document.getElementsByName();
document.getElementsByClassName();
document.getElementsByTagName();
document.images;
document.links;
document.forms;


