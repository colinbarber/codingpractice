// module pattern

// set up namespace
MYAPP.namespace('MYAPP.utilities.array');

// define the module
MYAPP.utilities.array = (function () {
    // dependencies
    let uobj = MYAPP.utilities.object,
        ulang =MYAPP.utilities.lang,

        // private properties
        array_string = "[object Array]",
        ops = Object.prototype.toString();

    // private methods
    // init procedures

    // public API
    return {
        inArray: function (needle, haystack) {
            for (let i = 0, max = haystack.length; i < max; i += 1) {
                if (haystack[i] === needle) {
                    return true;
                }
            }
        },
        isArray: function (a) {
            return ops.call(a) === array_string;
        }
        // more methods and properties
    };
}());
