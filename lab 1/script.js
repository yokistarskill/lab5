//forEach
var arr = ["Яблоко", "Апельсин", "Груша"];

arr.forEach(function (item, i, arr) {
	console.log(i + ": " + item + " (массив:" + arr + ")");
});
//Все элементы массива по очереди

var arr1 = [5, 10, 15];
let summ = 0;
arr1.forEach(function (item) {
	summ += item;
});
console.log('Cумма всех элементов массива = ' + summ);

//filter
var arr = [1, -1, 2, -2, 3];

var positiveArr = arr.filter(function (number) {
	return number > 0;
});

console.log('Числа без минуса ' + positiveArr); // 1,2,3

var numbers = [1, 3, 6, 8, 11];

var lucky = numbers.filter(function (number) {
	return number > 7;
});

console.log('Счастливые числа: ' + lucky)

//map 

var numbers1 = [1, 5, 10, 15];
var numbers = numbers1.map(function (x) {
	return x * 2;
});
console.log('Умноженные на 2 числа: ' + numbers)

var numbers2 = [1, 4, 9];
var resultNum = numbers2.map(Math.sqrt);
console.log('Корни чисел массива: ' + resultNum);

//every 

const sampleArray = [1, 2, 3, 4, 5];
console.log(sampleArray.every(number => number > 0));
//Являются ли все числа больше 0


function isBigEnough(element, index, array) {
	return element >= 10;
}
var passed = [12, 5, 8, 130, 44].every(isBigEnough);
console.log(passed);
//


//some

var arr5 = [10, 20, 30, 40];

function checkNumber(elem) {
	return elem == 30; // проверяем соответствует ли текущий элемент значению 30
}

// проверяем соответствует ли по крайней мере один элемент в массиве условию, заданному в передаваемой функции 
console.log(arr5.some(checkNumber))

let arr7 = [-1, -2, -3, 4];

let check = arr7.some(function (elem) {
	if (elem >= 0) {
		return true;
	} else {
		return false;
	}
});

console.log(check);

//reduce 

const euros = [29.76, 41.85, 46.5];
const sum = euros.reduce((total, amount) => total + amount);
//прибавляем к первому элементу остальные 2
console.log(sum);

let arr10 = [1, -2, -3, 4, 5, -6];

let result2 = arr10.reduce(function (sum, elem) {
	if (elem >= 0) {
		return sum + elem;
	} else {
		return sum;
	}
});

console.log(result2);

//reduceRight

const array11 = [[0, 1], [2, 3], [4, 5]].reduceRight(
	(accumulator, currentValue) => accumulator.concat(currentValue)
);

console.log(array11);
//Array [4, 5, 2, 3, 0, 1]

const numbers12 = [1, 2, 3, 4, 5, 6];

function sum_reducer(accumulator, currentValue) {
	return accumulator + currentValue;
}

let sum1 = numbers12.reduceRight(sum_reducer);
console.log(sum1); // 21



function House(ctFloors, ctRooms, isBasement) {
	this.floors = ctFloors; // количество этажей
	this.rooms = ctRooms; // количество комнат
	this.basement = isBasement; // наличие подвала

	this.getInfo = function () {
		return 'Количество этажей: ' + this.floors + 'Количество комнат: ' + this.rooms;
	}
}

//наследование на основе класса
function ModernHouse(ctFloors, ctRooms, isBasement, isAttic, ctVeranda) {
	House.apply(this, arguments); //вызываем конструктор House в контексте текущего конструктора, ModernHouse
	this.attic = isAttic;
	this.veranda = ctVeranda;
	this.getInfo = function () {
		//Полиморфизм, переопределям метод
		return 'Наличие веранды: ' + this.veranda;
	}
}
var modern = new ModernHouse(3, 23, true, true, true); // создаём объект класса ModernHouse
console.log(modern.getInfo()); // 3