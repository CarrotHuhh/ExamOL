//get current time
export function getCurDate() {
	var now = new Date();
	var year = now.getFullYear();
	var month = now.getMonth() + 1;
	var day = now.getDate();
	month = month < 10 ? "0" + day : day;
	return year + "-" + month + "-" + day;
}

//store a JSON in sessionStorage
export function setSessionStorage(keyStr, value) {
	sessionStorage.setItem(keyStr, JSON.stringify(value));
}

//get a JSON from sessionStorage, if nothing return null
export function getSessionStorage(keyStr) {
	var str = sessionStorage.getItem(keyStr);
	if (str == '' || str == null || str == 'null' || str == undefined) {
		return null;
	}
	else {
		return JSON.parse(str);
	}
}

//remove a JSON from sessionStorage
export function removeSessionStorage(keyStr) {
	sessionStorage.removeItem(keyStr);
}

//store a JSON in localStorage
export function setLocalStorage(keyStr, value) {
	localStorage.setItem(keyStr, JSON.stringify(value));
}

//get a JSON from localStorage, if nothing return null
export function getLocalStorage(keyStr) {
	var str = localStorage.getItem(keyStr);
	if (str == '' || str == null || str == 'null' || str == undefined) {
		return null;
	}
	else {
		return JSON.parse(str);
	}
}

//remove a JSON from localStorage
export function removeLocalStorage(keyStr) {
	localStorage.removeItem(keyStr);
}