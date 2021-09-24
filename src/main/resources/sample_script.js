function accumulator(a, b) {
    var GraalTest = Java.type("GraalTest")
    GraalTest.print();
    return a + b;
}

function makeContract(name, phoneNumber) {
    var contract = new Object();
    contract.name = name;
    contract.phoneNumber = phoneNumber;
    contract.print = function () {
        print('name =' + name)
        print('phoneNumber =' + phoneNumber)
    }
    return contract
}